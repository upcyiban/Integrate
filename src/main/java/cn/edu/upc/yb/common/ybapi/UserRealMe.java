package cn.edu.upc.yb.common.ybapi;

import cn.edu.upc.yb.common.dto.ErrorReporter;
import cn.edu.upc.yb.common.security.service.JwtTokenUtil;
import cn.edu.upc.yb.common.service.QueryService;
import com.google.gson.Gson;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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

    private final Log logger = LogFactory.getLog(this.getClass());

    /**
     * 易班https://openapi.yiban.cn/user/real_me接口封装
     *
     * @param token upcyiban token
     * @return
     */
    public Object getUserRealMe(String token) throws IOException {
        String ybtoken = jwtTokenUtil.getYbaccessToken(token);
        logger.info("ybtoken: " + ybtoken);
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


    public  class UserRealMeInfo {
        public   String status;

        public Info info;

        public   class Info{
            public  String yb_userid;
            public String yb_username;
            public String yb_usernick;
            public String yb_sex;
            public String yb_money;
            public String yb_exp;
            public String yb_userhead;
            public String yb_schoolid;
            public String yb_schoolname;
            public String yb_realname;
            public  String yb_studentid;
            public String yb_identity;
        }
    }

}
