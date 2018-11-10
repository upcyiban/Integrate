package cn.edu.upc.yb.common.ybapi;

import cn.edu.upc.yb.common.dto.ErrorReporter;
import cn.edu.upc.yb.common.security.service.JwtTokenUtil;
import cn.edu.upc.yb.common.service.QueryService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * Created By Kazusa in 2018/10/28 9:37
 */

@Service
public class SchoolAwardwx {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private QueryService queryService;

    /**
     * 易班https://openapi.yiban.cn/school/award_wx接口封装
     *
     * @param token upcyiban token
     * @return
     */

    public Boolean schoolAwardwx(String token,int ybId,int pay)throws IOException {
        String ybtoken = jwtTokenUtil.getYbaccessToken(token);
        String queryString = "access_token=" + ybtoken + "&yb_userid=" + ybId+ "&pay=" + pay;
        String result = queryService.getYbApi("school/award_wx", queryString);
        Gson gson = new Gson();
        System.out.println(result);
        try {
            Pay pay1 = gson.fromJson(result, Pay.class);
            if(pay1.status=="error"){
                return false;
            }else{
                return true;
            }
        } catch (Exception e) {
            return false;
        }
    }
    private class Pay {

        String status;
        String info;
    }

    }
