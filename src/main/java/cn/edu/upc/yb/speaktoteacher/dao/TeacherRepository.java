package cn.edu.upc.yb.speaktoteacher.dao;

import cn.edu.upc.yb.speaktoteacher.model.Teacher;
import org.springframework.data.repository.CrudRepository;

public interface TeacherRepository extends CrudRepository<Teacher,Integer> {

public Object findFirstByYibanId(int YibanId);
public  Object findByName(String name);
}
