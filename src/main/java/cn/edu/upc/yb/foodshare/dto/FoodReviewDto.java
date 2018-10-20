package cn.edu.upc.yb.foodshare.dto;

/**
 * Created By Kazusa in 2018/10/19 15:30
 */
public class FoodReviewDto {
    private int foodId;

    private int userid;//建立者的易班id

    private String name;//菜品名称

    private String imgurl;//菜品图片url

    private String price;//菜品价格

    private String kind;//菜品标签：标签表的id以“.”相隔

    private String detail;//菜品详细描述

    private String address;//地址

    private int review= 0;//评论数

    private int collection = 0;//收藏数

    private int likecount = 0;//点赞数

    private Long reviewId;

    private String reviewDetail;

    public FoodReviewDto(){

    }

    public Long getReviewId() {
        return reviewId;
    }

    public String getReviewDetail() {
        return reviewDetail;
    }

    public int getFoodId() {
        return foodId;
    }

    public void setFoodId(int foodId) {
        this.foodId = foodId;
    }

    public void setReviewId(Long reviewId) {
        this.reviewId = reviewId;
    }

    public void setReviewDetail(String reviewDetail) {
        this.reviewDetail = reviewDetail;
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
