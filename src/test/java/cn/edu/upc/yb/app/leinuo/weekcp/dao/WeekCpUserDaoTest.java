package cn.edu.upc.yb.app.leinuo.weekcp.dao;

import cn.edu.upc.yb.app.leinuo.weekcp.entity.WeekCpUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WeekCpUserDaoTest {

    @Autowired WeekCpUserDao userDao;

    @Test
    public void mainTest() throws Exception {
        System.out.println(userDao.findAll().toString());
        System.out.println(userDao.getAllByCpAndDeleted(0,0).toString());
        System.out.println(userDao.getWeekCpUserByUserId(1).toString());
        System.out.println(userDao.findAll());
    }

    @Test
    public void update()throws Exception{
        WeekCpUser user = new WeekCpUser("雷诺1","11429018626@qq.com","计算1501",1,0,"1234156789");
        user.setUserId(6);
        userDao.save(user);
        System.out.println(userDao.getWeekCpUserByUserId(6));
    }

    @Test
    public void insert()throws Exception{
        WeekCpUser user = new WeekCpUser("123","123","123",1,1,"1231");
        userDao.save(user);
        System.out.println(userDao.findAll());
    }

}