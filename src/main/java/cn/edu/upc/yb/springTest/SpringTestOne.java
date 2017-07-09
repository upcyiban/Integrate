package cn.edu.upc.yb.springTest;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by idea on 2017/7/9.
 */

public class SpringTestOne {
public static void main(String[] arg){
  AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DiConfig.class);
  UseFun useFun = context.getBean(UseFun.class);
  System.out.println(useFun.SayHello("di"));
context.close();

}

}
