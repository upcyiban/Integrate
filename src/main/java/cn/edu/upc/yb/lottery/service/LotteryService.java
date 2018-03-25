package cn.edu.upc.yb.lottery.service;

import cn.edu.upc.yb.common.security.service.JwtTokenUtil;
import cn.edu.upc.yb.common.service.UserService;
import cn.edu.upc.yb.common.ybapi.UserRealMe;
import cn.edu.upc.yb.lottery.model.Creator;
import cn.edu.upc.yb.lottery.model.LotteryList;
import cn.edu.upc.yb.lottery.model.Prize;
import cn.edu.upc.yb.lottery.model.PrizeList;
import cn.edu.upc.yb.lottery.repository.CreatorRepository;
import cn.edu.upc.yb.lottery.repository.LotteryListRepository;
import cn.edu.upc.yb.lottery.repository.PrizeListRepository;
import cn.edu.upc.yb.lottery.repository.PrizeRepository;
import cn.edu.upc.yb.lottery.utils.ResponseBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.Timestamp;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liliangbin dumpling1520@gmail.com
 * @date 2018/2/22  10:32
 */
@Service
public class LotteryService {

    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    private UserService userService;

    @Autowired
    UserRealMe userRealMe;

    @Autowired
    private PrizeRepository prizeRepository;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private CreatorRepository creatorRepository;
    @Autowired
    private LotteryListRepository lotteryListRepository;
    @Autowired
    private PrizeListRepository prizeListRepository;

//时间上是不是能够满足抽奖

    public boolean canLottery(Timestamp lotterytimebegin, Timestamp lotterytimeend) {
        Timestamp date = new Timestamp(System.currentTimeMillis());
        if (date.after(lotterytimebegin) && date.before(lotterytimeend)) {
            return true;
        }
        return false;
    }

    // 该用户是不是已经抽过奖了
    public boolean LotteryCondition2(long yibanId) {
        List<PrizeList> result = prizeListRepository.findByYibanid(yibanId);
        return result.isEmpty();
    }


    //需要前台传过来的参数强制转换成Create类型的数据
    public Object createLottery(Create create) {
        String authToken = create.token;
        String lotteryname = create.lotteryname;
        String lotteryintro = create.lotteryintro;
        Timestamp timeStart = create.lotterytimebegin;
        Timestamp timeEnd = create.lotterytimeend;
        String yibanId = jwtTokenUtil.getYBidFromTocken(authToken);
        try {

            String userName = create.username;

            Creator creator = creatorRepository.findByYibanid(Long.valueOf(yibanId));
            if (creator == null) {
                creator = new Creator();
                creator.setYibanid(Long.valueOf(yibanId));
                creator.setYibanname(userName);
                creatorRepository.save(creator);
            }

            creator = creatorRepository.findByYibanid(Long.valueOf(yibanId));

            if (creator == null) {

                return new ResponseBean(-1, "未知用户", null);
            }

            LotteryList lotteryList = new LotteryList();
            lotteryList.setCreatorid(creator.getId());
            lotteryList.setLotteryname(lotteryname);
            lotteryList.setLotteryintro(lotteryintro);
            lotteryList.setLotterytimebegin(timeStart);
            lotteryList.setLotterytimeend(timeEnd);
            lotteryList.setIspass(1);
            lotteryList.setCreatetime(new java.sql.Date(System.currentTimeMillis()));

            if (create.prizes != null) {
                for (Prize prize : create.prizes) {


                    prizeRepository.save(prize);
                }
            }

            int passcode = (int) (Math.random() * 9 + 1) * 100000;
            lotteryList.setPasscode(passcode);
            lotteryListRepository.save(lotteryList);
            return new ResponseBean(1, "success", passcode);

        } catch (Exception e) {
            return new ResponseBean(-1, "未知错误", e.getMessage());
        }

    }

    public Object getUserInfo(HttpServletRequest request) throws IOException {
        String authToken = request.getParameter(tokenHeader);
        System.out.println("你得token是"+authToken);
        String yibanId = jwtTokenUtil.getYBidFromTocken(authToken);
        System.out.println("你的一般ID是" + yibanId);
        Creator creator = new Creator();
        creator.setYibanid(Long.valueOf(yibanId));

        creator.setYibanname((String) userService.getStuName(request));
        return new ResponseBean(1, "登陆用户的信息", creator);

    }

