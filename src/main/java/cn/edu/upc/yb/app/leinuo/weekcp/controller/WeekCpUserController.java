package cn.edu.upc.yb.app.leinuo.weekcp.controller;

import cn.edu.upc.yb.app.leinuo.weekcp.entity.WeekCpMatch;
import cn.edu.upc.yb.app.leinuo.weekcp.entity.WeekCpUser;
import cn.edu.upc.yb.app.leinuo.weekcp.exception.WeekCpMatchException;
import cn.edu.upc.yb.app.leinuo.weekcp.exception.WeekCpUserException;
import cn.edu.upc.yb.app.leinuo.weekcp.result.Result;
import cn.edu.upc.yb.app.leinuo.weekcp.service.WeekCpUserService;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author leinuo
 * TODO 增加自己的CP接口，增加注册用户接口
 */
@RestController
@RequestMapping("/leinuo/weekcp/user")
public class WeekCpUserController {

    @Autowired
    private WeekCpUserService userService;

    @RequestMapping("/getNotCpUserList")
    public Result getNotCpUserList() {
        System.out.println("getNotCpUserList");
        List<WeekCpUser> userList;
        try {
            userList = userService.getNotCpUserList();
        } catch (WeekCpUserException e) {
            return Result.getResultFail(e.getMessage());
        } catch (WeekCpMatchException e) {
            return Result.getResultFail(e.getMessage());
        }
        return Result.getResultSuccess(userList);
    }

    @RequestMapping("/register")
    public Result<?> registerUser(@ModelAttribute("name")String name,
                                  @ModelAttribute("majorWithClass")String majorWithClass,
                                  @ModelAttribute("mail")String mail,
                                  @ModelAttribute("phoneNumber")String phoneNumber,
                                  @ModelAttribute("qq")String qq,
                                  @ModelAttribute("weiChat")String weiChat,
                                  @ModelAttribute("sex")Integer isMan,
                                  @ModelAttribute("isLoveMan")Integer isLoveMan,
                                  @ModelAttribute("hobby")String hobby,
                                  @ModelAttribute("headerImage")String headImage,
                                  @ModelAttribute("yibanId")String yibanId) {
        WeekCpUser user;
        System.out.println(name);
        try {
            user = new WeekCpUser(name,weiChat,qq,phoneNumber,
                    mail,majorWithClass,isMan,isLoveMan,hobby,headImage,yibanId);
        }catch (Exception e) {
            return Result.getResultFail("传入参数错误，请联系后端或查看接口文档");
        }
        boolean flag;
        try {
            flag = userService.addUser(user);
        } catch (WeekCpUserException e) {
            return Result.getResultFail(e.getMessage());
        } catch (WeekCpMatchException e) {
            return Result.getResultFail(e.getMessage());
        }
        return Result.getResultSuccess("成功注册用户",flag);
    }

    @RequestMapping("/{userId}/getUserById")
    public Result getUserById(@PathVariable("userId")Integer userId) {
        WeekCpUser user;
        try {
            user = userService.getUserById(userId);
        } catch (WeekCpUserException e) {
            return Result.getResultFail(e.getMessage());
        }
        return Result.getResultSuccess("成功",user);
    }

    @RequestMapping("/{yibanId}/getUserByYibanId")
    public Result getUserByYibanId(@PathVariable("yibanId")String yibanId) {
        WeekCpUser user;
        try {
            user = userService.getUserByYibanId(yibanId);
        } catch (WeekCpUserException e) {
            return Result.getResultFail(e.getMessage());
        }
        return Result.getResultSuccess("成功",user);
    }

    @RequestMapping("/")
    public WeekCpMatch hello(){
        return new WeekCpMatch(10,20);
    }
}
