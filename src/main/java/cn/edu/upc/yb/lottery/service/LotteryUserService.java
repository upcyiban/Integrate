package cn.edu.upc.yb.lottery.service;

import cn.edu.upc.yb.common.security.service.JwtTokenUtil;
import cn.edu.upc.yb.lottery.model.Creator;
import cn.edu.upc.yb.lottery.model.LotteryList;
import cn.edu.upc.yb.lottery.repository.CreatorRepository;
import cn.edu.upc.yb.lottery.repository.LotteryListRepository;
import cn.edu.upc.yb.lottery.utils.ResponseBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author liliangbin dumpling1520@gmail.com
 * @date 2018/3/8  9:37
 */
@Service
public class LotteryUserService {
    @Autowired
    private LotteryListRepository lotteryListRepository;

    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private CreatorRepository creatorRepository;

    //把过了和没有过的待审核的都统统的搞进一个map里面。

    public Map<String, List<LotteryList>> getLotterylist(HttpServletRequest request) {
        String authtoken = request.getParameter(this.tokenHeader);

        String ybId = jwtTokenUtil.getYBidFromTocken(authtoken);

        Creator creator = creatorRepository.findByYibanid(ybId);

        List<LotteryList> lotteryLists = lotteryListRepository.findAllByCreatorid(creator.getId());

        List<LotteryList> lotteryNotpass = null;
        List<LotteryList> lotteryWait = null;
        for (LotteryList lottery : lotteryLists) {

            if (lottery.getIspass() == 0) {
                lotteryWait.add(lottery);
                lotteryLists.remove(lottery);
            }
        }

        Map<String, List<LotteryList>> listMap = new HashMap<>();
        listMap.put("pass", lotteryLists);
        listMap.put("notPass", lotteryNotpass);
        return listMap;
    }

    public Object recover(long lotteryId, String lotteryname, String lotteryintro, Timestamp timebegin, Timestamp timeend) {

        LotteryList lottery = lotteryListRepository.findById(lotteryId);

        lottery.setLotteryname(lotteryname);
        lottery.setLotterytimebegin(timebegin);
        lottery.setLotterytimeend(timeend);
        lottery.setLotteryintro(lotteryintro);
        lottery.setIspass(0);
        lotteryListRepository.save(lottery);
        return new ResponseBean(1, "修改成功", null);
    }

    public Object warning(long lotteryId,String  feedback){


        LotteryList lotteryList = lotteryListRepository.findById(lotteryId);

        lotteryList.setIspass(0);
        lotteryList.setFeedback(feedback);
        lotteryList.setFeedbackTime(new Date(System.currentTimeMillis()));
        lotteryListRepository.save(lotteryList);
        return new ResponseBean(1,"举报成功",null);
    }
}
