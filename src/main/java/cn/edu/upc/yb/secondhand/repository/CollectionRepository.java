package cn.edu.upc.yb.secondhand.repository;

import cn.edu.upc.yb.secondhand.model.Collection;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CollectionRepository extends CrudRepository<Collection,Integer>{

    Iterable<Collection> findByArticleIdOrderByCreateTimeDesc(int articleId);
    List<Collection> findByUserIdOrderByCreateTimeDesc(int userId);
    Collection findByUserIdAndArticleId(int userid,int articleid);

}
