package cn.edu.upc.yb.innersystem.controller;

import cn.edu.upc.yb.common.security.service.JwtTokenUtil;
import cn.edu.upc.yb.foodshare.dto.Message;
import cn.edu.upc.yb.innersystem.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/innersys/task")
public class TaskController {
    @Autowired
    TaskService taskService;

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public Object createTask (String Authorization, String department, String name, String workload, String phoneNumber, String deadline, String detail, String principal, String supervisor) {
        String ybid = jwtTokenUtil.getYBidFromTocken(Authorization);
        if (ybid != null) {
            return taskService.createTask(ybid, department, name, workload, phoneNumber, deadline, detail, principal, supervisor);
        }
        return new Message(0, "未登录或登录状态过期！");
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Object updateTask (String Authorization, String department, String name, String workload, String phoneNumber, String deadline, String detail, String principal, String supervisor, Integer taskId) {
        String ybid = jwtTokenUtil.getYBidFromTocken(Authorization);
        if (ybid != null) {
            return taskService.updateTask(ybid, taskId, department, name, workload, phoneNumber, deadline, detail, principal, supervisor);
        }
        return new Message(0, "未登录或登录状态过期！");
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public Object deleteTask (String Authorization, Integer taskId) {
        String ybid = jwtTokenUtil.getYBidFromTocken(Authorization);
        if (ybid != null) {
            return taskService.deleteTask(ybid, taskId);
        }
        return new Message(0, "未登录或登录状态过期！");
    }

    @RequestMapping(value = "/getdpt", method = RequestMethod.POST)
    public Object getAllTask (String Authorization, String department) {
        String ybid = jwtTokenUtil.getYBidFromTocken(Authorization);
        if (ybid != null) {
            return taskService.getAllTaskByDpartment(department);
        }
        return new Message(0, "未登录或登录状态过期！");
    }
}