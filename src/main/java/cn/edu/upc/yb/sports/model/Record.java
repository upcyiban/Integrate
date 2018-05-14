package cn.edu.upc.yb.sports.model;

import javax.persistence.*;

/**
 * @author liliangbin dumpling1520@gmail.com
 * @date 2018/5/14  22:38
 */
@Entity
@Table(name = "sporter_record")
public class Record {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  int id;

    private String username;//用户名
    private String projectName;//项目名，，男子300米
    private String score;//成绩
    private String ranking;//排名
    private String recordInfo;//记录的简介。。省级，，校级


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getRanking() {
        return ranking;
    }

    public void setRanking(String ranking) {
        this.ranking = ranking;
    }

    public String getRecordInfo() {
        return recordInfo;
    }

    public void setRecordInfo(String recordInfo) {
        this.recordInfo = recordInfo;
    }
}
