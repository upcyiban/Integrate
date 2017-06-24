package cn.edu.upc.yb.common.ybapi;

import cn.edu.upc.yb.common.security.service.JwtTokenUtil;
import cn.edu.upc.yb.common.service.QueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URLEncoder;

/**
 * Created by lylllcc on 2017/6/22.
 */
@Service
public class UserMe {


    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private QueryService queryService;

    /**
     * 易班https://openapi.yiban.cn/user/me接口封装
     * @param token upcyiban token
     * @return
     */
    public Object getUserMe(String token) throws IOException {
        String ybtoken = jwtTokenUtil.getYbaccessToken(token);
        String queryString = "access_token=" + ybtoken;
        String charset = "UTF-8";
        String query = URLEncoder.encode(queryString, charset);
        String result = queryService.getYbApi("user/me",query);
        return result;
    }

}
