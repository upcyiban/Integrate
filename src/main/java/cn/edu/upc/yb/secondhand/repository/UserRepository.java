package cn.edu.upc.yb.secondhand.repository;

import cn.edu.upc.yb.secondhand.model.SecondUser;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<SecondUser,Integer>{

    SecondUser findByUserid(int userId);

}
