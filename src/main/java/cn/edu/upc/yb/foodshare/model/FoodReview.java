package cn.edu.upc.yb.foodshare.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "foodShare_review")
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

    private boolean delete=false;//是否被用户删除
    private Date createtime;//创建时间

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public boolean isDelete() {
        return delete;
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

    public boolean getDelete() {
        return delete;
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

    public void setDelete(boolean isdelete) {
        this.delete = isdelete;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }
}
