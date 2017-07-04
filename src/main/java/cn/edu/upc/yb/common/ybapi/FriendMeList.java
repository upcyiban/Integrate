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
public class FriendMeList {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private QueryService queryService;


    /**
     * 易班https://openapi.yiban.cn/friend/me_list接口封装
     *
     * @param token upcyiban token
     * @return
     */
    public Object getFriendMeList(String token ,int page ,int count ) throws IOException {
        String ybtoken = jwtTokenUtil.getYbaccessToken(token);
        String queryString = "access_token=" + ybtoken + "&page=" + page + "&count=" + count;
        String result = queryService.getYbApi("friend/me_list", queryString);
        Gson gson = new Gson();
        System.out.println(result);
        try {
            FriendMeList.FriendsList friendsList = gson.fromJson(result,FriendMeList.FriendsList.class);
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
            String yb_useractive;
        }
        public String num;
    }

}
