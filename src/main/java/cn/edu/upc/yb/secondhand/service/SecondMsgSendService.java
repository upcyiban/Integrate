package cn.edu.upc.yb.secondhand.service;

import cn.edu.upc.yb.common.ybapi.MsgLetter;
import cn.edu.upc.yb.secondhand.model.SecondArticle;
import cn.edu.upc.yb.secondhand.model.SecondUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;

@Service
public class SecondMsgSendService {

    @Autowired
    private MsgLetter msgLetter;

    public void reviewSend(String token, SecondArticle secondArticle, SecondUser user){
        String content = "评论了您在二手市场发布的" + secondArticle.getName() + "，快去看看吧。"+
                "\nhttp://f.yiban.cn/iapp218463";
       // System.out.println("评论"+content);
        String touid = String.valueOf(secondArticle.getUserid());
        Boolean result = false;
        result = (Boolean) msgLetter.setMsgLetter(token,touid,content);
    }

    public void collectionSend(String token,SecondArticle secondArticle){
        String content = "收藏了您在二手市场发布的" + secondArticle.getName() + "，快去看看吧。"+
                "\nhttp://f.yiban.cn/iapp218463";

        String touid = String.valueOf(secondArticle.getUserid());
       // System.out.println("收藏"+content);
        msgLetter.setMsgLetter(token,touid,content);
    }


}
