package cn.edu.upc.yb.app.leinuo.weekcp.entity;

import java.sql.Timestamp;

/**
 * @author leinuoleileinuo
 */
public class WeekCpUser {
    private Integer userId;
    private String name = "这个人很懒，没有名字";
    private String weiXin;
    private String qq;
    private String phoneNumber;
    private String email;
    private String majorClass;
    private Integer sex;
    private Integer sexualOrientation;
    private String hobby = "这个人没有填写兴趣爱好";
    private Timestamp createTime;
    private Timestamp updateTime;
    private String headerImage = "http://yb.upc.edu.cn/static/media/college-logo.98b06d35.png";
    private boolean cp = false;
    private boolean deleted = false;

    static {

    }
    public WeekCpUser(String name, String email, Integer sex, Integer sexualOrientation, String Hobby) {
        this.name = name;
        this.email = email;
        this.sex = sex;
        this.sexualOrientation = sexualOrientation;
        this.hobby = hobby;
    }

    public WeekCpUser(String name, String email, String majorClass, Integer sex, Integer sexualOrientation, String hobby) {
        this.name = name;
        this.email = email;
        this.majorClass = majorClass;
        this.sex = sex;
        this.sexualOrientation = sexualOrientation;
        this.hobby = hobby;
    }

    public WeekCpUser(Integer userId, String name, String weiXin, String qq, String phoneNumber, String email, String majorClass, Integer sex, Integer sexualOrientation, String hobby, Timestamp createTime, Timestamp updateTime, String headerImage) {
        this.userId = userId;
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
    }

    public WeekCpUser(Integer userId, String name, String weiXin, String qq, String phoneNumber, String email, String majorClass, Integer sex, Integer sexualOrientation, String hobby, String headerImage) {
        this.userId = userId;
        this.name = name;
        this.weiXin = weiXin;
        this.qq = qq;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.majorClass = majorClass;
        this.sex = sex;
        this.sexualOrientation = sexualOrientation;
        this.hobby = hobby;
        this.headerImage = headerImage;
    }

    public WeekCpUser( String name, String weiXin, String qq, String phoneNumber, String email, String majorClass, Integer sex, Integer sexualOrientation, String hobby) {
        this.name = name;
        this.weiXin = weiXin;
        this.qq = qq;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.majorClass = majorClass;
        this.sex = sex;
        this.sexualOrientation = sexualOrientation;
        this.hobby = hobby;
    }
    public WeekCpUser( String name, String weiXin, String qq, String phoneNumber, String email, String majorClass, Integer sex, Integer sexualOrientation, String hobby,String headerImage) {
        this.name = name;
        this.weiXin = weiXin;
        this.qq = qq;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.majorClass = majorClass;
        this.sex = sex;
        this.sexualOrientation = sexualOrientation;
        this.hobby = hobby;
        this.headerImage = headerImage;
    }

    public WeekCpUser(Integer userId, String name, String weiXin, String qq, String phoneNumber, String email, String majorClass, Integer sex, Integer sexualOrientation, String hobby, Timestamp createTime, Timestamp updateTime, String headerImage, Integer cp, Integer deleted) {
        this.userId = userId;
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
        this.cp = (cp == 1);
        this.deleted = (deleted == 1);
    }

    public boolean isCp() {
        return cp;
    }

    public void setCp(boolean cp) {
        this.cp = cp;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
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

