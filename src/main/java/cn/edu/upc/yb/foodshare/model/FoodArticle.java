package cn.edu.upc.yb.foodshare.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "foodShare_article")
public class FoodArticle {

    @Id
    @Column(name = "food_id")
    @GeneratedValue
    private int id;

    @Column(name = "user_id")
    private int userid;//建立者的易班id

    @Column(name = "name")
    private String name;//菜品名称

    @Column(name = "img_url")
    private String imgurl;//菜品图片url

    @Column(name = "price")
    private String price;//菜品价格

    @Column(name = "kind")
    private String kind;//菜品标签：标签表的id以“.”相隔

    @Column(name = "detail")
    private String detail;//菜品详细描述

    @Column(name = "address")
    private String address;//地址

    private Date createTime;//创建时间或者修改后的时间
    private Boolean check=false;//用户是否已检查通过审核

    @Column(name = "state")
    private int state=1;//状态：0为审核通过已发布，-1为用户删除，1为待审核,-2为审核未通过

    private int review= 0;//评论数
    private int collection = 0;//收藏数
    private int likeCount = 0;//点赞数

    public FoodArticle(){

    }

    public FoodArticle(String name, String imgurl, String priece, String kind, String detail, String address, int state) {
        this.name = name;
        this.imgurl = imgurl;
        this.price = priece;
        this.kind = kind;
        this.detail = detail;
        this.address = address;
        this.state = state;
    }


    public int getId() {
        return id;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String priece) {
        this.price = priece;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getCreatetime() {
        return createTime;
    }

    public void setCreatetime(Date createtime) {
        this.createTime = createtime;
    }

    public Boolean getIscheck() {
        return check;
    }

    public void setIscheck(Boolean ischeck) {
        this.check = ischeck;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getReview() {
        return review;
    }

    public void setReview(int review) {
        this.review = review;
    }

    public int getCollection() {
        return collection;
    }

    public void setCollection(int collection) {
        this.collection = collection;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }
}
