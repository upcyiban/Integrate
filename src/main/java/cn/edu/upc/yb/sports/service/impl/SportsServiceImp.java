package cn.edu.upc.yb.sports.service.impl;

import cn.edu.upc.yb.sports.model.SportsUser;
import cn.edu.upc.yb.sports.reporistory.SportsReporistory;
import cn.edu.upc.yb.sports.service.SportsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 * @author liliangbin dumpling1520@gmail.com
 * @date 2018/5/12  18:56
 */
@Service
public class SportsServiceImp implements SportsService {
    @Autowired
    private SportsReporistory sportsReporistory;

    @Override
    public Object addUser(String project, String username, String ranking, String score, long ScoreOrder) {
        SportsUser sportsUser = new SportsUser();
        sportsUser.setOrd(ScoreOrder);
        sportsUser.setProject(project);
        sportsUser.setRanking(ranking);
        sportsUser.setUsername(username);
        sportsReporistory.save(sportsUser);
        return "创建成功";
    }

    @Override
    public Object findByUsername(String username) {


        return sportsReporistory.findAllByUsername(username,new Sort(Sort.Direction.ASC, "ord"));
    }

    @Override
    public Object findByProject(String project) {

        return sportsReporistory.findAllByProject(project, new Sort(Sort.Direction.ASC, "ord"));
    }
}

/*
*
* DESC 降序排列
*
*
* ASC  代表升序排列*/