package cn.edu.upc.yb.foodshare.repository;

import cn.edu.upc.yb.foodshare.model.FoodArticle;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface FoodArticleRepository extends CrudRepository<FoodArticle,Integer> {

    Long countByUseridAndCheckAndStateIn(int userid,boolean check,int[] states);//根据易班id、是否被检查、是否被删除
    // 目返回用户尚未查看的已通过审核的菜品数

    //根据美食ID获取一个在state为？的美食
    FoodArticle findByIdAndState(int ybid,int state);

    Page<FoodArticle> findByUseridAndStateInOrderByCreatetimeDesc(int userid,int[] states,Pageable pageable);//根据状态、id、时间返回用户发布的菜品信息

    //根据标签和点赞数分页查询

    List<FoodArticle> findByUseridAndStateIn(int userid,int[] states);

    //SQL语句查询，按照用户点赞的时间降序并且在发布中的美食
    @Query(value = "select food.* from foodShare_food food,foodShare_like likes where" +
            " food.food_id = likes.food_id and likes.user_id = ?1 and food.state = 0 order by " +
            "likes.create_time desc",
            countQuery = "select count(*) from test_food food,test_like likes where " +
                    "food.food_id = likes.food_id and likes.user_id = ?1 and food.state = 0",nativeQuery = true)
    Page<FoodArticle> findBylike(int userid,Pageable pageable);

    //SQL语句查询，按照用户收藏的时间降序并且在发布中的美食
    @Query(value = "select food.* from foodShare_food food,foodShare_collection collection where"+
            " food.food_id = collection.food_id and collection.user_id = ?1 and food.state = 0 order by " +
            " collection.create_time desc",
            countQuery = "select count(*) from foodShare_food food,foodShare_collection collection where "+
                    " food.food_id = collection.food_id and collection.user_id = ?1 and food.state = 0 "
            ,nativeQuery = true)
    Page<FoodArticle> findByCollection(int userid,Pageable pageable);

    //获取评论过的菜品
    Page<FoodArticle> findByIdInAndState(Set set, int state, Pageable pageable);
//    @Query(value = "select DISTINCT food.* from foodShare_food food,foodShare_review review where"+
//            " food.food_id = review.food_id and review.user_id = ?1 and food.state = 0 order by " +
//            " collection.create_time desc",
//            countQuery = "select count( DISTINCT food.food_id) from foodShare_food food,foodShare_review review where "+
//                    " food.food_id = review.food_id and review.user_id = ?1 and food.state = 0 "
//            ,nativeQuery = true)
//    Page<FoodArticle> findByReview(int userid,Pageable pageable);


}
