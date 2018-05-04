package cn.edu.upc.yb.secondhand.repository;

import cn.edu.upc.yb.secondhand.model.SecondReview;
import org.springframework.data.repository.CrudRepository;

public interface ReviewRepository extends CrudRepository<SecondReview,Integer> {

    Iterable<SecondReview> findByArticleIdAndIsdeleteOrderByCreatetimeDesc(int articleId, int isdelelte);
    Iterable<SecondReview> findByYbidAndIsdeleteOrderByCreatetimeDesc(int userId, int isdelete);

}
