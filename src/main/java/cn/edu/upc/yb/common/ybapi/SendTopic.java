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
public class SendTopic {
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private QueryService queryService;

    /**
     * 易班https://openapi.yiban.cn/group/send_topic接口封装
     *
     * @param token upcyiban token
     * @return
     */
    public Object getSendTopic(String token , String group_id, String topic_title, String topic_content) throws IOException {
        String ybtoken = jwtTokenUtil.getYbaccessToken(token);
        String queryString = "access_token=" + ybtoken + "&group_id=" + group_id + "&topic_title=" + topic_title + "topic_content" +topic_content;
        String result = queryService.postYbApi("group/send_topic", queryString);
        Gson gson = new Gson();
        System.out.println(result);
        try {
            SendTopic.SendTopicList sendTopicList = gson.fromJson(result,SendTopic.SendTopicList.class);

            return sendTopicList;
        }catch (Exception e){
            return new ErrorReporter(1,"请求失败");
        }
    }


    class SendTopicList {

        public String status;

        public class Info {
            String status;

        }

    }
}
