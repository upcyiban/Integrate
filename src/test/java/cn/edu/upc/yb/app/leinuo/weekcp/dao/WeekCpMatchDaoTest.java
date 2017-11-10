package cn.edu.upc.yb.app.leinuo.weekcp.dao;

import cn.edu.upc.yb.app.leinuo.weekcp.entity.WeekCpMatch;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class WeekCpMatchDaoTest {

    @Autowired
    private WeekCpMatchDao matchDao;

    @Test
    public void testMain() throws Exception{
        System.out.println(matchDao.findAll().toString());
        System.out.println(matchDao.getWeekCpMatchByCpIdLeft(1));
        System.out.println(matchDao.getWeekCpMatchByCpIdRight(2));
        System.out.println(matchDao.getWeekCpMatchByCpIdLeftAndCpIdRight(1,2));
    }
    @Test
    public void insert() throws Exception{
        WeekCpMatch match = new WeekCpMatch(4,2);
        matchDao.save(match);
        this.testMain();
    }
}