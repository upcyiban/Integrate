package cn.edu.upc.yb.foodshare.model;

/**
 * Created By Kazusa in 2018/7/6 10:43
 */
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "foodshare_article")
public class FoodArticle {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "food_id")
    private int id;

    private int userid;//建立者的易班id

    private String name;//菜品名称

    private String imgurl;//菜品图片url

    private String price;//菜品价格

    private String kind;//菜品标签：标签表的id以“.”相隔

    private String detail;//菜品详细描述

    private String address;//地址

    private Date createtime;//创建时间或者修改后的时间

    private int ischeck= 0;//用户是否已检查通过审核

    private int state=1;//状态：0为审核通过已发布，-1为用户删除，1为待审核,-2为审核未通过

    private int review= 0;//评论数
    private int collection = 0;//收藏数
    private int likecount = 0;//点赞数



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

    public int getIscheck(){
        return  ischeck;
    }
    public void setIscheck(int ischeck) {
        this.ischeck = ischeck;
    }

    public int getLikecount() {
        return likecount;
    }

    public void setLikecount(int likecount) {
        this.likecount = likecount;
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
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
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
        return likecount;
    }

    public void setLikeCount(int likeCount) {
        this.likecount = likeCount;
    }
}
