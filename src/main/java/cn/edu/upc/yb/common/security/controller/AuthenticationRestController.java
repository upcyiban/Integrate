package cn.edu.upc.yb.common.security.controller;

import cn.edu.upc.yb.common.dto.ErrorReporter;
import cn.edu.upc.yb.common.security.auth.YibanOAuth;
import cn.edu.upc.yb.common.security.model.App;
import cn.edu.upc.yb.common.security.model.AppRepository;
import cn.edu.upc.yb.common.security.model.UPCYbUserFactory;
import cn.edu.upc.yb.common.security.service.JwtAuthenticationRequest;
import cn.edu.upc.yb.common.security.service.JwtAuthenticationResponse;
import cn.edu.upc.yb.common.security.service.JwtTokenUtil;
import cn.edu.upc.yb.common.security.service.JwtUser;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.mobile.device.Device;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class AuthenticationRestController {

    private final Log logger = LogFactory.getLog(this.getClass());

    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AppRepository appRepository;

    @Autowired
    private UPCYbUserFactory upcYbUserFactory;

    @RequestMapping(value = "${jwt.route.authentication.path}", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtAuthenticationRequest authenticationRequest, Device device) throws AuthenticationException {

        App app = appRepository.findFirstByAppname(authenticationRequest.getAppname());
        if (app == null) {
            return ResponseEntity.ok(new ErrorReporter(1, "未找到应用" + authenticationRequest.getAppname()));
        }

        YibanOAuth yibanOAuth = new YibanOAuth(authenticationRequest.getVq(), app);
        yibanOAuth.dealYibanOauth();
        if (yibanOAuth.isHasError() == false) {
            upcYbUserFactory.createUser(yibanOAuth.getYibanBasicUserInfo());
            String ybid = String.valueOf(yibanOAuth.getYibanBasicUserInfo().visit_user.userid);
            String ybtocken = yibanOAuth.getYibanBasicUserInfo().visit_oauth.access_token;
            final JwtUser userDetails = (JwtUser) userDetailsService.loadUserByUsername(ybid);
            final String token = jwtTokenUtil.generateToken(userDetails, ybtocken,authenticationRequest.getAppname(), device);
            return ResponseEntity.ok(new JwtAuthenticationResponse(token));
        } else {
            return ResponseEntity.ok(new ErrorReporter(2, "解析vq失败"));
        }
    }

    @RequestMapping(value = "${jwt.route.authentication.refresh}", method = RequestMethod.GET)
    public ResponseEntity<?> refreshAndGetAuthenticationToken(HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        String userid = jwtTokenUtil.getYBidFromTocken(token);
        JwtUser user = (JwtUser) userDetailsService.loadUserByUsername(userid);

        if (jwtTokenUtil.canTokenBeRefreshed(token, user.getLastPasswordResetDate())) {
            String refreshedToken = jwtTokenUtil.refreshToken(token);
            return ResponseEntity.ok(new JwtAuthenticationResponse(refreshedToken));
        } else {
            return ResponseEntity.badRequest().body(null);
        }
    }

}
