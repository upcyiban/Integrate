package cn.edu.upc.yb.lottery.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

/**
 * @author liliangbin dumpling1520@gmail.com
 * @date 2018/2/21  16:35
 */
@Entity
@Table(name = "prizeNew")
public class Prize {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private long creatorId;
    private String prizeName;
    private String prizeIntro;//作为一个测试可能是几等奖，或是什么其他的介绍
    private String prizePercentage;
    private long totalNumber;
    private long lotteryId;

    @ManyToOne(targetEntity = LotteryList.class)
    @JoinColumn(name = "lottery")
    @JsonBackReference
    private LotteryList lottery;

    public long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(long creatorId) {
        this.creatorId = creatorId;
    }

    public LotteryList getLottery() {
        return lottery;
    }

    public void setLottery(LotteryList lottery) {
        this.lottery = lottery;
    }

    public Prize(long creatorId, String prizeName, String prizeIntro, String prizePercentage, long totalNumber, long lotteryId) {
        this.creatorId = creatorId;
        this.prizeName = prizeName;
        this.prizeIntro = prizeIntro;
        this.prizePercentage = prizePercentage;
        this.totalNumber = totalNumber;
        this.lotteryId = lotteryId;
    }

    public Prize(String prizeName, String prizeIntro, String prizePercentage, long totalNumber, long lotteryId) {
        this.prizeName = prizeName;
        this.prizeIntro = prizeIntro;
        this.prizePercentage = prizePercentage;
        this.totalNumber = totalNumber;
        this.lotteryId = lotteryId;
    }

    public Prize() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPrizeName() {
        return prizeName;
    }

    public void setPrizeName(String prizeName) {
        this.prizeName = prizeName;
    }

    public String getPrizeIntro() {
        return prizeIntro;
    }

    public void setPrizeIntro(String prizeIntro) {
        this.prizeIntro = prizeIntro;
    }

    public String getPrizePercentage() {
        return prizePercentage;
    }

    public void setPrizePercentage(String prizePercentage) {
        this.prizePercentage = prizePercentage;
    }

    public long getTotalNumber() {
        return totalNumber;
    }

    public void setTotalNumber(long totalNumber) {
        this.totalNumber = totalNumber;
    }

    public long getLotteryId() {
        return lotteryId;
    }

    public void setLotteryId(long lotteryId) {
        this.lotteryId = lotteryId;
    }
}
