package cn.edu.upc.yb.common.ybapi;

import cn.edu.upc.yb.common.dto.ErrorReporter;
import cn.edu.upc.yb.common.security.service.JwtTokenUtil;
import cn.edu.upc.yb.common.service.QueryService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
@Service
public class ShareSendComment {
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private QueryService queryService;

    /**
     * 易班https://openapi.yiban.cn/share/send_comment接口封装
     *
     * @param token upcyiban token
     * @return
     */
    public Object getShareSendComment(String token,String feeds_id,String content) throws IOException {
        String ybtoken = jwtTokenUtil.getYbaccessToken(token);
        String queryString = "access_token=" + ybtoken + "feeds_id" + feeds_id + "content" + content ;
        String result = queryService.getYbApi("share/send_comment", queryString);
        Gson gson = new Gson();
        System.out.println(result);
        try {
            ShareSendComment.ShareSendCommentList shareSendCommentList = gson.fromJson(result,ShareSendComment.ShareSendCommentList.class);
            return shareSendCommentList;
        }catch (Exception e){
            return new ErrorReporter(1,"请求失败");
        }
    }
    class ShareSendCommentList{
        public String status;
        String info;
    }
}
