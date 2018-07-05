package cn.edu.upc.yb.foodshare.model;



import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="foodShare_user")
@JsonIgnoreProperties(value = {"createtime","lasttime","isadmin"})//
public class FoodUser {

    @Id
    @GeneratedValue
    private int id;

    private int userid;//易班id
    private String username;//易班昵称
    private String usersex;//性别
    private String ybhead;//易班头像

    private Date createtime;//第一次登陆时间
    private Date lasttime;//上次登录时间
    private boolean isadmin=false;//用户权限：false:正常用户,true:管理员

    public FoodUser(){

    }
    public FoodUser(int userid, String username, String usersex, String ybhead) {
        this.userid = userid;
        this.username = username;
        this.usersex = usersex;
        this.ybhead = ybhead;
    }

    public int getId() {
        return id;
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

    public String getUsersex() {
        return usersex;
    }

    public void setUsersex(String usersex) {
        this.usersex = usersex;
    }

    public String getYbhead() {
        return ybhead;
    }

    public void setYbhead(String ybhead) {
        this.ybhead = ybhead;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getLasttime() {
        return lasttime;
    }

    public void setLasttime(Date lasttime) {
        this.lasttime = lasttime;
    }

    public boolean isIsadmin() {
        return isadmin;
    }

    public void setIsadmin(boolean isadmin) {
        this.isadmin = isadmin;
    }
}
