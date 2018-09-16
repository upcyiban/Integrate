package cn.edu.upc.yb.foodshare.repository;

import cn.edu.upc.yb.foodshare.model.FoodReview;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public interface FoodReviewRepository extends CrudRepository<FoodReview,Long> {

    int countByFoodidAndIsdelete(int foodid,int isDelete);

    //获取一个用户评论过的菜品
    HashSet<FoodReview> findByUseridAndIsdelete(int userId,int isDelete);

    //获取一个菜品未被删除的评论
    Page<FoodReview> findByFoodidAndIsdeleteOrderByCreatetimeDesc(int foodId,int isdelete, Pageable pageable);
}
