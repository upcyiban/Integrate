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
public class SecondUserService {

    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMe userMe;

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

    /*
    注册用户信息
     */
    public Object addUser(HttpServletRequest request,String Qq){
        User user =new User();
        String token=request.getParameter(this.tokenHeader);
        int yibanId=Integer.valueOf(jwtTokenUtil.getYBidFromTocken(token));
        if (userRepository.findByUserid(yibanId)!=null){
            return new Message(0,"User already exists");
        }
        String access_token=jwtTokenUtil.getYbaccessToken(token);
        UserMe.UserInfo userInfo;
        try {
            userInfo=(UserMe.UserInfo)userMe.getUserMe(access_token);
        }catch (Exception e){
            e.printStackTrace();
            return new Message(0,"获取易班信息失败");
        }
        user.setUserid(Integer.valueOf(userInfo.info.yb_userid));
        user.setUsername(userInfo.info.yb_username);
        user.setYbhead(userInfo.info.yb_userhead);
        user.setUsernick(userInfo.info.yb_sex);
        user.setQq(Qq);
        userRepository.save(user);
        return user;
    }
    /*
    增加用户手机号
     */
    public Object addphone(HttpServletRequest request,String phone){
        String token=request.getParameter(this.tokenHeader);
        int yibanId=Integer.valueOf(jwtTokenUtil.getYBidFromTocken(token));
        User user=userRepository.findByUserid(yibanId);
        if (user==null){
            return new Message(0,"User null");
        }
        user.setPhone(phone);
        return userRepository.save(user);
    }

    /*
    增加用户wchat
     */
    public Object addwchat(HttpServletRequest request,String wchat){
        String token=request.getParameter(this.tokenHeader);
        int yibanId=Integer.valueOf(jwtTokenUtil.getYBidFromTocken(token));
        User user=userRepository.findByUserid(yibanId);
        if (user==null){
            return new Message(0,"User null");
        }
        user.setWchat(wchat);
        return userRepository.save(user);
    }

    /*
    增加用户email
     */
    public Object addemail(HttpServletRequest request,String email){
        String token=request.getParameter(this.tokenHeader);
        int yibanId=Integer.valueOf(jwtTokenUtil.getYBidFromTocken(token));
        User user=userRepository.findByUserid(yibanId);
        if (user==null){
            return new Message(0,"User null");
        }
        user.setEmail(email);
        return userRepository.save(user);
    }

}
