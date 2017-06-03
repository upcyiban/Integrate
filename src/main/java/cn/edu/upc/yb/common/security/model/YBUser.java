package cn.edu.upc.yb.common.security.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "ybuser")
public class YBUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private int userid;
    private String username;
    private String usernick;
    private char usersex;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "ybuser_authority",
            joinColumns = @JoinColumn(name = "ybuser_id"),
            inverseJoinColumns = @JoinColumn(name = "authority_id"))
    private List<Authority> authorities;

    public YBUser(int userid, String username, String usernick, char usersex) {
        this.userid = userid;
        this.username = username;
        this.usernick = usernick;
        this.usersex = usersex;
    }

    public YBUser(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsernick() {
        return usernick;
    }

    public void setUsernick(String usernick) {
        this.usernick = usernick;
    }

    public char getUsersex() {
        return usersex;
    }

    public void setUsersex(char usersex) {
        this.usersex = usersex;
    }

    public List<Authority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<Authority> authorities) {
        this.authorities = authorities;
    }
}
