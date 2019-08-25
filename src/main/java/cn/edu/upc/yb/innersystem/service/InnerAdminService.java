package cn.edu.upc.yb.innersystem.service;

import cn.edu.upc.yb.foodshare.dto.Message;
import cn.edu.upc.yb.innersystem.model.InnerAdmin;
import cn.edu.upc.yb.innersystem.repository.InnerAdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InnerAdminService {
    @Autowired
    InnerAdminRepository innerAdminRepository;

    public Object getAllAdmin (String ybid) {
        InnerAdmin manager = innerAdminRepository.findFirstByYbuid(ybid);
        if (manager != null && manager.getLevel() == 2) {
            return innerAdminRepository.findAll();
        }
        return new Message(0, "没有权限！");
    }

    public Object createAdmin (String ybid, String name, String department, String ybuid, Integer level) {
        InnerAdmin manager = innerAdminRepository.findFirstByYbuid(ybid);
        if (manager != null && manager.getLevel() == 2) {
            InnerAdmin newAdmin = new InnerAdmin(name, department, ybuid, level);
            return innerAdminRepository.save(newAdmin);
        }
        return new Message(0, "没有权限！");
    }

    public Object updateAdmin (String ybid, String name, String department, String ybuid, Integer level, Integer adminId) {
        InnerAdmin manager = innerAdminRepository.findFirstByYbuid(ybid);
        if (manager != null && manager.getLevel() == 2) {
            InnerAdmin newAdmin = innerAdminRepository.findFirstById(adminId);
            if (newAdmin != null) {
                newAdmin.setName(name);
                newAdmin.setDepartment(department);
                newAdmin.setYbuid(ybuid);
                newAdmin.setLevel(level);
                return innerAdminRepository.save(newAdmin);
            }
            return new Message(0, "此管理员不存在！");
        }
        return new Message(0, "没有权限！");
    }
}
