package cn.edu.upc.yb.common.ybapi;

import cn.edu.upc.yb.common.dto.ErrorReporter;
import cn.edu.upc.yb.common.security.service.JwtTokenUtil;
import cn.edu.upc.yb.common.service.QueryService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by 17797 on 2017/7/4.
 */
public class FriendCheck {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private QueryService queryService;

    /**
     * 易班https://openapi.yiban.cn/friend/check接口封装
     *
     * @param token upcyiban token
     * @return
     */
    public Object getFriendCheck(String token,int yb_friend_uid) throws IOException {
        String ybtoken = jwtTokenUtil.getYbaccessToken(token);
        String queryString = "access_token=" + ybtoken + "&yb_friend_uid" + yb_friend_uid;
        String result = queryService.getYbApi("friend/check", queryString);
        Gson gson = new Gson();
        System.out.println(result);
        try {
           FriendCheck.Check check = gson.fromJson(result,FriendCheck.Check.class);
            return check;
        }catch (Exception e){
            return new ErrorReporter(1,"请求失败");
        }
    }

    class Check {
        public String status;
        public String info;
    }
    /*
    "status":"success",
    "info":"关系结果"
    返回状态说明：true-是、false-否
    */
}
