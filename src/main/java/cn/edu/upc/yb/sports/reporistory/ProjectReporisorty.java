package cn.edu.upc.yb.sports.reporistory;

import cn.edu.upc.yb.sports.model.Project;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @author liliangbin dumpling1520@gmail.com
 * @date 2018/5/12  19:30
 */
public interface ProjectReporisorty extends CrudRepository<Project,Integer> {

   List<Project> findAllByGroupId(int groupId, Sort sort);



}
