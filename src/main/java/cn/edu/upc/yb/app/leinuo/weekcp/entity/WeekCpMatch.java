package cn.edu.upc.yb.app.leinuo.weekcp.entity;

import java.sql.Timestamp;

/**
 * @author leinuoleileinuo
 */
public class WeekCpMatch {
    private Integer matchId;
    private Integer cpIdLeft;
    private Integer cpIdRight;
    private Timestamp createTime;
    private Timestamp updateTime;
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
