package cn.edu.upc.yb.secondhand.service;

import cn.edu.upc.yb.common.ybapi.UserMe;

import cn.edu.upc.yb.common.security.service.JwtTokenUtil;
import cn.edu.upc.yb.secondhand.dto.Message;
import cn.edu.upc.yb.secondhand.model.User;
import cn.edu.upc.yb.secondhand.repository.UserRepository;
import jdk.nashorn.internal.parser.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class UserService {

    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserRepository userRepository;



    /*
    判断用户是否存在
     */
    public Boolean isExist(HttpServletRequest request){
        String token=request.getParameter(this.tokenHeader);
        int yibanId=Integer.valueOf(jwtTokenUtil.getYBidFromTocken(token));
        User user=userRepository.findByUserid(yibanId);
        return (user!=null);
    }

    /*
    获取用户基本信息
     */
    public Object userInfo(HttpServletRequest request){
        String token=request.getParameter(this.tokenHeader);
        int yibanId=Integer.valueOf(jwtTokenUtil.getYBidFromTocken(token));
        User user=userRepository.findByUserid(yibanId);
        if (user==null){
            return new Message(0,"null user");
        }
        return user;
    }

    public Object addUser(HttpServletRequest request,String email,String phone){
        UserMe userMe=new UserMe();
        User user =new User();
        String token=request.getParameter(this.tokenHeader);
        int yibanId=Integer.valueOf(jwtTokenUtil.getYBidFromTocken(token));
        String yibanName=jwtTokenUtil.getUsernameFromToken(token);
        String access_token=jwtTokenUtil.getYbaccessToken(token);
        UserMe.UserInfo userInfo;
        try {
            userInfo=(UserMe.UserInfo)userMe.getUserMe(access_token);
        }catch (Exception e){
            return new Message(0,"获取易班信息失败");
        }
        String ybhead=userInfo.info.yb_userhead;
        String ybnick=userInfo.info.yb_sex;

        System.out.println(ybhead);

        user.setEmail(email);
        user.setPhone(phone);
        userRepository.save(user);
        return user;
    }
}
