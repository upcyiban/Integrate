package cn.edu.upc.yb.lottery;

import cn.edu.upc.yb.lottery.config.AdminConfig;
import cn.edu.upc.yb.lottery.service.AdminLoginService;
import cn.edu.upc.yb.lottery.service.AdminService;
import cn.edu.upc.yb.lottery.utils.MD5;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

/**
 * @author liliangbin dumpling1520@gmail.com
 * @date 2018/3/9  20:01
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AdminLoginTest {

    @Autowired
    private AdminLoginService adminLoginService;

    @Autowired
    private AdminConfig adminConfig;

    @Test
    public void  login() throws Exception{

        System.out.println(adminConfig.adminSalt + "=====>" + adminConfig.adminUsername+"=====>" + adminConfig.adminPassword);
        String userName = "admin";
        String password = "123456";
        String str = userName+adminConfig.adminSalt+password;

        Object obj = MD5.EncoderByMd5(str);
        System.out.println(  "密码是什么： =====>"+ obj.toString());

        Boolean ll= MD5.checkpassword(str,adminConfig.adminPassword);
        System.out.println("chick  password : ====>" + ll);


    }


    @Test
    public void  llll() throws Exception{

        adminLoginService.login("admin","123456");
    }



}
