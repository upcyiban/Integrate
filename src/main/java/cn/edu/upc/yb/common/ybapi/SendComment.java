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
public class SendComment {
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
    public Object getSendComment(String token, String topic_id,String comment_content) throws IOException {
        String ybtoken = jwtTokenUtil.getYbaccessToken(token);
        String queryString = "access_token=" + ybtoken + "&topic_id=" + topic_id + "&comment_content=" + comment_content;
        String result = queryService.postYbApi("group/send_comment", queryString);
        Gson gson = new Gson();
        System.out.println(result);
        try {
            SendComment.SendCommentList sendCommentList = gson.fromJson(result,SendComment.SendCommentList.class);
            return sendCommentList;
        }catch (Exception e){
            return new ErrorReporter(1,"请求失败");
        }
    }


    class SendCommentList {

        public String status;

        public class Info {
            String status;

        }

    }
}
