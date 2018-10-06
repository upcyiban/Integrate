package cn.edu.upc.yb.foodshare.repository;

import cn.edu.upc.yb.foodshare.model.FoodArticle;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created By Kazusa in 2018/7/6 14:32
 */
public interface FoodArticleRepository extends CrudRepository<FoodArticle,Integer> {
    // 返回用户尚未查看的已通过审核或者未通过的菜品数
    Long countByUseridAndIscheckAndStateIn(int userid,int check,int[] states);

    //根据美食ID获取一个在state为？的美食
    FoodArticle findByIdAndState(int foodid,int state);

    //返回用户发布的处于某种状态的菜品信息
    List<FoodArticle> findByUseridAndStateInOrderByCreatetimeDesc(int userid, int[] states);

    //返回用户点赞、收藏、评论的菜品信息
    List<FoodArticle> findByIdInAndState(List<Integer> foodid,int states);

    //按种类搜索，返回满足查询条件以及在发布状态的菜品
    @Query("select a from FoodArticle a where a.kind like CONCAT('%',:kind,'%') and a.state=0 order by a.createtime DESC")
    List<FoodArticle> findByStateAndKindLikeOrderByCreatetimeDesc(@Param(value = "kind")String kind);
    //按菜品的名称搜索,按时间倒序排列
    @Query("select a from FoodArticle a where a.name like CONCAT('%',:name,'%') and a.state=0 order by a.createtime DESC")
    List<FoodArticle> findByNameLikeAndStateOrOrderByCreatetime(@Param(value = "name")String name);

    //浏览已发布的菜品,前端查询返回的菜品
    Page<FoodArticle> findByStateOrderByCreatetime(int state,Pageable pageable);
}
