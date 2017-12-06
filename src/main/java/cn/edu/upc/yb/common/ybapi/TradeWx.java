package cn.edu.upc.yb.common.ybapi;

import cn.edu.upc.yb.common.dto.ErrorReporter;
import cn.edu.upc.yb.common.security.service.JwtTokenUtil;
import cn.edu.upc.yb.common.service.QueryService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
@Service
public class TradeWx {


    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private QueryService queryService;


    /**
     * 易班https://openapi.yiban.cn/pay//trade_wx接口封装
     *
     * @param token upcyiban token
     * @return
     */


    public Object tradeWx(String token, int pay, String sighback, int YBid) throws IOException {

        String ybtoken = jwtTokenUtil.getYbaccessToken(token);
        String queryString = "access_token=" + ybtoken + "&pay=" + pay + "&sign_back=" + sighback + "&yb_userid=" + YBid;
        String result = queryService.getYbApi("pay/trade_wx", queryString);
        Gson gson = new Gson();
        System.out.println(result);
        try {
            TradeInfo tradeInfo = gson.fromJson(result, TradeInfo.class);
            return tradeInfo;
        } catch (Exception e) {

            return new ErrorReporter(1, "请求失败");
        }
    }

    class TradeInfo {

        String status;

        class Info {
            int trade_id;
            String sign_href;
        }

    }
}

/*
* {
  "status":"success",
  "info":{
    "trade_id":"交易订单号",
    "sign_href":"收银台验签地址"
  }
}*/