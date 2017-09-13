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
public class UserOther {
  private final Log logger = LogFactory.getLog(this.getClass());

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private QueryService queryService;

    /**
     * 易班https://openapi.yiban.cn/user/other接口封装
     *
     * @param token upcyiban token
     * @return
     */
    public Object getUserOther(String  token)  throws IOException{
        String ybtoken = jwtTokenUtil.getYbaccessToken(token);
        String ybid = jwtTokenUtil.getYBidFromTocken(token);
        String queryString = "access_token=" + ybtoken + "&yb_userid=" + ybid;

        String result = queryService.getYbApi("user/other", queryString);
        Gson gson = new Gson();
        logger.info(result);

        try{
            UserOtherInfo userOtherInfo = gson.fromJson(result,UserOther.UserOtherInfo.class);
            return userOtherInfo;
        } catch (Exception e){
            return new ErrorReporter(1,"请求失败");
        }

    }

    class UserOtherInfo {

        public  String  stauts;

        public class  info {

           String yb_userid;
           String yb_username;
           String yb_usernick;
           String yb_sex;
           String yb_userhead;

           String yb_schoolid;
           String yb_schoolname;


        }


    }
}
