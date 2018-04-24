package cn.edu.upc.yb.lottery.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

/**
 * @author liliangbin dumpling1520@gmail.com
 * @date 2018/2/21  16:22
 */
@Entity
@Table(name = "creatorNew")
public class Creator {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private long yibanid;
    private String yibanname;

    @OneToMany(mappedBy = "creator",fetch = FetchType.EAGER)
    @JsonIgnore
    private List<LotteryList> lotteryLists;


    public Creator(long yibanid, String yibanname, List<LotteryList> lotteryLists) {
        this.yibanid = yibanid;
        this.yibanname = yibanname;
        this.lotteryLists = lotteryLists;
    }

    public Creator(long yibanid, String yibanname) {
        this.yibanid = yibanid;
        this.yibanname = yibanname;
    }

    public Creator() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public List<LotteryList> getLotteryLists() {
        return lotteryLists;
    }

    public void setLotteryLists(List<LotteryList> lotteryLists) {
        this.lotteryLists = lotteryLists;
    }


}
