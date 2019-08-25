package cn.edu.upc.yb.innersystem.model;
import javax.persistence.*;

@Entity
@Table(name = "inner_admins")
public class InnerAdmin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String department;
    private String ybuid;
    private Integer level;

    public InnerAdmin(String name, String department, String ybuid, Integer level) {
        this.name = name;
        this.department = department;
        this.ybuid = ybuid;
        this.level = level;
    }

    public InnerAdmin() {
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
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

    public String getYbuid() {
        return ybuid;
    }

    public void setYbuid(String ybuid) {
        this.ybuid = ybuid;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }
}