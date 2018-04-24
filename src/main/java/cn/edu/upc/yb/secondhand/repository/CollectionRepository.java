package cn.edu.upc.yb.secondhand.repository;

import cn.edu.upc.yb.secondhand.model.Collection;
import org.springframework.data.repository.CrudRepository;

public interface CollectionRepository extends CrudRepository<Collection,Integer>{

    Iterable<Collection> findByArticleIdOrderByCreateTimeDesc(int articleId);
    Iterable<Collection> findByUserIdOrderByCreateTimeDesc(int userId);
    Collection findByUserIdAndArticleId(int userid,int articleid);

}
