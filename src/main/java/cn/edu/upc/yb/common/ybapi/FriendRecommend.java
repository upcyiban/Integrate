package cn.edu.upc.yb.common.ybapi;

import cn.edu.upc.yb.common.dto.ErrorReporter;
import cn.edu.upc.yb.common.security.service.JwtTokenUtil;
import cn.edu.upc.yb.common.service.QueryService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by 17797 on 2017/7/4.
 */
@Service
public class FriendRecommend {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private QueryService queryService;



    /**
     * 易班https://openapi.yiban.cn/friend/recommend接口封装
     *  https://openapi.yiban.cn/pay/yb_wx
     * @param token upcyiban token
     * @return
     */

    public Object getFriendRecommend(String token,int count ) throws IOException {
        String ybtoken = jwtTokenUtil.getYbaccessToken(token);
        String queryString = "access_token=" + ybtoken + "&count=" + count;
        String result = queryService.getYbApi("friend/recommend", queryString);
        Gson gson = new Gson();
        System.out.println(result);
        try {
            FriendRecommend.FriendsList friendsList = gson.fromJson(result,FriendRecommend.FriendsList.class);
            return friendsList;
        }catch (Exception e){
            return new ErrorReporter(1,"请求失败");
        }
    }

    class FriendsList {
        public String status;
        public ArrayList<FriendInfo> info;
        public class FriendInfo {
            String yb_userid;
            String yb_username;
            String yb_usernick;
            String yb_sex;
            String  yb_userhead;
        }
    }

}
