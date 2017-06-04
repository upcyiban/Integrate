package cn.edu.upc.yb.common.security.model;

import org.springframework.data.repository.CrudRepository;

/**
 * Created by lylll on 2017/6/3.
 */
public interface AuthorityRepository extends CrudRepository<Authority,Integer>{
    Authority findFirstByName(String name);
}
