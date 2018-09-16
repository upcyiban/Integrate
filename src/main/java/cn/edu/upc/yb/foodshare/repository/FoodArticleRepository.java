package cn.edu.upc.yb.foodshare.repository;

import cn.edu.upc.yb.foodshare.model.FoodArticle;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

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

    //按标签搜索，返回满足查询条件以及在发布状态的菜品，一次分页查询，一次十个（如果返回的标签能返回数字，可以在controller里写一个函数将
    // 前端的标签分离为单个数字以%为间隔）
    Page<FoodArticle> findByStateAndKindLikeOrderByCreatetimeDesc(int state,String kind,Pageable pageable);

    //按菜品的名称搜索或者是关键字，依旧是分页查询，一次十个
    Page<FoodArticle> findByStateAndNameLikeOrderByCreatetimeDesc(int state,String name,Pageable pageable);

    //浏览已发布的菜品,前端查询返回的菜品
    Page<FoodArticle> findByStateOrderByCreatetime(int state,Pageable pageable);
}
