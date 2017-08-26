package cn.edu.upc.yb.common.ybapi;

import cn.edu.upc.yb.common.dto.ErrorReporter;
import cn.edu.upc.yb.common.security.service.JwtTokenUtil;
import cn.edu.upc.yb.common.service.QueryService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * Created by zxshane on 2017/7/8.
 */
@Service
public class TopicInfo {
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
    public Object getTopicInfo(String token ,String group_id,String organ_id ,String topic_id) throws IOException {
        String ybtoken = jwtTokenUtil.getYbaccessToken(token);
        String queryString = "access_token=" + ybtoken +"&group_id=" + group_id+ "&topic_id=" + topic_id ;
        String result = queryService.getYbApi("group/topic_info", queryString);
        Gson gson = new Gson();
        System.out.println(result);
        try {
            TopicInfo.TopicInfoList topicInfoList = gson.fromJson(result,TopicInfo.TopicInfoList.class);
            return topicInfoList;
        }catch (Exception e){
            return new ErrorReporter(1,"请求失败");
        }
    }


    class TopicInfoList {

        public String status;

        public class Info {
            String topic_id;
            String topic_title;
            String pub_uid;
            String pub_nick;
            String pub_head;
            String reply_count;
            String topic_content;
            String create_time;
            String reply_time;
        }

    }
}
