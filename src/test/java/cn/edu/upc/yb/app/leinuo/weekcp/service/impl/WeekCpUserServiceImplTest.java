package cn.edu.upc.yb.app.leinuo.weekcp.service.impl;

import cn.edu.upc.yb.app.leinuo.weekcp.dao.WeekCpUserDao;
import cn.edu.upc.yb.app.leinuo.weekcp.entity.WeekCpUser;
import cn.edu.upc.yb.app.leinuo.weekcp.service.WeekCpUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class WeekCpUserServiceImplTest {
    @Test
    public void getUserByYibanId() throws Exception {
        String yibanId = "123456";
        WeekCpUser user = userService.getUserByYibanId(yibanId);
        System.out.println(user);
    }

    @Autowired
    private WeekCpUserServiceImpl userService;

    @Test
    public void getUserById() throws Exception {
        Integer userId = 10000;
        WeekCpUser user = userService.getUserById(userId);
        System.out.println(user);
    }

    @Test
    public void addUser() throws Exception {
        for (int i = 0;i < 10;i++) {
            WeekCpUser user = new WeekCpUser("雷诺","1142908626@qq.com","计算1501",1,0,"123456");
            userService.addUser(user);
        }
        for (int i = 0;i < 11;i++) {
            WeekCpUser user = new WeekCpUser("雷诺","1142908626@qq.com","计算1501",0,1,"123456");
            userService.addUser(user);
        }
//        WeekCpUser user = new WeekCpUser("雷诺","1142908626@qq.com","计算1501",1,0,"123456");
//        userService.addUser(user);
        this.getNotCpUserList();
    }

    @Test
    public void getMaxNumOfNotCp() throws Exception {
    }

    @Test
    public void updateUserById() throws Exception {
        WeekCpUser user = new WeekCpUser("雷诺12","1142908626@qq.com","计算1501",1,0,"123456");
        userService.updateUserById(10000,user);
    }

    @Test
    public void deleteUserById() throws Exception {
        userService.deleteUserById(10000);
    }

    @Test
    public void reliefUserById() throws Exception {
        userService.reliefUserById(10000);
    }

    @Test
    public void getNotCpUserList() throws Exception {
        List<WeekCpUser> userList = userService.getNotCpUserList();
        System.out.println(userList.toString());
    }

}