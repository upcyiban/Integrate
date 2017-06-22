package cn.edu.upc.yb.common.security.model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by lylll on 2017/6/2.
 */
@Entity
@Table(name = "authority")
public class Authority {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;

    @ManyToMany(mappedBy = "authorities", fetch = FetchType.LAZY)
    private List<YBUser> users;

    public Authority(){}

    public Authority(String name){
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<YBUser> getUsers() {
        return users;
    }

    public void setUsers(List<YBUser> users) {
        this.users = users;
    }
}
