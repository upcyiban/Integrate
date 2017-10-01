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
public class HotTopic {
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private QueryService queryService;

    /**
     * 易班https://openapi.yiban.cn/group/hot_topic接口封装
     *
     * @param token upcyiban token
     * @return
     */
    public Object getHotTopic(String token) throws IOException {
        String ybtoken = jwtTokenUtil.getYbaccessToken(token);
        String queryString = "access_token=" + ybtoken;
        String result = queryService.getYbApi("group/hot_topic", queryString);
        Gson gson = new Gson();
        System.out.println(result);
        try {
            HotTopic.TopicsList topicsList = gson.fromJson(result, HotTopic.TopicsList.class);
            return topicsList;
        }catch (Exception e){
            return new ErrorReporter(1,"请求失败");
        }
    }


    class TopicsList {
        public String status;
        public ArrayList<TopicInfo> info;
        public class TopicInfo {
            String topic_id;
            String topic_title;
            String pub_uid;
            String pub_nic;
            String  pub_head;
            String reply_count;
            String reply_content;
            String create_time;
            String reply_time;
        }
        public String num;
    }


}
