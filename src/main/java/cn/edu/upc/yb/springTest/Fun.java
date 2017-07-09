package cn.edu.upc.yb.springTest;

import org.springframework.stereotype.Service;

/**
 * Created by idea on 2017/7/9.
 */
@Service
public class Fun {
  public String sayHello(String word){
    return "hello" + word + "!";
  }
}
