package cn.edu.upc.yb.innersystem.service;

import cn.edu.upc.yb.foodshare.dto.Message;
import cn.edu.upc.yb.innersystem.dto.util;
import cn.edu.upc.yb.innersystem.model.InnerAdmin;
import cn.edu.upc.yb.innersystem.model.Task;
import cn.edu.upc.yb.innersystem.repository.InnerAdminRepository;
import cn.edu.upc.yb.innersystem.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TaskService {
    @Autowired
    TaskRepository taskRepository;

    @Autowired
    InnerAdminRepository innerAdminRepository;

    public Object createTask (String ybid, String department, String name, String workload, String phoneNumber, String deadline, String detail, String principal, String supervisor) {
        InnerAdmin manager = innerAdminRepository.findFirstByYbuid(ybid);
        if (manager != null && department.equals(manager.getDepartment())) {
            if (department != null && workload != null && phoneNumber != null && util.isValidDate(deadline) == false && detail != null && principal != null && supervisor != null) {
                Task task = new Task(department, name, workload, phoneNumber, deadline, detail, principal, supervisor);
                taskRepository.save(task);
                return new Message(1, "创建任务成功");
            }
            return new Message(0, "参数错误");
        }
        return new Message(0, "无权操作");
    }

    public Object deleteTask (String ybid, Integer taskId) {
        InnerAdmin manager = innerAdminRepository.findFirstByYbuid(ybid);
        Task task = taskRepository.findFirstById(taskId);
        if (manager != null) {
            if (task != null && task.getIsDeleted() == 0) {
                if (manager.getDepartment().equals(task.getDepartment())) {
                    task.setIsDeleted(1);
                    taskRepository.save(task);
                    return new Message(1, "删除任务成功");
                }
                return new Message(0, "无权操作！");
            }
            return new Message(0, "任务不存在！");
        }
        return new Message(0, "无权操作");
    }

    public Object updateTask (String ybid, Integer taskId, String department, String name, String workload, String phoneNumber, String deadline, String detail, String principal, String supervisor) {
        InnerAdmin manager = innerAdminRepository.findFirstByYbuid(ybid);
        Task task = taskRepository.findFirstById(taskId);
        if (manager != null) {
            if (task != null && task.getIsDeleted() == 0) {
                if (department != null && workload != null && phoneNumber != null && util.isValidDate(deadline) == false && detail != null && principal != null && supervisor != null) {
                    if (manager.getDepartment().equals(task.getDepartment())) {
                        task.setDepartment(department);
                        task.setName(name);
                        task.setWorkload(workload);
                        task.setPhoneNumber(phoneNumber);
                        task.setDeadline(deadline);
                        task.setDetail(detail);
                        task.setPrincipal(principal);
                        task.setSupervisor(supervisor);
                        taskRepository.save(task);
                        return new Message(1, "更新任务成功");
                    }
                    return new Message(0, "无权操作");
                }
                return new Message(0, "参数错误！");
            }
            return new Message(0, "任务不存在！");
        }
        return new Message(0, "无权操作");
    }

    //按部门查询任务
    public Object getAllTaskByDpartment (String department) {
        List tasklist = taskRepository.findAllByDepartmentAndIsDeleted(department, 0);
        Map map = new HashMap();
        map.put("message", new Message(1, "获取任务列表成功"));
        map.put("tasklist", tasklist);
        return map;
    }
}
