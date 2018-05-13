package cn.edu.upc.yb.sports.model;

import javax.persistence.*;
import java.util.Date;

/**
 * @author liliangbin dumpling1520@gmail.com
 * @date 2018/5/12  19:25
 */
@Entity
@Table(name = "sports_project")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private int id;

    private String projectName;

    private Date start;

    private int groupId;

    private int projectId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }
}
