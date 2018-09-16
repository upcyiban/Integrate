package cn.edu.upc.yb.foodshare.repository;

import cn.edu.upc.yb.foodshare.model.FoodLike;
import io.swagger.models.auth.In;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FoodLikeRepository extends CrudRepository<FoodLike,Integer> {

    int countByFoodid(int foodid);

    FoodLike findByUseridAndFoodid(int userid,int foodid);

    List<Integer> findFoodidByUseridOrderByCreatetime(int userid);

}
