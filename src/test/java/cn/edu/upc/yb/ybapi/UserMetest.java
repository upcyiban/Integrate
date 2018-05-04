package cn.edu.upc.yb.ybapi;

import cn.edu.upc.yb.common.security.service.JwtTokenUtil;
import cn.edu.upc.yb.common.ybapi.UserMe;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMetest {

    @Autowired
    private UserMe userMe;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Test
    public void userMeTest(){

        String token="eyJhbGciOiJIUzUxMiJ9.eyJhdWRpZW5jZSI6IndlYi" +
                "IsInlidG9rZW4iOiI0NDdiNDg4NGIyN2Q1M2NkM2ZiOGQ" +
                "zZjc2MDRjZWYwZWJhOWQwYjRkIiwiYXBwbmFtZSI6InNlY" +
                "29uZCIsImNyZWF0ZWQiOjE1MjUxNjAwNDk3MTQsInliaWQi" +
                "OjU4MzA2MzEsImV4cCI6MTUyNTc2NDg0OX0.H0sR7LzVOL0EOnnk" +
                "whyZIeWszXEXzwCwGxiQrXhJrWk3Ad6jn2OzXYnRA0dYOJ" +
                "rDKXhxvMslKfwK7F7wP7hQnQ";

        String access_token=jwtTokenUtil.getYbaccessToken(token);
        UserMe.UserInfo userInfo;
        try {
           userInfo=(UserMe.UserInfo)userMe.getUserMe(access_token);
            System.out.println(userInfo.info.yb_username);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

}
