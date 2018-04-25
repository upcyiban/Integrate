package cn.edu.upc.yb.secondhand.repository;

import cn.edu.upc.yb.secondhand.model.Article;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ArticleRepository extends CrudRepository<Article,Integer> {

    Iterable<Article> findByIsdealOrderByCreatetimeDesc(int isdeal);

    List<Article> findByUseridOrderByCreatetimeDesc(int userId);

    @Query("select a from Article a where a.name like CONCAT('%',:name,'%') and a.isdeal=0")
    List<Article> findByNameLike(@Param(value = "name") String name);

    List<Article> findByKindOrderByCreatetimeDesc(String kind);

}
