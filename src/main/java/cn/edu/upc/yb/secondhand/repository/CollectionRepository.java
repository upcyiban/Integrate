package cn.edu.upc.yb.secondhand.repository;

import cn.edu.upc.yb.secondhand.model.SecondCollection;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CollectionRepository extends CrudRepository<SecondCollection,Integer>{

    Iterable<SecondCollection> findByArticleIdOrderByCreateTimeDesc(int articleId);
    List<SecondCollection> findByUserIdOrderByCreateTimeDesc(int userId);
    SecondCollection findByUserIdAndArticleId(int userid, int articleid);

}
