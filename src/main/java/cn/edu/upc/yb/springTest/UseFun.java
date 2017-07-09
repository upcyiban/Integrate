package cn.edu.upc.yb.springTest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by idea on 2017/7/9.
 */
@Service
public class UseFun {

  @Autowired
  public Fun fun;

  public String SayHello(String word){
    return fun.sayHello(word);
  }
}
