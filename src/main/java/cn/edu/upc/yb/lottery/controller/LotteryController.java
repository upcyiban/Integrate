package cn.edu.upc.yb.lottery.controller;

import cn.edu.upc.yb.common.dto.SwaggerParameter;
import cn.edu.upc.yb.lottery.service.LotteryService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.Swagger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author liliangbin dumpling1520@gmail.com
 * @date 2018/2/22  10:30
 */


@RestController
@RequestMapping("/lottery")
public class LotteryController {

    @Autowired
    private LotteryService lotteryService;

    @GetMapping("")
    @ApiOperation(value = "", notes = "获取抽奖的列表")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = SwaggerParameter.Authorization, value = "token", dataType = "String")
    })
    public Object lotteryIndex(HttpServletRequest request) {

        return lotteryService.getLotteryList();
    }

    @PostMapping("/userInfo")
    @ResponseBody

    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = SwaggerParameter.Authorization, value = "token", dataType = "String")
    })

    public Object userInfo(HttpServletRequest request) throws IOException {

        return lotteryService.getUserInfo(request);
    }

    @PostMapping("/create")
    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = SwaggerParameter.Authorization, value = "token", dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "username", dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "lotteryname", dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "lotteryintro", dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "timeStart", dataType = "Timestamp"),
            @ApiImplicitParam(paramType = "query", name = "timeEnd", dataType = "Timestamp"),
            @ApiImplicitParam(paramType = "query", name = "list<Prizes>", dataType = "Prizes  的一个数组"),
    })
    public Object createLottery(@RequestBody LotteryService.Create create) {

        return lotteryService.createLottery(create);
    }

    @PostMapping("/ispass")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name =SwaggerParameter.Authorization, value = "抽奖Id", dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "lotteryId", value = "抽奖Id", dataType = "long"),
            @ApiImplicitParam(paramType = "query", name ="passcode", value = "passcode", dataType = "int")
    })
    public Object isPass(long lotteryId, int passcode) {
        return lotteryService.isPass(lotteryId, passcode);
    }


    @GetMapping("/dolottery")
    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name =SwaggerParameter.Authorization, value = "抽奖Id", dataType = "String"),
            @ApiImplicitParam(paramType = "query", name ="lotteryId", value = "抽奖Id", dataType = "long")

    })
    public Object doLottery(HttpServletRequest request) throws IOException {

        return lotteryService.doLottery(request);

    }

    @PostMapping("/prizeList")

    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name =SwaggerParameter.Authorization, value = "抽奖Id", dataType = "String"),
            @ApiImplicitParam(paramType = "query", name ="lotteryId", value = "抽奖Id", dataType = "long")
    })
    public Object prizeList(long lotteryId) {
        return lotteryService.message(lotteryId);
    }

    @PostMapping("/fuzzy")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name =SwaggerParameter.Authorization, value = "抽奖Id", dataType = "String"),
            @ApiImplicitParam(paramType = "query", name ="passcode", value = "passcode", dataType = "long")

    })    public Object findall(int passcode) {

        return lotteryService.findbycode(passcode);
    }

}
