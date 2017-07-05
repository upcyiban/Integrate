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
public class FriendTest {

    String tocken = "eyJhbGciOiJIUzUxMiJ9.eyJhdWRpZW5jZSI6IndlYiIsInlidG9rZW4iOiIwZjZhYTI3NjZhMmJmMDYxZWQxZTA0YjU1MDU1MTExY2MwYjE0M2Q4IiwiYXBwbmFtZSI6InRlc3QiLCJjcmVhdGVkIjoxNDk5MjU3NjgzNDY1LCJ5YmlkIjo1ODMxNDQ5LCJleHAiOjE0OTk4NjI0ODN9.3yvec-l_g48ghIjZ-DfR6JRclJvYnaRfg2-LsclrsQhoBZhzzyQ7Zcxq4szpwmfjLe-JhWgK30Pu6xOxjimrpg";


    @Autowired
    private FriendApply friendapplay;

    @Autowired
    private FriendMeList friendMeList;

    @Autowired
    private FriendCheck friendCheck;

    @Autowired
    private FriendRemove friendRemove;

    @Autowired
    private FriendRecommend friendRecommend;



    @Test
    public void applay() throws IOException {

        friendapplay.doFriendApply(tocken,7075653,61);
    }

    @Test
    public void check() throws IOException {
        friendCheck.getFriendCheck(tocken,7075653);
    }

    @Test
    public void friendMe() throws IOException {
        friendMeList.getFriendMeList(tocken,1,10);
    }

    @Test
    public void friendRe() throws IOException {
        friendRemove.doFriendRemove(tocken,7075653);
    }

    @Test
    public void recommend() throws IOException {
        friendRecommend.getFriendRecommend(tocken,10);
    }

}
