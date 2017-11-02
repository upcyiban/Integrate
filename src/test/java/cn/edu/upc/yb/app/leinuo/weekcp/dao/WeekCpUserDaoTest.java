package cn.edu.upc.yb.app.leinuo.weekcp.dao;

import cn.edu.upc.yb.app.leinuo.weekcp.entity.WeekCpUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WeekCpUserDaoTest {
    @Test
    public void getUserByYibanId() throws Exception {

    }

    @Autowired
    private WeekCpUserDao userDao;

    @Test
    public void reliefUserById() throws Exception {
        Integer userId = 10001;
        userDao.reliefUserById(userId);
    }

    @Test
    public void getNotCpUserList() throws Exception {
        List<WeekCpUser> userList = userDao.getNotCpUserList();
        System.out.println(userList.toString());
    }


    @Test
    public void getUserListTest() throws Exception {
        System.out.println(userDao.getUserList().toString());
    }

    @Test
    public void getUserByIdTest()throws Exception{
        Integer userId = 10003;
        WeekCpUser weekCpUser = userDao.getUserById(userId);
        System.out.println(weekCpUser.toString());
    }
    @Test
    public void addUserTest() throws Exception{
        WeekCpUser weekCpUser = new WeekCpUser("雷诺","1142908626@qq.com","计算1501",1,0,"1234567");
        userDao.addUser(weekCpUser);
        this.getUserListTest();
    }

    @Test
    public void updateUserByIdTest() throws Exception {
        WeekCpUser weekCpUser = new WeekCpUser("雷诺1","1142908126@qq.com","计算1501",1,0,"1234567");
        weekCpUser.setUserId(1);
        weekCpUser.setCp(1);
        userDao.updateUserById(weekCpUser.getUserId(), weekCpUser);
        this.getUserListTest();
    }
    @Test
    public void deleteUserByIdTest() throws Exception {
        Integer userId = 10002;
        userDao.deleteUserById(userId);
        this.getUserListTest();
    }

}