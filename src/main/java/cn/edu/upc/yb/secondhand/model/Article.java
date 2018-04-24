package cn.edu.upc.yb.secondhand.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "secondhand_article")
public class Article {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;//物品名字
    private String kind;//物品种类
    private String detail;//物品详细描述
    private String imgurl;//物品图片
    private String price;//物品价格
    private String degree;//崭新度
    private int isdeal=0;//物品是否处理 0为对外发布状态 1为不发布状态 -1为用户删除状态 -2为管理员删除
    private Date createtime;//创建时间
    private Date updatetime;//更新时间
    private int collections;//收藏数目

    private int userid;//易班id

    public Article() {
    }

    public Article(String name, String kind, String detail, String imgurl, String price, String degree) {
        this.name = name;
        this.kind = kind;
        this.detail = detail;
        this.imgurl = imgurl;
        this.price = price;
        this.degree = degree;
    }

    public int getId() {
        return id;
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

    public int getIsdeal() {
        return isdeal;
    }

    public void setIsdeal(int isdeal) {
        this.isdeal = isdeal;
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

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public int getCollections() {
        return collections;
    }

    public void setCollections(int collections) {
        this.collections = collections;
    }
}
