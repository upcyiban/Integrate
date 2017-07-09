package cn.edu.upc.yb.ybapi;

import cn.edu.upc.yb.common.ybapi.UserMe;
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
public class UserMeTest {

   String token = "eyJhbGciOiJIUzUxMiJ9.eyJhdWRpZW5jZSI6IndlYiIsInlidG9rZW4iOiIwZjZhYTI3NjZhMmJmMDYxZWQxZTA0YjU1MDU1MTExY2MwYjE0M2Q4IiwiYXBwbmFtZSI6InRlc3QiLCJjcmVhdGVkIjoxNDk5NDg2Njc0MDc1LCJ5YmlkIjo1ODMxNDQ5LCJleHAiOjE1MDAwOTE0NzR9.UlAQFDgw-pUFpwnK-lwvcvZtQKACnX4-oUetZ6f8rZjMKEkJQb6u9OXNH16HDxP_4KcC1d7UNCgnJ1NGPMt5MA";
    @Autowired
    private UserMe userMe;

    @Test
    public void userMe() throws IOException {
        System.out.println(userMe.getUserMe(token));
    }
}
