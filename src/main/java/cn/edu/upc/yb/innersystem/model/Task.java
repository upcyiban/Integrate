package cn.edu.upc.yb.innersystem.model;

import javax.persistence.*;

@Entity
@Table(name = "inner_tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String department;
    private String name;
    private String workload;
    private String phoneNumber;
    private String deadline;
    private String detail;
    private String principal;
    private String supervisor;
    private Integer isDone;
    private Integer isExpired;
    private Integer isDeleted;

    public Task (String department, String name, String workload, String phoneNumber, String deadline, String detail, String principal, String supervisor) {
        this.department = department;
        this.name = name;
        this.workload = workload;
        this.phoneNumber = phoneNumber;
        this.deadline = deadline;
        this.detail = detail;
        this.principal = principal;
        this.supervisor = supervisor;
        this.isDone = 0;
        this.isExpired = 0;
        this.isDeleted = 0;
    }

    public Task() {
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWorkload() {
        return workload;
    }

    public void setWorkload(String workload) {
        this.workload = workload;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public String getPrincipal() {
        return principal;
    }

    public void setPrincipal(String principal) {
        this.principal = principal;
    }

    public String getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(String supervisor) {
        this.supervisor = supervisor;
    }

    public Integer getIsDone() {
        return isDone;
    }

    public void setIsDone(Integer isDone) {
        this.isDone = isDone;
    }

    public Integer getIsExpired() {
        return isExpired;
    }

    public void setIsExpired(Integer isExpired) {
        this.isExpired = isExpired;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }
}
