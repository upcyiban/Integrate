package cn.edu.upc.yb.secondhand.repository;

import cn.edu.upc.yb.secondhand.model.Collection;
import org.springframework.data.repository.CrudRepository;

public interface CollectionRepository extends CrudRepository<Collection,Integer>{

    Iterable<Collection> findByArticleIdOrderByCreateTime(int articleId);
    Iterable<Collection> findByUserIdOrderByCreateTime(int userId);

}
