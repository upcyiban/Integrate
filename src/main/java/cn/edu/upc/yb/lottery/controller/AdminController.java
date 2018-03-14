package cn.edu.upc.yb.lottery.controller;

import cn.edu.upc.yb.common.dto.SwaggerParameter;
import cn.edu.upc.yb.lottery.service.AdminService;
import cn.edu.upc.yb.lottery.utils.ResponseBean;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
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

    @ApiOperation(value = "/getlotterylist",notes = "获取待审核的抽奖")
    public Object getLotteryList() {
        if (httpSession.getAttribute("lotteryAdmin")==null){
            return new ResponseBean(-1,"没有登陆",null);
        }
            return adminService.getLotteryList();
    }

    @PostMapping("/dochoice")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "lotteryid",value = "抽奖的Id",dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "ispass", value = "一般是填1",dataType = "int"),
            @ApiImplicitParam(paramType = "query", name = "feedback",value = "应用的反馈信息",dataType = "String")

    })

    public Object dochoice(String lotteryid, int ispass , String feedback) {
        if (httpSession.getAttribute("lotteryAdmin")==null){
            return new ResponseBean(-1,"没有登陆",null);
        }
        return adminService.selectLottery(lotteryid, ispass, feedback);
    }

}
