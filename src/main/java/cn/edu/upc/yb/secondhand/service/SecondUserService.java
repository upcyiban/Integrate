package cn.edu.upc.yb.secondhand.service;

import cn.edu.upc.yb.common.ybapi.UserMe;

import cn.edu.upc.yb.common.security.service.JwtTokenUtil;
import cn.edu.upc.yb.secondhand.dto.Message;
import cn.edu.upc.yb.secondhand.model.SecondUser;
import cn.edu.upc.yb.secondhand.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.repository.query.Param;
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
        SecondUser user=userRepository.findByUserid(yibanId);
        return (user!=null);
    }

    /*
    获取用户基本信息
     */
    public Object userInfo(HttpServletRequest request){
        String token=request.getParameter(this.tokenHeader);
        int yibanId=Integer.valueOf(jwtTokenUtil.getYBidFromTocken(token));
        SecondUser user=userRepository.findByUserid(yibanId);
        if (user==null){
            return new Message(0,"null user");
        }
        return user;
    }

    /**
     * 注册用户信息
     * @param request
     * @param Qq
     * @param phone
     * @param wchat
     * @param email
     * @return
     */
    public Object addUser(HttpServletRequest request,String Qq,String phone,String wchat,String email){
        SecondUser user =new SecondUser();
        String token=request.getParameter(this.tokenHeader);
        int yibanId=Integer.valueOf(jwtTokenUtil.getYBidFromTocken(token));
        if (userRepository.findByUserid(yibanId)!=null){
            return new Message(0,"SecondUser already exists");
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
        user.setPhone(phone);
        user.setEmail(email);
        user.setPhone(wchat);
        userRepository.save(user);
        return user;
    }

    /**
     * 更新用户联系方式信息
     * @param request
     * @param qq
     * @param phone
     * @param wchat
     * @param email
     * @return
     */
    public Object updateUserInfo(HttpServletRequest request, String  qq, String phone, String wchat, String email){
        String token = request.getParameter(this.tokenHeader);
        int yibanId =  Integer.valueOf(jwtTokenUtil.getYBidFromTocken(token));
        SecondUser secondUser = userRepository.findByUserid(yibanId);
        if (secondUser == null){
            return new Message(0,"SecondUser null");
        }
        secondUser.setQq(qq);
        secondUser.setPhone(phone);
        secondUser.setWchat(wchat);
        secondUser.setEmail(email);
        return userRepository.save(secondUser);
    }

}
