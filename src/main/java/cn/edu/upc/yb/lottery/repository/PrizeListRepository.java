package cn.edu.upc.yb.lottery.repository;

import cn.edu.upc.yb.lottery.model.PrizeList;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @author liliangbin dumpling1520@gmail.com
 * @date 2018/2/22  9:33
 */
public interface PrizeListRepository extends CrudRepository<PrizeList,Long> {

    List<PrizeList> findAllByLotteryid(long lotteryId );

    List<PrizeList> findByYibanid(long yibanId );
    PrizeList findByYibanidAndLotteryid(long yibanId,long lotteryid);



}
