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
public class UserVerifyMe {


    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private QueryService queryService;

    /**
     * 易班https://openapi.yiban.cn/user/verify_me接口封装
     *
     * @param token upcyiban token
     * @return
     */
    public Object getUserVerifyMe(String token) throws IOException {
        String ybtoken = jwtTokenUtil.getYbaccessToken(token);
        String queryString = "access_token=" + ybtoken;
        String result = queryService.getYbApi("user/verify_me", queryString);
        Gson gson = new Gson();
        System.out.println(result);
        try {
            UserVerifyMeInfo userVerifyMeInfo = gson.fromJson(result,UserVerifyMeInfo.class);
            return userVerifyMeInfo;
        }catch (Exception e){
            return new ErrorReporter(1,"请求失败");
        }
    }

    class UserVerifyMeInfo{

        public  String status;
        public class Info{
            String yb_userid;
            String yb_realname;
            String yb_schoolid;
            String yb_schoolname;
            String yb_studentid;
            String yb_examid;
            String yb_admissionid;
            String yb_employid;
        }
    }


}
