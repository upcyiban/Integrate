package cn.edu.upc.yb.ybapi;

import cn.edu.upc.yb.common.ybapi.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

/**
 * Created by lylllcc on 2017/6/24.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class TopicTest {

    String token = "eyJhbGciOiJIUzUxMiJ9.eyJhdWRpZW5jZSI6IndlYiIsInlidG9rZW4iOiIwZjZhYTI3NjZhMmJmMDYxZWQxZTA0YjU1MDU1MTExY2MwYjE0M2Q4IiwiYXBwbmFtZSI6InRlc3QiLCJjcmVhdGVkIjoxNDk5NjAxMTM5Nzc1LCJ5YmlkIjo1ODMxNDQ5LCJleHAiOjE1MDAyMDU5Mzl9.3YOJZjz0H6dgKc615lqIo0RE8kMZn6QicNVSzWLzcfnrqIKarprs90AqB9twY3y7wmqSC1KaIeMMWzXDY0Rvpw";
    @Autowired
    private HotTopic hotTopic;


    @Autowired
    private SendTopic sendTopic;


    @Autowired
    private SendComment sendComment;


    @Autowired
    private TopicInfo topicInfo;


    @Autowired
    private TopicComment topicComment;


    @Autowired
    private DeleteComment deleteComment;


    @Autowired
    private DeleteTopic deleteTopic;

    @Autowired
    private UserMe userMe;
    @Test
    public void ggg() throws  IOException{
        userMe.getUserMe(token);
    }


    @Test
    public void dTopic() throws  IOException{
        deleteTopic.getDeleteTopic(token,"327387","2");
    }

    @Test
    public void hTopic() throws IOException {
        hotTopic.getHotTopic(token);
    }

    @Test
    public void sTopic() throws IOException {
        sendTopic.getSendTopic(token,"1","2","3456");
    }
    @Test
    public void sComment() throws IOException {
        sendComment.getSendComment(token,"1","2");
    }
    @Test
    public void tInfo() throws IOException {
        topicInfo.getTopicInfo(token,"327387","4","2");
    }
    @Test
    public void tComment() throws IOException {
        topicComment.getTopicComment(token,"327387","4","2");
    }
    @Test
    public void dComment() throws IOException {
        deleteComment.getDeleteComment(token,"327387","4","2","3");
    }

}
