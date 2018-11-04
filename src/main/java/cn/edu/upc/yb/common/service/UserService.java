package cn.edu.upc.yb.common.service;

import cn.edu.upc.yb.common.security.service.JwtTokenUtil;
import cn.edu.upc.yb.common.ybapi.UserMe;
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
    @Autowired
    private  UserMe userMe;

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

    public Object getStuIdByAccessToken(String access_token) throws IOException {

        UserMe.UserInfo userInfo = (UserMe.UserInfo) userMe.getUserMe(access_token);
        System.out.println(userInfo.info.yb_userid);
        return userInfo.info.yb_schoolid;
    }

    public Object getStuInfoByAccessToken(String access_token) throws IOException{

        UserRealMe.UserRealMeInfo userInfo = (UserRealMe.UserRealMeInfo) userRealMe.getUserInfoByAccessToken(access_token);
        System.out.println(userInfo.info.yb_studentid);
        return userInfo.info;
    }
}
