package cn.edu.upc.yb.innersystem.controller;

import cn.edu.upc.yb.common.security.service.JwtTokenUtil;
import cn.edu.upc.yb.foodshare.dto.Message;
import cn.edu.upc.yb.innersystem.repository.InnerAdminRepository;
import cn.edu.upc.yb.innersystem.service.InnerAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/innersys/manager")
public class InnerAdminController {
    @Autowired
    InnerAdminRepository innerAdminRepository;

    @Autowired
    InnerAdminService innerAdminService;

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public Object createAdmin (String Authorization, String name, String department, String ybuid, Integer level) {
        String ybid = jwtTokenUtil.getYBidFromTocken(Authorization);
        if (ybid != null) {
            return innerAdminService.createAdmin(ybid, name, department, ybuid, level);
        }
        return new Message(0, "未登录或登录状态过期！");
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Object updateAdmin (String Authorization, String name, String department, String ybuid, Integer level, Integer id) {
        String ybid = jwtTokenUtil.getYBidFromTocken(Authorization);
        if (ybid != null) {
            return innerAdminService.updateAdmin(ybid, name, department, ybuid, level, id);
        }
        return new Message(0, "未登录或登录状态过期！");
    }

    @RequestMapping(value = "/getall", method = RequestMethod.POST)
    public Object getAllAdmin (String Authorization) {
        String ybid = jwtTokenUtil.getYBidFromTocken(Authorization);
        if (ybid != null) {
            return innerAdminService.getAllAdmin(ybid);
        }
        return new Message(0, "未登录或登录状态过期！");
    }
}
