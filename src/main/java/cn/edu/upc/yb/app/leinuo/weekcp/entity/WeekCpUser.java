package cn.edu.upc.yb.app.leinuo.weekcp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Timestamp;

/**
 * @author leinuoleileinuo
 */
@Entity
public class WeekCpUser {
    @Id
    @GeneratedValue
    private Integer userId;
    @Column(nullable = false)
    private String name = "这个人很懒，没有名字";
    private String weiXin;
    private String qq;
    private String phoneNumber;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String majorClass;
    @Column(nullable = false)
    private Integer sex;
    @Column(nullable = false)
    private Integer sexualOrientation;
    private String hobby = "这个人没有填写兴趣爱好";
    @Column(name = "create_time",columnDefinition = "TIMESTAMP default current_time")
    private Timestamp createTime;
    @JsonIgnore
    private Timestamp updateTime = new Timestamp(System.currentTimeMillis());
    private String headerImage = "http://yb.upc.edu.cn/static/media/college-logo.98b06d35.png";
    private Integer cp = 0;
    private Integer deleted = 0;
    @Column(nullable = false,unique = true)
    private String yibanId;

    public WeekCpUser(){}

    public WeekCpUser(String name, String email, String majorClass, Integer sex, Integer sexualOrientation, String yibanId) {
        this.name = name;
        this.email = email;
        this.majorClass = majorClass;
        this.sex = sex;
        this.sexualOrientation = sexualOrientation;
        this.yibanId = yibanId;
    }

    public WeekCpUser(String name, String weiXin, String qq, String phoneNumber, String email, String majorClass, Integer sex, Integer sexualOrientation, String hobby,String headerImage, String yibanId) {
        this.name = name;
        this.weiXin = weiXin;
        this.qq = qq;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.majorClass = majorClass;
        this.sex = sex;
        this.sexualOrientation = sexualOrientation;
        this.hobby = hobby;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.headerImage = headerImage;
        this.cp = cp;
        this.deleted = deleted;
        this.yibanId = yibanId;
    }

    public Integer getCp() {
        return cp;
    }

    public void setCp(Integer cp) {
        this.cp = cp;
    }

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

    public Integer getUserId() {
        return userId;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
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

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWeiXin() {
        return weiXin;
    }

    public void setWeiXin(String weiXin) {
        this.weiXin = weiXin;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMajorClass() {
        return majorClass;
    }

    public void setMajorClass(String majorClass) {
        this.majorClass = majorClass;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getSexualOrientation() {
        return sexualOrientation;
    }

    public void setSexualOrientation(Integer sexualOrientation) {
        this.sexualOrientation = sexualOrientation;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public String getHeaderImage() {
        return headerImage;
    }

    public void setHeaderImage(String headerImage) {
        this.headerImage = headerImage;
    }

    public String getYibanId() {
        return yibanId;
    }

    public void setYibanId(String yibanId) {
        this.yibanId = yibanId;
    }

    @Override
    public String toString() {
        return "WeekCpUser{" +
                "userId=" + userId +
                ", name='" + name + '\'' +
                ", weiXin='" + weiXin + '\'' +
                ", qq='" + qq + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", majorClass='" + majorClass + '\'' +
                ", sex=" + sex +
                ", sexualOrientation=" + sexualOrientation +
                ", hobby='" + hobby + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", headerImage='" + headerImage + '\'' +
                ", cp=" + cp +
                ", deleted=" + deleted +
                '}';
    }
}

