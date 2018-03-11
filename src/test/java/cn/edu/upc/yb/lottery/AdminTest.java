package cn.edu.upc.yb.lottery;

import cn.edu.upc.yb.lottery.service.AdminService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.servlet.http.HttpSession;

/**
 * @author liliangbin dumpling1520@gmail.com
 * @date 2018/3/10  18:05
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class AdminTest {

    @Autowired
    private AdminService adminService;


    @Autowired
    private HttpSession httpSession ;


    @Test
    public void  klkkk(){
        httpSession.setAttribute("lotteryAdmin",true);


        adminService.getLotteryList();
        adminService.selectLottery("1",1,"nnnnn");

    }

}
