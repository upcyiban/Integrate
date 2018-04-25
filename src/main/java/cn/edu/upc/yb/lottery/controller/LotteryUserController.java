package cn.edu.upc.yb.lottery.controller;

import cn.edu.upc.yb.common.dto.SwaggerParameter;
import cn.edu.upc.yb.lottery.model.LotteryList;
import cn.edu.upc.yb.lottery.service.LotteryUserService;
import cn.edu.upc.yb.lottery.utils.ResponseBean;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author liliangbin dumpling1520@gmail.com
 * @date 2018/3/8  9:36
 */
@RestController
@RequestMapping("/lottery/user")
public class LotteryUserController {
    @Autowired
    private LotteryUserService lotteryUserService;

    @GetMapping("/pass")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = SwaggerParameter.Authorization, value = "token", dataType = "String"),
    })
    public Object lotteryPass(HttpServletRequest request) throws IOException{

        Map<String , List<LotteryList>> listMap = lotteryUserService.getLotterylist(request);
        return new ResponseBean(1,"所有过的抽奖",listMap.get("pass")) ;
    }

    @GetMapping("/notPass")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = SwaggerParameter.Authorization, value = "token", dataType = "String"),
    })
    public Object lotteryNotPass(HttpServletRequest request) throws Exception{
        Map<String , List<LotteryList>> listMap = lotteryUserService.getLotterylist(request);
        return new ResponseBean(1,"没有过的",listMap.get("notPass")) ;
    }


    @PostMapping("/warning")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = SwaggerParameter.Authorization, value = "token", dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "lotteryId", value = "lotteryId", dataType = "Long"),
            @ApiImplicitParam(paramType = "query", name = "feedback", value = "反馈信息", dataType = "String"),
    })
    public Object warning(long lotteryId,String feedback){
        return lotteryUserService.warning(lotteryId,feedback);
    }

}
