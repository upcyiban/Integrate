package cn.edu.upc.yb.common.service;

import cn.edu.upc.yb.common.security.service.JwtTokenUtil;
import cn.edu.upc.yb.common.ybapi.UserRealMe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Service
public class UserService {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    private UserRealMe userRealMe;

    public Object getStuId(HttpServletRequest request) throws IOException {
        String authToken = request.getParameter(this.tokenHeader);
        UserRealMe.UserRealMeInfo userRealMeInfo = (UserRealMe.UserRealMeInfo) userRealMe.getUserRealMe(authToken);
        return userRealMeInfo.info.yb_studentid;
    }


    public Object getStuName(HttpServletRequest request) throws IOException {
        String authToken = request.getParameter(this.tokenHeader);
        UserRealMe.UserRealMeInfo userRealMeInfo = (UserRealMe.UserRealMeInfo) userRealMe.getUserRealMe(authToken);
        return userRealMeInfo.info.yb_realname;
    }
}
