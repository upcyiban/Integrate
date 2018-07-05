package cn.edu.upc.yb.foodshare.repository;

import cn.edu.upc.yb.foodshare.model.FoodCollection;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodCollectionRepository extends JpaRepository<FoodCollection,Long> {

     int countByFoodid(int foodid);

     FoodCollection findByFoodidAndUserid(int foodId, int userId);
}
