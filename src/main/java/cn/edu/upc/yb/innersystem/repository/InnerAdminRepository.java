package cn.edu.upc.yb.innersystem.repository;

import cn.edu.upc.yb.innersystem.model.InnerAdmin;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface InnerAdminRepository extends CrudRepository<InnerAdmin, Integer> {
    InnerAdmin findFirstByYbuid(String ybuid);
    List<InnerAdmin> findAll();
    InnerAdmin findFirstById(Integer id);
}
