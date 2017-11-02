package cn.edu.upc.yb.app.leinuo.weekcp.dao;

import cn.edu.upc.yb.app.leinuo.weekcp.entity.WeekCpMatch;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WeekCpMatchDaoTest {

    @Autowired
    private WeekCpMatchDao matchDao;
    @Test
    public void getMatchByUser() throws Exception {
        Integer userId = 7;
        List<WeekCpMatch> matchList = matchDao.getMatchByUser(userId);
        System.out.println(matchList.toString());
    }

    @Test
    public void getMatchByMatchMap() throws Exception {
        WeekCpMatch match = new WeekCpMatch(10001,10002);
        WeekCpMatch cpMatch = matchDao.getMatchByMatchMap(match);
        System.out.println(cpMatch.toString());
    }


    @Test
    public void getMatchList() throws Exception {
        System.out.println(matchDao.getMatchList().toString());
    }

    @Test
    public void getMatchById() throws Exception {
        Integer matchId = 10000;
        System.out.println(matchDao.getMatchById(matchId));
    }

    @Test
    public void addMatchTest()throws Exception {
        Integer cpId0 = 10001;
        Integer cpId1 = 10002;
        matchDao.addMatch(cpId0 , cpId1);
    }
    @Test
    public void updateMatchById() throws Exception {
        WeekCpMatch match = new WeekCpMatch(10000,10002);
        matchDao.updateMatchById(10001,match);
    }

    @Test
    public void deleteMatchById() throws Exception {
        Integer matchId = 10001;
        matchDao.deleteMatchById(matchId);
        this.getMatchList();


    }

    @Test
    public void reliefMatchById() throws Exception {
        Integer matchId = 10001;
        matchDao.reliefMatchById(matchId);
        this.getMatchList();
    }

}