package cn.edu.upc.yb.sports.model;

import javax.persistence.*;

/**
 * @author liliangbin dumpling1520@gmail.com
 * @date 2018/5/12  18:31
 */
@Entity
@Table(name = "sports_User")
public class SportsUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO )
    private int id;

    private String project;//参加的项目
    private String username; //参赛人员名字
    private String  ranking;//排名
    private String score ; //成绩
    private long  ord ;
    private  boolean outRecord;

    public boolean isOutRecord() {
        return outRecord;
    }

    public void setOutRecord(boolean outRecord) {
        this.outRecord = outRecord;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRanking() {
        return ranking;
    }

    public void setRanking(String ranking) {
        this.ranking = ranking;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public long getOrd() {
        return ord;
    }

    public void setOrd(long ord) {
        this.ord = ord;
    }
}
