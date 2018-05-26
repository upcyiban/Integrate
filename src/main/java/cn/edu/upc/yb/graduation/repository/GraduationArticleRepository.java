package cn.edu.upc.yb.graduation.repository;

import cn.edu.upc.yb.graduation.model.Article;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

/**
 * @author liliangbin dumpling1520@gmail.com
 * @date 2018/5/24  17:30
 */
public interface GraduationArticleRepository extends CrudRepository<Article, Integer> {


    Article findById(long id);

    Iterable<Article> findTop10ByOrderById();

    Iterable<Article> findAll(Sort sort);

    Iterable<Article> findAll();


    @Transactional
    @Modifying
    @Query(value = "SELECT * FROM graduation_article  ORDER BY  RAND() LIMIT 16", nativeQuery = true)
    Iterable<Article> theBest16();


}
