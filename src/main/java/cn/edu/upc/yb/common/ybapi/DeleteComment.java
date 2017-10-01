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
public class DeleteComment {
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private QueryService queryService;

    /**
     * 易班https://openapi.yiban.cn/group/delete_comment接口封装
     *
     * @param token upcyiban token
     * @return
     */
    public Object getDeleteComment(String token,String group_id,String organ_id, String topic_id,String comment_id) throws IOException {
        String ybtoken = jwtTokenUtil.getYbaccessToken(token);
        String queryString = "access_token=" + ybtoken + "&group_id=" + group_id + "&topic_id=" + topic_id + "&comment_id=" + comment_id;
        String result = queryService.getYbApi("group/delete_comment", queryString);
        Gson gson = new Gson();
        System.out.println(result);
        try {
            DeleteComment.DeleteCommentList deleteCommentList = gson.fromJson(result,DeleteComment.DeleteCommentList.class);
            return deleteCommentList;
        }catch (Exception e){
            return new ErrorReporter(1,"请求失败");
        }
    }


    class DeleteCommentList{

        public String status;

        public class Info {
            String status;

        }

    }
}
