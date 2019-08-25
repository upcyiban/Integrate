package cn.edu.upc.yb.innersystem.service;

import cn.edu.upc.yb.foodshare.dto.Message;
import cn.edu.upc.yb.innersystem.model.InnerAdmin;
import cn.edu.upc.yb.innersystem.model.Member;
import cn.edu.upc.yb.innersystem.repository.InnerAdminRepository;
import cn.edu.upc.yb.innersystem.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
    @Autowired
    MemberRepository memberRepository;

    @Autowired
    InnerAdminRepository innerAdminRepository;

    public Object createMember (String ybid, String name, String studentId, String department, String phoneNumber, String college) {
        if (name != null && studentId != null && department != null) {
            InnerAdmin manager = innerAdminRepository.findFirstByYbuid(ybid);
            if (manager != null && department.equals(manager.getDepartment())) {
                Member member = new Member(name, studentId, department, phoneNumber, college);
                memberRepository.save(member);
                return new Message(1, "创建成员成功");
            }
        }
        return new Message(0, "创建成员失败");
    }

    public Object deleteMember (String ybid, String studentId) {
        InnerAdmin manager = innerAdminRepository.findFirstByYbuid(ybid);
        Member member = memberRepository.findFirstByStudentId(studentId);
        if (manager != null && member != null && manager.getDepartment().equals(member.getDepartment())) {
            member.setIsDeleted(1);
            memberRepository.save(member);
            return new Message(1, "删除成员成功");
        }
        return new Message(0, "删除成员失败");
    }

    public Object updateMember (String ybid, String name, String studentId, String department, String phoneNumber, String college) {
        InnerAdmin manager = innerAdminRepository.findFirstByYbuid(ybid);
        Member member = memberRepository.findFirstByStudentId(studentId);
        if (manager != null && member != null && manager.getDepartment().equals(member.getDepartment())) {
            member.setName(name);
            member.setStudentId(studentId);
            member.setDepartment(department);
            member.setPhoneNumber(phoneNumber);
            member.setCollege(college);
            memberRepository.save(member);
            return new Message(1, "更新成员信息成功");
        }
        return new Message(0, "更新成员信息失败");
    }

    public Object getAllActiveMembers () {
        return memberRepository.findAllByIsDeleted(0);
    }

    public Object getAllActiveMembersByDepartment (String department) {
        return memberRepository.findAllByDepartmentAndIsDeleted(department ,0);
    }

}
