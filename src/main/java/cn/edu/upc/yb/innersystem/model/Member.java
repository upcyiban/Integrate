package cn.edu.upc.yb.innersystem.model;

import javax.persistence.*;

@Entity
@Table(name = "inner_members")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String studentId;
    private String department;
    private String phoneNumber;
    private String college;
    private Integer isDeleted;

    public Member (String name, String studentId, String department, String phoneNumber, String college) {
        this.name = name;
        this.studentId = studentId;
        this.department = department;
        this.phoneNumber = phoneNumber;
        this.college = college;
        this.isDeleted = 0;
    }

    public Member() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }
}
