package cn.edu.upc.yb.foodshare.repository;

import cn.edu.upc.yb.foodshare.model.FoodReview;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FoodReviewRepository extends JpaRepository<FoodReview,Long> {

    int countByFoodidAndDelete(int foodid,Boolean delete);

    List<FoodReview> findByUseridAndDelete(int userid,Boolean isdelete);


    Page<FoodReview> findByFoodidAndAndDeleteAndOrderByCreatetimeDesc(int foodId, Boolean delete, Pageable pageable);
}
