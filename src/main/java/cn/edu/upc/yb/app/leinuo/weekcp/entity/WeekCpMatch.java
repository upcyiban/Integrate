package cn.edu.upc.yb.app.leinuo.weekcp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Timestamp;

/**
 * @author leinuoleileinuo
 */
@Entity
public class WeekCpMatch {
    @Id @GeneratedValue
    private Integer matchId;
    @Column(nullable = false,unique = true)
    private Integer cpIdLeft;
    @Column(nullable = false , unique = true)
    private Integer cpIdRight;
    @Column(name = "create_time",columnDefinition = "TIMESTAMP default current_time")
    private Timestamp createTime;
    private Timestamp updateTime = new Timestamp(System.currentTimeMillis());
    private Integer deleted;


    @Override
    public String toString() {
        return "WeekCpMatch{" +
                "matchId=" + matchId +
                ", cpIdLeft=" + cpIdLeft +
                ", cpIdRight=" + cpIdRight +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", deleted=" + deleted +
                '}';
    }

    public WeekCpMatch(){}

    public WeekCpMatch(Integer cpIdLeft, Integer cpIdRight) {
        this.cpIdLeft = cpIdLeft;
        this.cpIdRight = cpIdRight;
    }

    public WeekCpMatch(Integer matchId, Integer cpIdLeft, Integer cpIdRight, Timestamp createTime, Timestamp updateTime, Integer deleted) {
        this.matchId = matchId;
        this.cpIdLeft = cpIdLeft;
        this.cpIdRight = cpIdRight;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.deleted = deleted;
    }

    public Integer getCpIdLeft() {
        return cpIdLeft;
    }

    public void setCpIdLeft(Integer cpIdLeft) {
        this.cpIdLeft = cpIdLeft;
    }

    public Integer getMatchId() {
        return matchId;
    }

    public void setMatchId(Integer matchId) {
        this.matchId = matchId;
    }



    public Integer getCpIdRight() {
        return cpIdRight;
    }

    public void setCpIdRight(Integer cpIdRight) {
        this.cpIdRight = cpIdRight;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

}
