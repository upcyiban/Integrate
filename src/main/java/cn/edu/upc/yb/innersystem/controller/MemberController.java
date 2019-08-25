package cn.edu.upc.yb.innersystem.controller;

import cn.edu.upc.yb.common.security.service.JwtTokenUtil;
import cn.edu.upc.yb.foodshare.dto.Message;
import cn.edu.upc.yb.innersystem.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/innersys/member")
public class MemberController {
    @Autowired
    MemberService memberService;

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public Object createMember (String Authorization, String name, String studentId, String department, String phoneNumber, String college) {
        String ybid = jwtTokenUtil.getYBidFromTocken(Authorization);
        if (ybid != null) {
            return memberService.createMember(ybid, name, studentId, department, phoneNumber, college);
        }
        return new Message(0, "未登录或登录状态过期！");
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public Object deleteMember (String Authorization, String studentId) {
        String ybid = jwtTokenUtil.getYBidFromTocken(Authorization);
        if (ybid != null) {
            return memberService.deleteMember(ybid, studentId);
        }
        return new Message(0, "未登录或登录状态过期！");
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Object deleteMember (String Authorization, String name, String studentId, String department, String phoneNumber, String college) {
        String ybid = jwtTokenUtil.getYBidFromTocken(Authorization);
        if (ybid != null) {
            return memberService.updateMember(ybid, name, studentId, department, phoneNumber, college);
        }
        return new Message(0, "未登录或登录状态过期！");
    }

    @RequestMapping(value = "/getall", method = RequestMethod.POST)
    public Object getAllActiveMembers (String Authorization) {
        String ybid = jwtTokenUtil.getYBidFromTocken(Authorization);
        if (ybid != null) {
            return memberService.getAllActiveMembers();
        }
        return new Message(0, "未登录或登录状态过期！");
    }

    @RequestMapping(value = "/getdpt", method = RequestMethod.POST)
    public Object getAllActiveMembersByDepartment (String Authorization, String department) {
        String ybid = jwtTokenUtil.getYBidFromTocken(Authorization);
        if (ybid != null) {
            return memberService.getAllActiveMembersByDepartment(department);
        }
        return new Message(0, "未登录或登录状态过期！");
    }
}
