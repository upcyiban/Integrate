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

    String token = "eyJhbGciOiJIUzUxMiJ9.eyJhdWRpZW5jZSI6IndlYiIsInlidG9rZW4iOiIwZjZhYTI3NjZhMmJmMDYxZWQxZTA0YjU1MDU1MTExY2MwYjE0M2Q4IiwiYXBwbmFtZSI6ImZlZWRiYWNrIiwiY3JlYXRlZCI6MTQ5ODMwNDE1ODQ4NiwieWJpZCI6NTgzMTQ0OSwiZXhwIjoxNDk4OTA4OTU4fQ.yBTkYdniaVVM5e-R8uRT1Dw5K33fjD37mtS2__8hO2oDn-l1IkqIDuIIcoQZ3r_ViuCqNIqdTHfD-4Lb23WSLg";

    @Autowired
    private UserMe userMe;

    @Test
    public void userMe() throws IOException {
        System.out.println(userMe.getUserMe(token));
    }



}
