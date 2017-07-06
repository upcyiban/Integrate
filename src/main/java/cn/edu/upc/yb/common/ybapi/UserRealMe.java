package cn.edu.upc.yb.common.ybapi;

import cn.edu.upc.yb.common.dto.ErrorReporter;
import cn.edu.upc.yb.common.security.service.JwtTokenUtil;
import cn.edu.upc.yb.common.service.QueryService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * Created by idea on 2017/7/2.
 */
@Service
public class UserRealMe {


    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private QueryService queryService;

    /**
     * 易班https://openapi.yiban.cn/user/me接口封装
     *
     * @param token upcyiban token
     * @return
     */
    public Object getUserRealMe(String token) throws IOException {
        String ybtoken = jwtTokenUtil.getYbaccessToken(token);
        String queryString = "access_token=" + ybtoken;
        String result = queryService.getYbApi("user/real_me", queryString);
        Gson gson = new Gson();
        System.out.println(result);
        try {
            UserRealMeInfo  userRealMeInfo = gson.fromJson(result,UserRealMeInfo.class);
            return userRealMeInfo;
        }catch (Exception e){
            return new ErrorReporter(1,"请求失败");
        }
    }


    class UserRealMeInfo {
        public  String status;

        public class info{




            String yb_userid;
            String yb_username;
            String yb_usernick;
            String yb_sex;
            String yb_money;
            String yb_exp;
            String yb_userhead;
            String yb_schoolid;
            String yb_schoolname;
            String yb_realname;
            String yb_studentid;
            String yb_identity;
        }
    }

}
