package cn.edu.upc.yb.sports.controller;

import cn.edu.upc.yb.sports.reporistory.ProjectReporisorty;
import cn.edu.upc.yb.sports.service.SportsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liliangbin dumpling1520@gmail.com
 * @date 2018/5/12  18:44
 */
@RestController
@RequestMapping("/sports")
public class SportsController {

    @Autowired
    private SportsService sportsService;

    @Autowired
    private ProjectReporisorty projectReporisorty;

    @PostMapping("/add")
    public Object sportsAddUser(String project,String username,String ranking ,String score ,long scoreOrder ,boolean outRecord){

       return sportsService.addUser(project,username,ranking,score,scoreOrder,outRecord);

    }

    @PostMapping("/user_ranking")
    public Object ranking(String username){
        return sportsService.findByUsername(username);

    }

    @PostMapping("/all_ranking")
    public Object rankingProject(String project){

        return sportsService.findByProject(project);
    }


    @PostMapping("/project")
    public Object getProject(int groupId){

        return projectReporisorty.findAllByGroupId(groupId,new Sort(Sort.Direction.ASC,"projectId"));
    }

    @PostMapping("/modify")
    public Object modify(String project,String username,String ranking ,String score ,long scoreOrder ,boolean outRecord){

        return sportsService.modifyUser(project,username,ranking,score,scoreOrder,outRecord);
    }

    @PostMapping("/delete_user")
    public Object deleteuser (String project,String username){


        return sportsService.deleteUser(project,username);
    }


}
