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
public class ShareMeList {
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private QueryService queryService;

    /**
     * 易班https://openapi.yiban.cn/share/me_list接口封装
     *
     * @param token upcyiban token
     * @return
     */
    public Object getShareMeList(String token,String userid) throws IOException {
        String ybtoken = jwtTokenUtil.getYbaccessToken(token);
        String queryString = "access_token=" + ybtoken + "userid=" + userid ;
        String result = queryService.getYbApi("share/me_list", queryString);
        Gson gson = new Gson();
        System.out.println(result);
        try {
            ShareMeList.ShareMeListList shareMeListList = gson.fromJson(result,ShareMeList.ShareMeListList.class);
            return shareMeListList;
        }catch (Exception e){
            return new ErrorReporter(1,"请求失败");
        }
    }


    class ShareMeListList {

        public String status;
        public ArrayList<ShareMeInfo> info;
        public class ShareMeInfo{
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
            public ArrayList<Share> yb_share;
            public class Share{
                String share_title;
                String share_content;
                String share_href;
                String share_image;
                String share_source;
            }
            String page;

        }

    }
}
