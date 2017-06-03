package cn.edu.upc.yb.common.security.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

/**
 * Created by lylll on 2017/6/3.
 */
@Entity
@Table(name = "integrate_app")
public class App {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private int id;

    private String appname;
    private String appid;
    private String appkey;

    public App(String appname, String appid, String appkey) {
        this.appname = appname;
        this.appid = appid;
        this.appkey = appkey;
    }

    public App() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAppname() {
        return appname;
    }

    public void setAppname(String appname) {
        this.appname = appname;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getAppkey() {
        return appkey;
    }

    public void setAppkey(String appkey) {
        this.appkey = appkey;
    }
}
