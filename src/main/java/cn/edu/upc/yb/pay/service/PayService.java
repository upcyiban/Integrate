package cn.edu.upc.yb.pay.service;

import cn.edu.upc.yb.common.security.service.JwtTokenUtil;
import cn.edu.upc.yb.common.ybapi.PayYBwx;
import cn.edu.upc.yb.feedback.model.FeedBackRepository;
import com.sun.deploy.net.HttpRequest;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Service
public class PayService {

    @Autowired
    JwtTokenUtil jwtTokenUtil;


    private final Log logger = LogFactory.getLog(this.getClass());

    public Object doPay(String token, int pay) throws IOException {
        PayYBwx payYBwx = new PayYBwx();
        String ybid = jwtTokenUtil.getYBidFromTocken(token);
        logger.info("yibanid = "+ybid);
       return payYBwx.getYBwx(token, pay);
   //  return  ybid;
    }

}
