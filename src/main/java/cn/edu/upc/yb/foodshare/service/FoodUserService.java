package cn.edu.upc.yb.foodshare.service;

/**
 * Created By Kazusa in 2018/7/6 16:56
 */
import cn.edu.upc.yb.common.security.service.JwtTokenUtil;
import cn.edu.upc.yb.common.ybapi.UserMe;
import cn.edu.upc.yb.foodshare.dto.Message;
import cn.edu.upc.yb.foodshare.model.FoodUser;
import cn.edu.upc.yb.foodshare.repository.FoodArticleRepository;
import cn.edu.upc.yb.foodshare.repository.FoodUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;

@Service
public class FoodUserService {

    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserMe userMe;

    @Autowired
    private FoodUserRepository foodUserRepository;

    @Autowired
    private FoodArticleRepository foodArticleRepository;

    //判断用户是否存在
    public Boolean isExist(String token){
        int yibanId = Integer.valueOf(jwtTokenUtil.getYBidFromTocken(token));
        FoodUser foodUser = foodUserRepository.findByUserid(yibanId);
        return (foodUser!=null);
    }

    //获取用户基本信息
    public Object userInfo(String token){
        int yibanId = Integer.valueOf(jwtTokenUtil.getYBidFromTocken(token));
        FoodUser foodUser = foodUserRepository.findByUserid(yibanId);
        if(foodUser==null){
            return new Message(0,"用户不存在！");
        }
        else return foodUser;
    }
    // 第一次登陆用户，注册用户信息
    public Object addUser(String token){
        FoodUser foodUser = new FoodUser();
        int yibanId=Integer.valueOf(jwtTokenUtil.getYBidFromTocken(token));
        if(foodUserRepository.findByUserid(yibanId)!=null){
            return new Message(0,"用户已存在");
        }
        String access_token=jwtTokenUtil.getYbaccessToken(token);
        UserMe.UserInfo userInfo;
        try {
            userInfo=(UserMe.UserInfo)userMe.getUserMe(access_token);
        }catch (Exception e){
            e.printStackTrace();
            return new Message(0,"获取易班信息失败");
        }
        foodUser.setUserid(Integer.valueOf(userInfo.info.yb_userid));
        foodUser.setUsername(userInfo.info.yb_username);
        foodUser.setYbhead(userInfo.info.yb_userhead);
        foodUser.setUsersex(userInfo.info.yb_sex);
        foodUser.setCreatetime(new Date());
        foodUser.setLasttime(new Date());
        foodUserRepository.save(foodUser);
        return foodUser;
    }

    //获取易班id
    public int getuserid(String token){
        return Integer.valueOf(jwtTokenUtil.getYBidFromTocken(token));
    }

    //经过审核的菜品判断,有返回true，没有返回false
    public Boolean ischeck(String token){
        int yibanId=Integer.valueOf(jwtTokenUtil.getYBidFromTocken(token));
        int[] states ={0,-2};
        Long is = foodArticleRepository.countByUseridAndIscheckAndStateIn(yibanId,0,states);
        return (is!=0);
    }

}
