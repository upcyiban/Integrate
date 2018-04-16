package cn.edu.upc.yb.secondhand.repository;

import cn.edu.upc.yb.secondhand.model.Article;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ArticleRepository extends CrudRepository<Article,Integer> {

    Iterable<Article> findByIsdealOrderByCreatetimeDesc(int isdeal);

    List<Article> findByUseridOrderByCreatetimeDesc(int userId);
}
