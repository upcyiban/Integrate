package cn.edu.upc.yb.lottery.model;


import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

/**
 * @author liliangbin dumpling1520@gmail.com
 * @date 2018/2/21  17:03
 */
@Entity
@Table(name = "prizeListNew")
public class PrizeList {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private long lotteryid;
    private long yibanid;
    private String yibanname;
    private String prizeName;
    private String prizeStage;

    @ManyToOne(targetEntity = LotteryList.class)
    @JoinColumn(name = "lotteryPrize")
    @JsonBackReference
    private LotteryList lotteryPrize;

    public LotteryList getLotteryPrize() {
        return lotteryPrize;
    }

    public void setLotteryPrize(LotteryList lotteryPrize) {
        this.lotteryPrize = lotteryPrize;
    }

    public String getPrizeName() {
        return prizeName;
    }

    public void setPrizeName(String prizeName) {
        this.prizeName = prizeName;
    }

    public String getPrizeStage() {
        return prizeStage;
    }

    public void setPrizeStage(String prizeStage) {
        this.prizeStage = prizeStage;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getLotteryid() {
        return lotteryid;
    }

    public void setLotteryid(long lotteryid) {
        this.lotteryid = lotteryid;
    }

    public long getYibanid() {
        return yibanid;
    }

    public void setYibanid(long yibanid) {
        this.yibanid = yibanid;
    }

    public String getYibanname() {
        return yibanname;
    }

    public void setYibanname(String yibanname) {
        this.yibanname = yibanname;
    }
}
