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
 * Created by zxshane on 2017/7/8.
 */
@Service
public class TopicComment {
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
    public Object getTopicComment(String token , String group_id, String organ_id,String topic_id) throws IOException {
        String ybtoken = jwtTokenUtil.getYbaccessToken(token);
        String queryString = "access_token=" + ybtoken + "&group_id=" + group_id + "&topic_id=" + topic_id;
        String result = queryService.getYbApi("group/topic_comment", queryString);
        Gson gson = new Gson();
        System.out.println(result);
        try {
            UserMe.UserInfo userInfo = gson.fromJson(result,UserMe.UserInfo.class);
            return userInfo;
        }catch (Exception e){
            return new ErrorReporter(1,"请求失败");
        }
    }


    class CommentList {

        public String status;
        public ArrayList<CommentInfo> info;
        public class CommentInfo {
            String topic_id;
            String comment_id;
            String pub_uid;
            String pub_nick;
            String pub_head;
            String comment_content;
            public ArrayList<Reply> reply_list;
            public class Reply {

                String topic_id;
                String reply_id;
                String comment_id;
                String pub_uid;
                String pub_nick;
                String pub_head;
                String comment_content;
                String create_time;
            }
            String create_time;

        }

    }
}
