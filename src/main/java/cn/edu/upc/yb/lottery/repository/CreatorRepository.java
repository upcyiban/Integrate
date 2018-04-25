package cn.edu.upc.yb.lottery.repository;

import cn.edu.upc.yb.lottery.model.Creator;
import org.springframework.data.repository.CrudRepository;

/**
 * @author liliangbin dumpling1520@gmail.com
 * @date 2018/2/22  9:30
 */
public interface CreatorRepository extends CrudRepository<Creator, Long> {

    Creator findByYibanid(long yibanId);

}
