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
public class InfoShare {
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private QueryService queryService;

    /**
     * 易班https://openapi.yiban.cn/share/info_share接口封装
     *
     * @param token upcyiban token
     * @return
     */

    public Object  getInfoShare(String  token ,String feeds_id ) throws IOException {

        String ybtoken = jwtTokenUtil.getYbaccessToken(token);
        String queryString = "access_token=" + ybtoken + "feeds_id=" + feeds_id;
        String result = queryService.getYbApi("share/info_share", queryString);
        Gson gson = new Gson();
        System.out.println(result);

        try {
            InfoShare.InfoShareList infoShareList = gson.fromJson(result,InfoShare.InfoShareList.class);
            return infoShareList;
        }catch (Exception e){
            return new ErrorReporter(1,"请求失败");
        }



    }
    class InfoShareList{
        public String status;
        public ArrayList<InfoShareInfo> info;
        public class InfoShareInfo{
            String yb_feedid;
            String yb_content;
            String yb_userid;
            String yb_username;
            String yb_usernick;
            String yb_userhead;
            String yb_sendtime;
            String yb_goodnum;
            String yb_pitynum;
            String yb_replynum;
            public ArrayList<Reply> yb_replylist;
            public class Reply{
                String reply_commid;
                String reply_userid;
                String reply_username;
                String reply_usernick;
                String reply_userhead;
                String reply_content;
                String reply_sendtime;
            }


        }
        public ArrayList<YbShareInfo> yb_share;
        public class YbShareInfo{
            String share_title;
            String share_content;
            String share_href;
            String share_image;
            String share_source;
        }

    }
}
