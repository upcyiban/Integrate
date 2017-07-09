package cn.edu.upc.yb.common.ybapi;

import cn.edu.upc.yb.common.dto.ErrorReporter;
import cn.edu.upc.yb.common.security.service.JwtTokenUtil;
import cn.edu.upc.yb.common.service.QueryService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * Created by fang on 2017/7/8.
 */
@Service
public class OrganTopic {

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

    public Object getOrganTopic(String token,String organ_uid) throws IOException {


        String ybtoken = jwtTokenUtil.getYbaccessToken(token);
        String queryString = "access_token=" + ybtoken+ "&organ_uid=" + organ_uid;
        String result = queryService.getYbApi("group/organ_topic", queryString);
        Gson gson = new Gson();
        System.out.println(result);
        try {
            OrganTopic.OrganTopicInfo organTopicInfo = gson.fromJson(result, OrganTopic.OrganTopicInfo.class);
            return organTopicInfo;
        } catch (Exception e) {
            return new ErrorReporter(1, "请求失败");
        }


    }

    class OrganTopicInfo {

        String status;

        public class info {


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




