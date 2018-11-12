package cn.edu.upc.yb.api;

import cn.edu.upc.yb.common.ybapi.MsgLetter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MsgLetterTest {

    @Autowired
    private MsgLetter msgLetter;

    @Test
    public void test(){
//        MsgLetter msgLetter = new MsgLetter();
        String token = "eyJhbGciOiJIUzUxMiJ9.eyJhdWRpZW5jZSI6IndlYiIsI" +
                "nlidG9rZW4iOiIzYzM4YWJmNzYxYmUyNDk3Y2EwYTQ4Zj" +
                "ljYjBkNGIxN2U2M2YyZGYyIiwiYXBwbmFtZSI6InNlY29uZCIsImNyZWF0ZWQiO" +
                "jE1NDIwMDQyOTI4ODcsInliaWQiOjU4MzA2MzEsImV4cCI6MTU0MjYwOTA5Mn0.DXe_" +
                "92mN_JnQiNbZbxAm59sRBPRwDLNbL8n2L4y7vVCxbcCLQRe93Wtztpqnd11YLDrLle" +
                "zeDnmEPO2fQge7cg";
        String touid = "5830631";
        String content = "你好呀";
        for (int i=0;i<2;i++){
            msgLetter.setMsgLetter(token,touid,content);
        }

    }

}
