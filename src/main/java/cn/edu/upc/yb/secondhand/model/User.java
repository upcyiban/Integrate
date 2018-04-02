package cn.edu.upc.yb.secondhand.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "secondhand_user")
@JsonIgnoreProperties(value = {"isdelete", "createtime", "updatatime"})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private int userid;//用户易班id
    private String username;//用户易班昵称
    private String usernick;//用户性别
    private char usersex;//用户性别
    private String ybhead;//用户头像
    private String qq=null;//qq
    private String wchat=null;//微信
    private String phone=null;//手机号
    private String email=null;//邮件

    private Date createtime;//第一次登录
    private Date updatatime;//上次登录
    private boolean isdelete = false;//是否删除

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

    public Date getUpdatatime() {
        return updatatime;
    }

    public void setUpdatatime(Date updatatime) {
        this.updatatime = updatatime;
    }

    public boolean isIsdelete() {
        return isdelete;
    }

    public void setIsdelete(boolean isdelete) {
        this.isdelete = isdelete;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getWchat() {
        return wchat;
    }

    public void setWchat(String wchat) {
        this.wchat = wchat;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
