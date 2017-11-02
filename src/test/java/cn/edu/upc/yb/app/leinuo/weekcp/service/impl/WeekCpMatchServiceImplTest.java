package cn.edu.upc.yb.app.leinuo.weekcp.service.impl;

import cn.edu.upc.yb.app.leinuo.weekcp.entity.WeekCpMatch;
import cn.edu.upc.yb.app.leinuo.weekcp.service.WeekCpMatchService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class WeekCpMatchServiceImplTest {

    @Autowired
    private WeekCpMatchService matchService;
    @Test
    public void addMatchByMatchMap() throws Exception {
        WeekCpMatch match = new WeekCpMatch(2,9);
        matchService.addMatchByMatchMap(match);
    }

}