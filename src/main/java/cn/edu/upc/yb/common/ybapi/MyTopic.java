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
public class MyTopic {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private QueryService queryService;

    /**
     * 易班https://openapi.yiban.cn/group/my_topic接口封装
     *
     * @param token upcyiban token
     * @return
     */

    public  Object getMyTopic(String   token,String groupId)  throws IOException {

        String ybtoken = jwtTokenUtil.getYbaccessToken(token);
        String queryString = "access_token=" + ybtoken+  "&group_id=" + groupId;
        String result = queryService.getYbApi("group/my_topic", queryString);
        Gson gson = new Gson();
        System.out.println(result);
        try {
            MyTopic. MyTopicInfo  myTopicInfo = gson.fromJson(result, MyTopic. MyTopicInfo.class);
            return  myTopicInfo;
        }catch (Exception e){
            return new ErrorReporter(1,"请求失败");
        }


    }

    public void getOrganTopic(String token, String s) {
    }

    class  MyTopicInfo{

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

/*
成功返回：

{
  "status":"success",
  "info":{
    "list":[
      {
        "topic_id":"话题ID",
        "topic_title":"话题标题",
        "pub_uid":"发表者用户ID",
        "pub_nick":"发表者用户昵称",
        "pub_head":"发表者用户头像",
        "reply_count":"回复评论数",
        "topic_content":"话题内容",
        "create_time":"发表时间",
        "reply_time":"最后评论时间"
      },
      ......
    ]
  }
} */

}


