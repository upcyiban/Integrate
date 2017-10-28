package cn.edu.upc.yb.speaktoteacher.dao;

import cn.edu.upc.yb.speaktoteacher.model.Message;
import org.springframework.data.repository.CrudRepository;

public interface MessageRepository extends CrudRepository<Message,Integer> {



}
