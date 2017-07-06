package cn.edu.upc.yb.ybapi;

import cn.edu.upc.yb.common.ybapi.IsReal;
import cn.edu.upc.yb.common.ybapi.UserOther;
import cn.edu.upc.yb.common.ybapi.UserRealMe;
import cn.edu.upc.yb.common.ybapi.UserVerifyMe;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

/**
 * Created by idea on 2017/7/6.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserVerifyMeTest {


  private String token = "eyJhbGciOiJIUzUxMiJ9.eyJhdWRpZW5jZSI6IndlYiIsInlidG9rZW4iOiIwZjZhYTI3NjZhMmJmMDYxZWQxZTA0YjU1MDU1MTExY2MwYjE0M2Q4IiwiYXBwbmFtZSI6InRlc3QiLCJjcmVhdGVkIjoxNDk5MzUwMjM2NTcwLCJ5YmlkIjo1ODMxNDQ5LCJleHAiOjE0OTk5NTUwMzZ9.-qloz_hwbRmLV8Fh1IX7_PLafOvDutSDWbZs7M1mQzyLV7QOyXN-Q4EsnxXvuAZu6ZmrmlUVRyNYViuLakpmog";

  @Autowired
  private UserVerifyMe userVerifyMe;

  @Autowired
  private UserOther userOther;

  @Autowired
  private IsReal isReal;

  @Test
  public void userMe() throws IOException {
      System.out.println(userVerifyMe.getUserVerifyMe(token));
  }

  @Test
  public void userOther() throws IOException {
    userOther.getUserOther(token);
  }
  @Test
  public void isReal() throws  IOException{
    isReal.getIsReal(token);
  }
}
