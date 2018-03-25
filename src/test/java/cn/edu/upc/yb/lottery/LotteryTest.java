package cn.edu.upc.yb.lottery;

import cn.edu.upc.yb.lottery.model.LotteryList;
import cn.edu.upc.yb.lottery.model.Prize;
import cn.edu.upc.yb.lottery.repository.LotteryListRepository;
import cn.edu.upc.yb.lottery.repository.PrizeRepository;
import cn.edu.upc.yb.lottery.service.LotteryService;
import cn.edu.upc.yb.lottery.service.LotteryUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * @author liliangbin dumpling1520@gmail.com
 * @date 2018/3/10  18:12
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class LotteryTest {

    String token = "eyJhbGciOiJIUzUxMiJ9.eyJhdWRpZW5jZSI6IndlYiIsInlidG9rZW4iOiIiLCJhcHBuYW1lIjoiY29tbWl0IiwiY3JlYXRlZCI6MTUyMDY4MzIyNTU3MSwieWJpZCI6NTgzMTQ0OSwiZXhwIjoxNTIxMjg4MDI1fQ.6ev1MzzN6vm6QjIeQMMQoI8-JoqE8zQMFs8jv83pDN_bLZIabn_T-7xUuS9zj_KOLCRdWHcRCL1yckOKdJVLDw";


    @Autowired
    private LotteryService lotteryService;

    @Autowired
    private LotteryListRepository lotteryListRepository;


    @Autowired
    PrizeRepository prizeRepository;

    @Autowired
    LotteryUserService userService;

    @Test
    public void ff() {

        LotteryList lotteryList = new LotteryList();
        lotteryList.setLotteryname("ninini");
        lotteryList.setLotteryintro("intrpo");
        lotteryList.setIspass(1);
        lotteryList.setPasscode(123);
        lotteryList.setLotterytimebegin(new Timestamp(System.currentTimeMillis()));
        lotteryList.setLotterytimeend(new Timestamp(System.currentTimeMillis() + 800000));
        Prize prize = new Prize();
        prize.setTotalNumber(22);
        prize.setCreatorId(1);
        prize.setPrizeName("fffjjfjf");
        List<Prize> prizes = new ArrayList<Prize>();
        prizes.add(prize);
        lotteryList.setPrizes(prizes);
        prizeRepository.save(prize);
        lotteryListRepository.save(lotteryList);
    }


    @Test
    public void fffff() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        System.out.println(timestamp.toString());
        System.out.println(timestamp.toString());

        timestamp = Timestamp.valueOf("2018-03-10 20:41:56.427");
        System.out.println("hhfhfhhfhf" + timestamp);

        LotteryList lotteryList = new LotteryList();
        lotteryList.setLotterytimebegin(new Timestamp(System.currentTimeMillis()));

        lotteryList.setLotterytimeend(Timestamp.valueOf("2018-04-10 20:41:56.427"));

        lotteryList.setPasscode(123456);
        lotteryList.setIspass(1);

        Prize prize = new Prize();
        prize.setTotalNumber(33);
        prize.setPrizeName("fsdjkfhd");
        lotteryListRepository.save(lotteryList);

        prize.setLotteryId(lotteryList.getId());
        System.out.println("  该抽奖的ID是"+ lotteryList.getId());

        lotteryService.getLotteryList();


    }


    @Test
    public void ffff() {

        LotteryService.Create create = lotteryService.new Create();
        create.token = "eyJhbGciOiJIUzUxMiJ9.eyJhdWRpZW5jZSI6IndlYiIsInlidG9rZW4iOiIiLCJhcHBuYW1lIjoiY29tbWl0IiwiY3JlYXRlZCI6MTUyMDc1NTgwOTU1OCwieWJpZCI6NTgzMTQ0OSwiZXhwIjoxNTIxMzYwNjA5fQ.-WeE2TQ3NeuEs1Du6Yd2JTuebXxsegJxJ_lc2yKgim1rg635HpmyxqXW6Kpmopmmni2rLqH4-HrG78WzJS-ISQ";
        Prize prize = new Prize();
        List<Prize> prizes = new ArrayList<>();
        prize.setPrizeName("hfhfhhfh");
        prize.setCreatorId(22222);
        prize.setTotalNumber(22);
        for (int i = 0; i < 10; i++) {
            prize.setTotalNumber(i);

            prizeRepository.save(prize);

            prizes.add(prize);

        }
        create.prizes = prizes;
        create.lotteryintro = "dfhnasdhfuiayhsdf";
        create.lotteryname = "kfjhdsjfhajs";
        create.username = "lilkiafh";

        lotteryService.createLottery(create);
    }

    @Test
    public void okfj() {

        lotteryService.getLotteryList();
    }

}
