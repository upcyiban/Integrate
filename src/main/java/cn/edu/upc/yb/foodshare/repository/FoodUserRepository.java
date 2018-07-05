package cn.edu.upc.yb.foodshare.repository;

import cn.edu.upc.yb.foodshare.model.FoodUser;
import org.springframework.data.repository.CrudRepository;

public interface FoodUserRepository extends CrudRepository<FoodUser,Integer> {

    FoodUser findByUserid(int userid);//根据易班id返回用户
}
