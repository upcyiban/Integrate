package cn.edu.upc.yb.common.ybapi;

import cn.edu.upc.yb.common.dto.ErrorReporter;
import cn.edu.upc.yb.common.security.service.JwtTokenUtil;
import cn.edu.upc.yb.common.service.QueryService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * Created by lylllcc on 2017/6/22.
 */
@Service
public class UserMe {


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
    public Object getUserMe(String token) throws IOException {
        String ybtoken = jwtTokenUtil.getYbaccessToken(token);
        String queryString = "access_token=" + ybtoken;
        String result = queryService.getYbApi("user/me", queryString);
        Gson gson = new Gson();
        System.out.println(result);
        try {
            UserInfo userInfo = gson.fromJson(result,UserInfo.class);
            return userInfo;
        }catch (Exception e){
            return new ErrorReporter(1,"请求失败");
        }
    }


    class UserInfo {

        public String status;

        public class Info {
            String yb_userid;
            String yb_username;
            String yb_usernick;
            String yb_sex;
            String yb_money;
            String yb_exp;
            String yb_userhead;
            String yb_schoolid;
            String yb_schoolname;
        }

    }
}


