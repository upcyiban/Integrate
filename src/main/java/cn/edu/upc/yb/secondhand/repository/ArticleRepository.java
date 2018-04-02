package cn.edu.upc.yb.secondhand.repository;

import cn.edu.upc.yb.secondhand.model.Article;
import org.springframework.data.repository.CrudRepository;

public interface ArticleRepository extends CrudRepository<Article,Integer> {

    Iterable<Article> findByIsdealOrderByCreatetimeDesc(int isdeal);

    Iterable<Article> findByUseridOrderByCreatetimeDesc(int userId);
}
