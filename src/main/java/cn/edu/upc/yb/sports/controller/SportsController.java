package cn.edu.upc.yb.sports.controller;

import cn.edu.upc.yb.sports.reporistory.ProjectReporisorty;
import cn.edu.upc.yb.sports.service.SportsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
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
//用户添加界面，添加了recordInfo。。可以直接。
    @PostMapping("/add")
    public Object sportsAddUser(String project,String username,String ranking ,String score ,long scoreOrder ,boolean outRecord,String recordInfo){

       return sportsService.addUser(project,username,ranking,score,scoreOrder,outRecord,recordInfo);

    }

//用户参加的所有项目的成绩
    @PostMapping("/user_ranking")
    public Object ranking(String username){

        return sportsService.findByUsername(username);
    }
//某个项目的所有排名
    @PostMapping("/all_ranking")
    public Object rankingProject(String project){

        return sportsService.findByProject(project);

    }
//通过groupId返回所有的项目
    @PostMapping("/project")
    public Object getProject(int groupId){

        return projectReporisorty.findAllByGroupId(groupId,new Sort(Sort.Direction.ASC,"projectId"));

    }
//通过Id删用户。
    @PostMapping("/delete_user")
    public Object deleteUser (int id){
        return sportsService.deleteUser(id);
    }

//返回所有的打破记录的数据
    @GetMapping("/record")
    public Object findAllRecord(){

        return sportsService.findAllOutRecord(true);


    }




}