    public Object getLotteryList() {


        List<LotteryList> lists = new ArrayList<>();
        List<LotteryList> lotteryListLists = lotteryListRepository.findAll();
        if (lotteryListLists.isEmpty()) {

            return new ResponseBean(-1, "暂时没有抽奖", null);
        }

            for (LotteryList lotteryList : lotteryListLists) {
                Timestamp begin = lotteryList.getLotterytimebegin();
                Timestamp end = lotteryList.getLotterytimeend();

                System.out.println("进入循环了");
                if (begin == null || end == null) {
                    lists.add(lotteryList);
                    System.out.println("直接的时间上为null'的不允许参加抽奖");
                    break;
                }
                if (!canLottery(begin, end)) {

                   lists.add(lotteryList);
                }
            }
            lotteryListLists.remove(lists);
            return new ResponseBean(1, "获取所有满足时间要求的抽奖", lotteryListLists);



    }


    public Object doLottery(HttpServletRequest request) throws IOException {
        String tmp = request.getParameter("lotteryId");

        if (tmp == null) {
            return new ResponseBean(0, "非法请求", false);
        }

        long lotteryId = Long.valueOf(tmp);
        String authToken = request.getParameter(tokenHeader);
        String yibanId = jwtTokenUtil.getYBidFromTocken(authToken);
        String username = (String) userService.getStuName(request);

        if (!LotteryCondition2(Long.valueOf(yibanId))) {

            return new ResponseBean(-1, "你已经抽过奖了", false);
        }

        LotteryList lotteryList = lotteryListRepository.findOne(lotteryId);
        //传过来一个抽奖
        List<Prize> prizes = prizeRepository.findAllByCreatorId(lotteryList.getCreatorid());
        return dealLottery(prizes, Long.valueOf(yibanId), username, lotteryId);

    }

    public Object isPass(long lotteryId, int passcode) {
        LotteryList lotteryList = lotteryListRepository.findOne(lotteryId);
        if (lotteryList == null) {
            return new ResponseBean(-1, "没有该抽奖", false);
        }
        if (lotteryList.getPasscode() == passcode) {
            //这个时候就是验证了验证码是否正确后。
            return new ResponseBean(1, "验证码正确", true);

        } else {

            return new ResponseBean(-2, "验证码有问题，请重新输入", false);
        }


    }

    //默认不是创建者
    public Object dealLottery(List<Prize> prizes, long yibanid, String username, long lotteryId) {


        PrizeList prizeList = prizeListRepository.findByYibanidAndLotteryid(yibanid, lotteryId);

        if (prizeList != null) {

            return new ResponseBean(-1, "你已经参加过该抽奖", null);

        }

        System.out.println(lotteryId + "抽奖得ID");
        Prize prize = randomPrize(prizes);
        if (prize == null) {

            PrizeList prizeList1 = new PrizeList();
            prizeList1.setLotteryid(lotteryId);
            System.out.println("村粗");
            prizeList1.setYibanid(yibanid);
            prizeList1.setYibanname(username);
            prizeListRepository.save(prizeList1);
            return new ResponseBean(-2, "很遗憾你没有中奖", null);
        } else {
            prize.setTotalNumber(prize.getTotalNumber() - 1);
            PrizeList prizeList1 = new PrizeList();
            prizeRepository.save(prize);
            //.这个时候需要我把数据保存下来
            prizeList1.setLotteryid(lotteryId);
            prizeList1.setYibanid(yibanid);
            prizeList1.setYibanname(username);
            prizeList1.setPrizeName(prize.getPrizeName());
            prizeListRepository.save(prizeList1);
            return new ResponseBean(1, "恭喜你获奖了", prize);

        }


    }


    public Object findbycode(int passcode) {

        return lotteryListRepository.findAllByPasscode(passcode);
    }

    //用于检测随机数。
    public Prize randomPrize(List<Prize> prizes) {

        int total = 0;
        int code = (int) Math.random() * 1000;

        for (Prize prize : prizes) {
            total += Integer.valueOf(prize.getPrizePercentage());
            if (total > code) {

                if (prize.getTotalNumber() == 0) {

                    randomPrize(prizes);

                }
                return prize;
            }
        }
        return null;

    }

    public Object message(long lotteryId) {
        List<PrizeList> prizeLists = prizeListRepository.
                findAllByLotteryid(lotteryId);
        try {
            for (PrizeList prizeList : prizeLists) {

                if (prizeList.getPrizeName() == null) {
                    prizeLists.remove(prizeList);
                }
            }

            return prizeLists;
        } catch (Exception e) {

            return e.getMessage();

        }

    }

    public class Create {
        public String token;
        public String username;//realname
        public String lotteryname;
        public String lotteryintro;
        public Timestamp lotterytimebegin;
        public Timestamp lotterytimeend;
        public List<Prize> prizes;//表示一个抽奖的奖项是可以叠加的。。。。。
    }


}

