package cn.edu.upc.yb.lottery.repository;

import cn.edu.upc.yb.lottery.model.Prize;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @author liliangbin dumpling1520@gmail.com
 * @date 2018/2/22  9:31
 */
public interface  PrizeRepository  extends CrudRepository<Prize,Long>{
    List<Prize> findAllByCreatorId(long creatorId);
}
