package cn.edu.upc.yb.secondhand.repository;

import cn.edu.upc.yb.secondhand.model.SecondArticle;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ArticleRepository extends CrudRepository<SecondArticle,Integer> {

    Iterable<SecondArticle> findByIsdealOrderByCreatetimeDesc(int isdeal);

    Page<SecondArticle> findByIsdealOrderByCreatetimeDesc(int isdeal, Pageable pageable);

    List<SecondArticle> findByUseridOrderByCreatetimeDesc(int userId);

    @Query("select a from SecondArticle a where a.name like CONCAT('%',:name,'%') and a.isdeal=0 order by a.createtime DESC")
    List<SecondArticle> findByNameLike(@Param(value = "name") String name);

    @Query("select a  from SecondArticle a  where a.kind like CONCAT('%',:kind,'%') and a.isdeal=0 order by a.createtime DESC " )
    List<SecondArticle> findByKindOrderByCreatetimeDesc(@Param(value = "kind") String kind);

}
