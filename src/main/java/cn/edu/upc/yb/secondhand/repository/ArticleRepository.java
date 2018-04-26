package cn.edu.upc.yb.secondhand.repository;

import cn.edu.upc.yb.secondhand.model.SecondArticle;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ArticleRepository extends CrudRepository<SecondArticle,Integer> {

    Iterable<SecondArticle> findByIsdealOrderByCreatetimeDesc(int isdeal);

    List<SecondArticle> findByUseridOrderByCreatetimeDesc(int userId);

    @Query("select a from SecondArticle a where a.name like CONCAT('%',:name,'%') and a.isdeal=0")
    List<SecondArticle> findByNameLike(@Param(value = "name") String name);

    List<SecondArticle> findByKindOrderByCreatetimeDesc(String kind);

}
