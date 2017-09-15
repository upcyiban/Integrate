package cn.edu.upc.yb.ybapi;

import cn.edu.upc.yb.common.ybapi.*;
import org.hibernate.annotations.Check;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

/**
 * Created by lylllcc on 2017/7/5.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class ShareTest {
    String token = "eyJhbGciOiJIUzUxMiJ9.eyJhdWRpZW5jZSI6IndlYiIsInlidG9rZW4iOiIwZjZhYTI3NjZhMmJmMDYxZWQxZTA0YjU1MDU1MTExY2MwYjE0M2Q4IiwiYXBwbmFtZSI6InRlc3QiLCJjcmVhdGVkIjoxNDk5NjAxMTM5Nzc1LCJ5YmlkIjo1ODMxNDQ5LCJleHAiOjE1MDAyMDU5Mzl9.3YOJZjz0H6dgKc615lqIo0RE8kMZn6QicNVSzWLzcfnrqIKarprs90AqB9twY3y7wmqSC1KaIeMMWzXDY0Rvpw";
    @Autowired
    private SendShare sendShare;
    @Autowired
    private InfoShare infoShare;

    @Autowired
    private ShareMeList shareMeList;

    @Autowired
    ShareOtherList shareOtherList;

    @Autowired
    ShareSendComment shareSendComment;

    @Autowired
    SharePraise sharePraise;

    @Test
    public void spraise()throws IOException{
        sharePraise.getSharePraise(token,"123","1");
    }

    @Test
    public void sscomment()throws IOException{
        shareSendComment.getShareSendComment(token,"123","456");
    }

    @Test
    public void soList()throws IOException{
        shareOtherList.getShareOtherList(token);
    }
    @Test
    public void smList()throws IOException{
        shareMeList.getShareMeList(token,"123");
    }
    @Test
    public void ishare()throws IOException {
        infoShare.getInfoShare(token,"23");
    }

    @Test
    public void sshare() throws IOException {
        sendShare.getSendShare(token,"23","123","256");
    }
}
