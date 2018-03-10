package cn.edu.upc.yb.lottery.controller;

import cn.edu.upc.yb.lottery.service.LotteryService;
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
    public Object lotteryIndex(HttpServletRequest request) {

        return lotteryService.getLotteryList();

    }

    @GetMapping("/userInfo")
    @ResponseBody
    public Object userInfo(HttpServletRequest request) throws IOException{

        return lotteryService.getUserInfo(request);
    }

    @PostMapping("/create")
    @ResponseBody
    public Object createLottery(@RequestBody LotteryService.Create create) {

        return lotteryService.createLottery(create);
    }

    @GetMapping("/dolottery")
    @ResponseBody
    public Object doLottery(HttpServletRequest request) throws IOException {

        return lotteryService.doLottery(request);

    }
}
