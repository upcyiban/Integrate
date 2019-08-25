package cn.edu.upc.yb.innersystem.repository;

import cn.edu.upc.yb.innersystem.model.Task;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TaskRepository extends CrudRepository <Task, Integer> {
    Task findFirstById(Integer id);
    List<Task> findAllByDepartmentAndIsDeleted(String department, Integer isDeleted);
}
