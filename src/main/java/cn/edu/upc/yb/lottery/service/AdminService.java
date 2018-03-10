package cn.edu.upc.yb.lottery.service;

import cn.edu.upc.yb.lottery.model.LotteryList;
import cn.edu.upc.yb.lottery.model.PrizeList;
import cn.edu.upc.yb.lottery.repository.LotteryListRepository;
import cn.edu.upc.yb.lottery.utils.ResponseBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.ws.ServiceMode;
import java.sql.Date;
import java.util.List;

/**
 * @author liliangbin dumpling1520@gmail.com
 * @date 2018/3/6  20:34
 */

@Service
public class AdminService {
    @Autowired
    private LotteryListRepository lotteryListRepository;


    public Object getLotteryList() {

        List<LotteryList> lotteryLists = lotteryListRepository.findAllByIspass(0);
        return lotteryLists;

    }

    public Object selectLottery(String lotteryid, int ispass, String feedback) {
//假设ispass=1，就是通过。ispass = 0；就是被举报的。。。。。
        //没有ispass = 2；

        LotteryList lottery = lotteryListRepository.findById(Long.valueOf(lotteryid));

            lottery.setIspass(ispass);
            lottery.setFeedback(feedback);
            lottery.setFeedbackTime(new Date(System.currentTimeMillis()));
            lotteryListRepository.save(lottery);
            return new ResponseBean(1,"选择成功", null);

    }
}
