package cn.edu.upc.yb.lottery.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

/**
 * @author liliangbin dumpling1520@gmail.com
 * @date 2018/2/21  16:24
 */


@Entity
@Table(name = "lotteryList")
public class LotteryList {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private long creatorid;
    private String lotteryname;
    private String lotteryintro;
    private Timestamp lotterytimebegin;
    private Timestamp lotterytimeend;
    private Date createtime;
    private int ispass; //是否审核通过.未审核为0,通过为1,不通过为2.默认的时候为
    private int passcode;
    private String feedback;//假设这个抽奖没有过的时候就会给用户一个反馈信息，，，
    private Date feedbackTime;

    @ManyToOne(targetEntity = Creator.class)
    @JoinColumn(name = "creator")
    @JsonBackReference
    private Creator creator;

    public Creator getCreator() {
        return creator;
    }

    public void setCreator(Creator creator) {
        this.creator = creator;
    }

    @OneToMany(mappedBy = "lottery", fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Prize> prizes;

    public List<Prize> getPrizes() {
        return prizes;
    }

    public void setPrizes(List<Prize> prizes) {
        this.prizes = prizes;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public Date getFeedbackTime() {
        return feedbackTime;
    }

    public void setFeedbackTime(Date feedbackTime) {
        this.feedbackTime = feedbackTime;
    }

    public LotteryList(long creatorid, String lotteryname, String lotteryintro, Timestamp lotterytimebegin, Timestamp lotterytimeend, Date createtime) {
        this.creatorid = creatorid;
        this.lotteryname = lotteryname;
        this.lotteryintro = lotteryintro;
        this.lotterytimebegin = lotterytimebegin;
        this.lotterytimeend = lotterytimeend;
        /* this.prizes = prizes;*/
        this.createtime = createtime;
        this.ispass = 0;
    }

    public LotteryList() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getCreatorid() {
        return creatorid;
    }

    public void setCreatorid(long creatorid) {
        this.creatorid = creatorid;
    }

    public String getLotteryname() {
        return lotteryname;
    }

    public void setLotteryname(String lotteryname) {
        this.lotteryname = lotteryname;
    }

    public String getLotteryintro() {
        return lotteryintro;
    }

    public void setLotteryintro(String lotteryintro) {
        this.lotteryintro = lotteryintro;
    }

    public Timestamp getLotterytimebegin() {
        return lotterytimebegin;
    }

    public void setLotterytimebegin(Timestamp lotterytimebegin) {
        this.lotterytimebegin = lotterytimebegin;
    }

    public Timestamp getLotterytimeend() {
        return lotterytimeend;
    }

    public void setLotterytimeend(Timestamp lotterytimeend) {
        this.lotterytimeend = lotterytimeend;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public int getIspass() {
        return ispass;
    }

    public void setIspass(int ispass) {
        this.ispass = ispass;
    }

    public int getPasscode() {
        return passcode;
    }

    public void setPasscode(int passcode) {
        this.passcode = passcode;
    }
}