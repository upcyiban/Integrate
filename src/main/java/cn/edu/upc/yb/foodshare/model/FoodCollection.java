package cn.edu.upc.yb.foodshare.model;

/**
 * Created By Kazusa in 2018/7/6 11:03
 */
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "foodshare_collection")
public class FoodCollection {

    @Id
    @GeneratedValue
    @Column(name = "collection_id")
    private Long id;

    private int userid;//易班id
    private int foodid;//菜品id

    @Column(name = "create_time")
    private Date createtime;//创建时间

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
