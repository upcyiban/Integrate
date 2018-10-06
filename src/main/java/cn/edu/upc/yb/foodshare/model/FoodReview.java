package cn.edu.upc.yb.foodshare.model;

/**
 * Created By Kazusa in 2018/7/6 11:02
 */
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "foodshare_review")
@JsonIgnoreProperties(value = {"delete"})
public class FoodReview {

    @Id
    @GeneratedValue
    @Column(name = "review_id")
    private Long id;

    private int foodid;//菜品id
    private int userid;//评论者易班id
    private String ybhead;//评论者头像
    private String ybname;//评论者昵称
    private String detail;//评论内容

    private int isdelete = 0;//是否被用户删除


    private Date createtime;//创建时间


    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Long getId() {
        return id;
    }

    public int getFoodid() {
        return foodid;
    }

    public int getUserid() {
        return userid;
    }

    public String getYbhead() {
        return ybhead;
    }

    public String getYbname() {
        return ybname;
    }

    public int getDelete() {
        return isdelete;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setFoodid(int foodid) {
        this.foodid = foodid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public void setYbhead(String ybhead) {
        this.ybhead = ybhead;
    }

    public void setYbname(String ybname) {
        this.ybname = ybname;
    }

    public void setDelete(int delete) {
        this.isdelete = delete;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }
}
