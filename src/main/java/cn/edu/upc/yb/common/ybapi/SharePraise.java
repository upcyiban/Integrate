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
public class SharePraise {
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private QueryService queryService;

    /**
     * 易班https://openapi.yiban.cn/share/praise接口封装
     *
     * @param token upcyiban token
     * @return
     */
    public Object getSharePraise(String token , String feeds_id,String action) throws IOException {
        String ybtoken = jwtTokenUtil.getYbaccessToken(token);
        String queryString = "access_token=" + ybtoken + "feeds_id" + feeds_id + "action" + action;
        String result = queryService.getYbApi("share/praise", queryString);
        Gson gson = new Gson();
        System.out.println(result);
        try {
            SharePraise.SharePraiseList sharePraiseList = gson.fromJson(result,SharePraise.SharePraiseList.class);
            return sharePraiseList;
        }catch (Exception e){
            return new ErrorReporter(1,"请求失败");
        }
    }
    class SharePraiseList{
        public String status;
        String info;
    }
}
