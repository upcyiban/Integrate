package cn.edu.upc.yb.sports.reporistory;

import cn.edu.upc.yb.sports.model.SportsUser;
import cn.edu.upc.yb.sports.service.SportsService;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @author liliangbin dumpling1520@gmail.com
 * @date 2018/5/12  18:39
 */
public interface SportsReporistory extends CrudRepository<SportsUser,Integer> {
    List<SportsUser> findAllByUsername(String username,Sort sort);
    List<SportsUser> findAllByProject(String project,Sort sort);

    SportsUser findByUsernameAndAndProject(String  username,String project);
}
