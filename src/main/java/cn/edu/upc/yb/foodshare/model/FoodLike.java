package cn.edu.upc.yb.foodshare.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "foodShare_like")
public class FoodLike {

    @Id
    @GeneratedValue
    @Column(name = "like_id")
    private Long id;

    @Column(name = "user_id")
    private int userid;//易班id

    @Column(name = "food_id")
    private int foodid;//菜品id

    @Column(name = "create_time")
    private Date createtime;//创建时间

    public FoodLike(){

    }

    public FoodLike(int userid, int foodid, Date createtime) {
        this.userid = userid;
        this.foodid = foodid;
        this.createtime = createtime;
    }

    public Long getId() {
        return id;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public int getFoodid() {
        return foodid;
    }

    public void setFoodid(int foodid) {
        this.foodid = foodid;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }
}
