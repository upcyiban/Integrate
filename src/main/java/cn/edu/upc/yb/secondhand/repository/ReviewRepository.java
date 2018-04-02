package cn.edu.upc.yb.secondhand.repository;

import cn.edu.upc.yb.secondhand.model.Review;
import org.springframework.data.repository.CrudRepository;

public interface ReviewRepository extends CrudRepository<Review,Integer> {

    Iterable<Review> findByArticleIdAndIsdeleteOrderByCreatetimeDesc(int articleId,int isdelelte);
    Iterable<Review> findByYbidAndIsdeleteOrderByCreatetimeDesc(int userId,int isdelete);

}
