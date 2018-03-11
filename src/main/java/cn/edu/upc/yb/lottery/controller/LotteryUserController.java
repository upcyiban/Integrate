package cn.edu.upc.yb.lottery.controller;

import cn.edu.upc.yb.lottery.model.LotteryList;
import cn.edu.upc.yb.lottery.service.LotteryUserService;
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
    public Object lotteryPass(HttpServletRequest request) throws IOException{

        Map<String , List<LotteryList>> listMap = lotteryUserService.getLotterylist(request);
        return listMap.get("pass");
    }

    @GetMapping("/notPass")
    public Object lotteryNotPass(HttpServletRequest request){
        Map<String , List<LotteryList>> listMap = lotteryUserService.getLotterylist(request);
        return listMap.get("notPass");
    }

   /* @PostMapping("/cover")
    public Object lotterycover(long lotteryid, String lotteryname, String lotteryintro, Timestamp timebegin,Timestamp timeend){

        return lotteryUserService.recover(lotteryid,lotteryname,lotteryintro,timebegin,timeend);

    }
*/
    @PostMapping
    public Object warning(long lotteryId,String feedback){
        return lotteryUserService.warning(lotteryId,feedback);
    }

}
