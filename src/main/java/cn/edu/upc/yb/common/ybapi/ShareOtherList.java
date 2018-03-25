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
public class ShareOtherList {
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private QueryService queryService;

    /**
     * 易班https://openapi.yiban.cn/share/other_list接口封装
     *
     * @param token upcyiban token
     * @return
     */
    public Object getShareOtherList(String token) throws IOException {
        String ybtoken = jwtTokenUtil.getYbaccessToken(token);
        String queryString = "access_token=" + ybtoken ;
        String result = queryService.getYbApi("share/other_list", queryString);
        Gson gson = new Gson();
        System.out.println(result);
        try {
            ShareOtherList.ShareOtherListList shareOtherListList = gson.fromJson(result,ShareOtherList.ShareOtherListList.class);
            return shareOtherListList;
        }catch (Exception e){
            return new ErrorReporter(1,"请求失败");
        }
    }
    class ShareOtherListList {

        public String status;
        public ArrayList<ShareOtherInfo> info;
        public class ShareOtherInfo{
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
