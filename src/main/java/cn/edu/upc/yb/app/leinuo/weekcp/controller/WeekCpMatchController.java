package cn.edu.upc.yb.app.leinuo.weekcp.controller;

import cn.edu.upc.yb.app.leinuo.weekcp.entity.WeekCpMatch;
import cn.edu.upc.yb.app.leinuo.weekcp.exception.WeekCpMatchException;
import cn.edu.upc.yb.app.leinuo.weekcp.exception.WeekCpUserException;
import cn.edu.upc.yb.app.leinuo.weekcp.result.Result;
import cn.edu.upc.yb.app.leinuo.weekcp.service.WeekCpMatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author leinuoleileinuo
 */
@RestController
@RequestMapping("/leinuo/weekcp/match")
public class WeekCpMatchController {
    @Autowired
    private WeekCpMatchService matchService;

    @RequestMapping("/{userId}/getMatchByUserId")
    public Result<?> getMatchByUserId(@PathVariable("userId") Integer userId) {
        System.out.println(userId);
        WeekCpMatch match;
        try {
            match = matchService.getMatchByUserId(userId);
        }  catch (WeekCpMatchException e) {
            return Result.getResultFail(e.getMessage());
        }
        return Result.getResultSuccess("请求成功",match);
    }
}
