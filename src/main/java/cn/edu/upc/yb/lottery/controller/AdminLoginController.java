package cn.edu.upc.yb.lottery.controller;

import cn.edu.upc.yb.lottery.service.AdminLoginService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liliangbin dumpling1520@gmail.com
 * @date 2018/3/6  20:38
 */

@RestController
@RequestMapping("/lottery/admin")
public class AdminLoginController {

    @Autowired
    private AdminLoginService adminLoginService;

    @PostMapping("/login")
    @ApiOperation(value = "/login",notes = "admin的登陆操作")
    public Object login(String username, String password) throws Exception {

        return adminLoginService.login(username, password);
    }

    @GetMapping("/logout")
    @ApiOperation(value = "/logout",notes = "admin的退出")
    public Object logout() {
        return adminLoginService.logout();
    }

}
