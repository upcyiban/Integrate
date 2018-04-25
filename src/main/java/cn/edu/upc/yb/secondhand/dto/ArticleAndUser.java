package cn.edu.upc.yb.secondhand.dto;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

public class ArticleAndUser {

    private String articleId;
    private String name;//物品名字
    private String kind;//物品种类
    private String detail;//物品详细描述
    private String imgurl;//物品图片
    private String price;//物品价格
    private String degree;//崭新度
    private Date createtime;//创建时间
    private Date updatetime;//更新时间
    private int collections;//收藏数目
    private int reviews;
    private String username;//用户易班昵称
    private String ybhead;//用户头像

    public ArticleAndUser() {
    }

    public ArticleAndUser(String articleId, String name, String kind, String detail, String imgurl, String price, String degree, Date createtime, Date updatetime, int collections, int reviews, String username, String ybhead) {
        this.articleId = articleId;
        this.name = name;
        this.kind = kind;
        this.detail = detail;
        this.imgurl = imgurl;
        this.price = price;
        this.degree = degree;
        this.createtime = createtime;
        this.updatetime = updatetime;
        this.collections = collections;
        this.reviews = reviews;
        this.username = username;
        this.ybhead = ybhead;
    }

    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public int getCollections() {
        return collections;
    }

    public void setCollections(int collections) {
        this.collections = collections;
    }

    public int getReviews() {
        return reviews;
    }

    public void setReviews(int reviews) {
        this.reviews = reviews;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getYbhead() {
        return ybhead;
    }

    public void setYbhead(String ybhead) {
        this.ybhead = ybhead;
    }
}
