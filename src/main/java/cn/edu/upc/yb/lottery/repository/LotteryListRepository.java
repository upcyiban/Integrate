package cn.edu.upc.yb.lottery.repository;

import cn.edu.upc.yb.lottery.model.LotteryList;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @author liliangbin dumpling1520@gmail.com
 * @date 2018/2/22  9:32
 */
public interface LotteryListRepository extends CrudRepository<LotteryList, Long> {

    //主页的时候需要所有ispass = 1 ，，时间还有范围内的数据。

    List<LotteryList> findAll();

    List<LotteryList> findAllByIspass(int ispass);
    LotteryList findById(long lotteryId);

   List<LotteryList> findAllByCreatorid(long creatorId);


}
