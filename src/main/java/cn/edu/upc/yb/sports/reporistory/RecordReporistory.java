package cn.edu.upc.yb.sports.reporistory;

import cn.edu.upc.yb.sports.model.Record;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @author liliangbin dumpling1520@gmail.com
 * @date 2018/5/14  22:55
 */
public interface RecordReporistory  extends CrudRepository<Record,Integer> {

}
