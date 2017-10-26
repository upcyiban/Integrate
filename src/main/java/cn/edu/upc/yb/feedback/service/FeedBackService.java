package cn.edu.upc.yb.feedback.service;

import cn.edu.upc.yb.common.dto.ErrorReporter;
import cn.edu.upc.yb.common.security.service.JwtTokenUtil;
import cn.edu.upc.yb.feedback.model.FeedBackRepository;
import cn.edu.upc.yb.feedback.model.FeedbackMessage;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by lylll on 2017/6/4.
 */
@Service
public class FeedBackService {

    private final Log logger = LogFactory.getLog(this.getClass());

    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    private FeedBackRepository feedBackRepository;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    public Object doFeedBack(HttpServletRequest request, String message, String appname) {

        //从请求头中获取token
        String authToken = request.getParameter(this.tokenHeader);

        //从token中获取id
        String ybid = jwtTokenUtil.getYBidFromTocken(authToken);

        return feedBackRepository.save(new FeedbackMessage(Integer.valueOf(ybid), message, appname));

    }


    public Object showAll() {

        return feedBackRepository.findAll();
    }

    public Object delete(int id) {

        try {

            feedBackRepository.delete(id);

        } catch (Exception ex) {
            ex.printStackTrace();
            logger.info("删除某个id处存在问题" + id);
            return new ErrorReporter(1, "error");

        }

        logger.info("删除成功");

        return "删除成功";
    }

    public Object showOne(int id) {


        FeedbackMessage feedbackMessage = feedBackRepository.findOne(id);

        if (feedbackMessage == null) {

            logger.info("查找这个id：" + id + "为空");
            return new ErrorReporter(1, "error");
        }
        return feedbackMessage;


    }

    public Object modtify(String message, String appname, int id) {

        try {
            FeedbackMessage feedbackMessage;
            feedbackMessage = feedBackRepository.findOne(id);
            feedbackMessage.setAppname(appname);
            feedbackMessage.setMessage(message);
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.info("修改信息处出现错误");
            return new ErrorReporter(1, "error");
        }

        return "修改成功";
    }




}
