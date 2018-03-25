package cn.edu.upc.yb.match.repository;

import cn.edu.upc.yb.match.model.Team;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamRepository extends CrudRepository<Team, String> {
    Team findById (int id);
    List<Team> findAll();
}
