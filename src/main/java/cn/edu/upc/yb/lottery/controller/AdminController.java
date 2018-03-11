package cn.edu.upc.yb.lottery.controller;

import ch.qos.logback.core.pattern.util.RegularEscapeUtil;
import cn.edu.upc.yb.lottery.service.AdminService;
import cn.edu.upc.yb.lottery.utils.ResponseBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * @author liliangbin dumpling1520@gmail.com
 * @date 2018/3/6  20:33
 */

@RestController
@RequestMapping("/lottery/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private HttpSession httpSession;

    @GetMapping("/getlotterylist")
    public Object getLotteryList() {
        if (httpSession.getAttribute("lotteryAdmin")==null){
            return new ResponseBean(-1,"没有登陆",null);
        }
            return adminService.getLotteryList();
    }

    @PostMapping("/dochoice")
    public Object dochoice(String lotteryid, int ispass , String feedback) {
        if (httpSession.getAttribute("lotteryAdmin")==null){
            return new ResponseBean(-1,"没有登陆",null);
        }
        return adminService.selectLottery(lotteryid, ispass, feedback);
    }

}
