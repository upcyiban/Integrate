package cn.edu.upc.yb.common.ybapi;

import cn.edu.upc.yb.common.dto.ErrorReporter;
import cn.edu.upc.yb.common.security.service.JwtTokenUtil;
import cn.edu.upc.yb.common.service.QueryService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * Created by idea on 2017/7/5.
 */
@Service
public class IsReal {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private QueryService queryService;

    /**
     * 易班https://openapi.yiban.cn/user/is_real接口封装
     *
     * @param token upcyiban token
     * @return
     */

    public Object  getIsReal(String  token ) throws IOException {

        String ybtoken = jwtTokenUtil.getYbaccessToken(token);
        String yb_userid = jwtTokenUtil.getYBidFromTocken(token);

        String queryString = "access_token=" + ybtoken + "&yb_userid=" + yb_userid;
        String result = queryService.getYbApi("user/is_real", queryString);
        Gson gson = new Gson();
        System.out.println(result);

        try {
        Check check    = gson.fromJson(result,Check.class);
            return check;
        }catch (Exception e){
            return new ErrorReporter(1,"请求失败");
        }



    }

    class Check {
        String  status;
        String info;
    }
/*
{
  "status":"success",
  "info":"验证结果"
}
//返回状态说明：true-是、false-否
 */

}
