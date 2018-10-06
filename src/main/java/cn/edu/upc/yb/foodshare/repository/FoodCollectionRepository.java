package cn.edu.upc.yb.foodshare.repository;

import cn.edu.upc.yb.foodshare.model.FoodCollection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FoodCollectionRepository extends CrudRepository<FoodCollection,Long> {

     int countByFoodid(int foodid);

     FoodCollection findByFoodidAndUserid(int foodId, int userId);

    List<FoodCollection> findByUseridOrderByCreatetime(int userId);
}
