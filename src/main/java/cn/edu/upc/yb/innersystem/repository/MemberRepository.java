package cn.edu.upc.yb.innersystem.repository;

import cn.edu.upc.yb.innersystem.model.Member;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MemberRepository extends CrudRepository <Member, Integer> {
    Member findFirstByStudentId(String studentId);
    List<Member> findAllByDepartmentAndIsDeleted(String department, Integer isDeleted);
    List<Member> findAllByIsDeleted(Integer isDeleted);
}
