package cn.edu.upc.yb.secondhand.repository;

import cn.edu.upc.yb.secondhand.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,Integer>{

    User findByUserid(int userId);

}
