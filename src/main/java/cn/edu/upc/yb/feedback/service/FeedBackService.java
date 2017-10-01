package cn.edu.upc.yb.feedback.service;

import cn.edu.upc.yb.common.security.service.JwtTokenUtil;
import cn.edu.upc.yb.feedback.model.FeedBackRepository;
import cn.edu.upc.yb.feedback.model.FeedbackMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by lylll on 2017/6/4.
 */
@Service
public class FeedBackService {

    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    private FeedBackRepository feedBackRepository;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    public Object doFeedBack(HttpServletRequest request,String message, String appname){

        //从请求头中获取token
        String authToken = request.getParameter(this.tokenHeader);

        //从token中获取id
        String ybid = jwtTokenUtil.getYBidFromTocken(authToken);

        return feedBackRepository.save(new FeedbackMessage(Integer.valueOf(ybid),message,appname));
    }
}
