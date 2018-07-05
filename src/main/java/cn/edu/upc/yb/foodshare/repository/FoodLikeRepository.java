package cn.edu.upc.yb.foodshare.repository;

import cn.edu.upc.yb.foodshare.model.FoodLike;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodLikeRepository extends JpaRepository<FoodLike,Integer> {

    int countByFoodid(int foodid);

    FoodLike findByUseridAndFoodid(int userid,int foodid);

//    Page<FoodLike> findByUseridAndOrderByCreatetimeDesc(int userid,Pageable pageable);
}
