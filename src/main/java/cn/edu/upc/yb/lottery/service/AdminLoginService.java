package cn.edu.upc.yb.lottery.service;

import cn.edu.upc.yb.lottery.config.AdminConfig;
import cn.edu.upc.yb.lottery.utils.MD5;
import cn.edu.upc.yb.lottery.utils.ResponseBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

/**
 * @author liliangbin dumpling1520@gmail.com
 * @date 2018/3/6  20:39
 */

@Service
public class AdminLoginService {

    @Autowired
    private AdminConfig adminConfig;

    @Autowired
    public HttpSession httpSession;

    public Object login(String userName,String password) throws Exception{

        String str = userName+adminConfig.adminSalt+password;

        if(MD5.checkpassword(str,adminConfig.adminPassword)){

            httpSession.setAttribute("lotteryAdmin",true);
            return new ResponseBean(1,"登陆成功","登陆成功");

        }
        else {

            return new ResponseBean(-1,"密码或用户名有问题，请确认",null);

        }


    }

    public Object logout(){

        if(httpSession.getAttribute("lotteryAdmin")!=null ){

            httpSession.removeAttribute("lotteryAdmin");


        }
        return new ResponseBean(1,"你已经成功退出登陆",null);
    }


}
