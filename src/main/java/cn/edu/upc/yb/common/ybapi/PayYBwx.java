package cn.edu.upc.yb.common.ybapi;

import cn.edu.upc.yb.common.dto.ErrorReporter;
import cn.edu.upc.yb.common.security.service.JwtTokenUtil;
import cn.edu.upc.yb.common.service.QueryService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class PayYBwx {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private QueryService queryService;


    /**
     * 易班https://openapi.yiban.cn/pay/yb_wx接口封装
     *
     * @param token upcyiban token
     * @return
     */


    public Object getYBwx(String token, int pay) throws IOException {

        String ybtoken = jwtTokenUtil.getYbaccessToken(token);
        String queryString = "access_token=" + ybtoken + "&pay=" + pay;
        String result = queryService.getYbApi("pay/yb_wx", queryString);
        Gson gson = new Gson();
        System.out.println(result);
        try {
            Pay pay1 = gson.fromJson(result, Pay.class);
            return pay1;
        } catch (Exception e) {
            return new ErrorReporter(1, "请求失败");
        }
    }
    private class Pay {

        String status;
        String info;
    }
}
