package cn.edu.upc.yb.common.security.controller;

import cn.edu.upc.yb.common.dto.ErrorReporter;
import cn.edu.upc.yb.common.dto.SwaggerParameter;
import cn.edu.upc.yb.common.security.auth.YibanOAuth;
import cn.edu.upc.yb.common.security.model.App;
import cn.edu.upc.yb.common.security.model.AppRepository;
import cn.edu.upc.yb.common.security.model.UPCYbUserFactory;
import cn.edu.upc.yb.common.security.service.JwtAuthenticationRequest;
import cn.edu.upc.yb.common.security.service.JwtAuthenticationResponse;
import cn.edu.upc.yb.common.security.service.JwtTokenUtil;
import cn.edu.upc.yb.common.security.service.JwtUser;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.mobile.device.Device;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
public class AuthenticationRestController {

    private final Log logger = LogFactory.getLog(this.getClass());

    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AppRepository appRepository;

    @Autowired
    private UPCYbUserFactory upcYbUserFactory;

    @ApiOperation(value = "授权", notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query",name = "appName", value = "应用的名字", required = true,dataType = "String"),
            @ApiImplicitParam(paramType = "query",name = "vq",value = "vq",required = true,dataType = "String"),
            @ApiImplicitParam(paramType = "query",name = "device",value = "设备的名字",required = true,dataType = "String")})


    @RequestMapping(value = "${jwt.route.authentication.path}", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(String appName,String vq, Device device) throws AuthenticationException {

        logger.info("应用:" + appName  + " 正在授权！");

        App app = appRepository.findFirstByAppname(appName);
        if (app == null) {
            return ResponseEntity.ok(new ErrorReporter(1, "未找到应用" + appName));
        }

        YibanOAuth yibanOAuth = new YibanOAuth(vq, app);
        yibanOAuth.dealYibanOauth();
        if (yibanOAuth.isHasError() == false) {
            upcYbUserFactory.createUser(yibanOAuth.getYibanBasicUserInfo());
            String ybid = String.valueOf(yibanOAuth.getYibanBasicUserInfo().visit_user.userid);
            String ybtocken = yibanOAuth.getYibanBasicUserInfo().visit_oauth.access_token;
            logger.info("ybtocken: " + ybtocken);
            final JwtUser userDetails = (JwtUser) userDetailsService.loadUserByUsername(ybid);
            final String token = jwtTokenUtil.generateToken(userDetails, ybtocken,appName, device);
            logger.info("发放token：" + token);
            return ResponseEntity.ok(new JwtAuthenticationResponse(token));
        } else {
            return ResponseEntity.ok(new ErrorReporter(2, "解析vq失败，可能是由于密钥长度不匹配"));
        }
    }

    @ApiOperation(value = "刷新token", notes = "")
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

    @GetMapping("/token")
    public String genToken(Device device){
        final JwtUser userDetails = (JwtUser) userDetailsService.loadUserByUsername("5831449");
        final String token = jwtTokenUtil.generateToken(userDetails, "","commit", device);
        return token;

    }

}
