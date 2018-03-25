package cn.edu.upc.yb.match.controller;

import cn.edu.upc.yb.match.repository.TeamRepository;
import com.sun.deploy.net.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import cn.edu.upc.yb.match.model.Team;

import java.util.List;

@RestController

public class MatchController {

    @Qualifier("teamRepository")
    @Autowired
    TeamRepository teamRepository;

    @RequestMapping(value = "/match/apply", method = RequestMethod.POST)
    @GetMapping("/index")
    public void createTeam(String teamName, String captainName, String tel, String email, String qq,
                                   String  college, String rank, String member1Name, String member1College,
                                   String member1Rank, String member2Name, String member2College, String member2Rank,
                                   String ideaName, String instructions){
        Team team = new Team(teamName, captainName ,tel , email, qq, college, rank, member1Name, member1College, member1Rank, member2Name, member2College, member2Rank, ideaName, instructions);
        teamRepository.save(team);
    }

    @RequestMapping(value = "/match/showAll",method = RequestMethod.GET)
    public List<Team> teamList(){
        return teamRepository.findAll();
    }
}
