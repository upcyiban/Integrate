package cn.edu.upc.yb.ybapi;

import cn.edu.upc.yb.common.ybapi.*;
import org.hibernate.annotations.Check;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.access.method.P;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

/**
 * Created by lylllcc on 2017/7/5.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class FriendTest {

    String tocken = "eyJhbGciOiJIUzUxMiJ9.eyJhdWRpZW5jZSI6IndlYiIsInlidG9rZW4iOiI3OGQ5ZmYxYjIyZmI3ODBlZjkwNDhhODQ3MTRiZTJiOTJkYWM0YTc4NmJjOTg0MzY1ZGM5NzdkMWQ5N2Y1NDFlNzU3OGZkMGY3NWY3YTJkYTYwMDQwMzQ3Y2M4OGRlMjg1NmMwNDg0ZTM4MDc4MWUwYWU4ODZjNzkwOGQ4MDRkOWQ2YTg5MDI1MGZhMmI0ZWM2ZTQ0NzdhYTUzMzgxNzc3IiwiYXBwbmFtZSI6ImNvbW1pdCIsImNyZWF0ZWQiOjE1MDg5OTg4MTQ2NzAsInliaWQiOjU4MzE0NDksImV4cCI6MTUwOTYwMzYxNH0.nW2sf4ttoDzs-Vh2sxnKjCzgAzSigL8QEwgT2aGIMuSwgdYFxaIX_OGp46qHKZf-I8tafDz7mCeSNCCnkX45kA";


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

    @Autowired
    private PayYBwx payYBwx;


    @Test
    public void applay() throws IOException {

        friendapplay.doFriendApply(tocken,7075653,"I am your father.");
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
    @Test
    public void pay() throws IOException{
        payYBwx.getYBwx(tocken,1);

    }

}
