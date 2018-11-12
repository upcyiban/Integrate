package cn.edu.upc.yb.common.ybapi;

import cn.edu.upc.yb.common.dto.ErrorReporter;
import cn.edu.upc.yb.common.security.service.JwtTokenUtil;
import cn.edu.upc.yb.common.service.QueryService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class SendShare {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private QueryService queryService;

    /**
     * 易班https://openapi.yiban.cn/share/send_share接口封装
     *
     * @param token upcyiban token
     * @return
     */
    public Object getSendShare(String token,String content,String share_title,String share_url) throws IOException {
        String ybtoken = jwtTokenUtil.getYbaccessToken(token);
        String queryString = "access_token=" + ybtoken + "content=" + content + "share_title" + share_title + "share_url" + share_url ;
        String result = queryService.postYbApi("share/send_share", queryString);
        Gson gson = new Gson();
        System.out.println(result);
        try {
            SendShare.SendShareList sendShareList = gson.fromJson(result,SendShare.SendShareList.class);
            return sendShareList;
        }catch (Exception e){
            return new ErrorReporter(1,"请求失败");
        }



    }
    class SendShareList{
        public String status;
           String info;
    }


}
