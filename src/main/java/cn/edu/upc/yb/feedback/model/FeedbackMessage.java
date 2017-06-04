package cn.edu.upc.yb.feedback.model;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by lylll on 2017/6/4.
 */
@Entity
@Table(name = "feedback")
public class FeedbackMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private int ybid; //发送人的id
    private String  message;
    private String appname;
    private Timestamp sendTime;
    private int ispass;

    public FeedbackMessage() {
    }

    public FeedbackMessage(int ybid, String message, String appname) {
        this.ybid = ybid;
        this.message = message;
        this.appname = appname;
        this.sendTime = new Timestamp(System.currentTimeMillis());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getYbid() {
        return ybid;
    }

    public void setYbid(int ybid) {
        this.ybid = ybid;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getAppname() {
        return appname;
    }

    public void setAppname(String appname) {
        this.appname = appname;
    }

    public Timestamp getSendTime() {
        return sendTime;
    }

    public void setSendTime(Timestamp sendTime) {
        this.sendTime = sendTime;
    }

    public int getIspass() {
        return ispass;
    }

    public void setIspass(int ispass) {
        this.ispass = ispass;
    }
}
