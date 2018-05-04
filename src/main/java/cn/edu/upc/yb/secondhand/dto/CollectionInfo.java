package cn.edu.upc.yb.secondhand.dto;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

public class CollectionInfo {

    private int id;
    private int userId;
    private int articleId;
    private Date creatTime;

    private String articleUserYBhead;
    private String articleUserYBName;
    private String articleName;
    private String articleDetail;
    private String articlePrice;
    private String articleKind;
    private String articleImg;
    private Date articleDate;

    public CollectionInfo() {
    }

    public CollectionInfo(int id, int userId, int articleId, String articleUserYBhead, String articleUserYBName, String articleName, String articleDetail, String articlePrice, String articleKind) {
        this.id = id;
        this.userId = userId;
        this.articleId = articleId;
        this.articleUserYBhead = articleUserYBhead;
        this.articleUserYBName = articleUserYBName;
        this.articleName = articleName;
        this.articleDetail = articleDetail;
        this.articlePrice = articlePrice;
        this.articleKind = articleKind;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getArticleId() {
        return articleId;
    }

    public void setArticleId(int articleId) {
        this.articleId = articleId;
    }

    public String getArticleUserYBhead() {
        return articleUserYBhead;
    }

    public void setArticleUserYBhead(String articleUserYBhead) {
        this.articleUserYBhead = articleUserYBhead;
    }

    public String getArticleUserYBName() {
        return articleUserYBName;
    }

    public void setArticleUserYBName(String articleUserYBName) {
        this.articleUserYBName = articleUserYBName;
    }

    public String getArticleName() {
        return articleName;
    }

    public void setArticleName(String articleName) {
        this.articleName = articleName;
    }

    public String getArticleDetail() {
        return articleDetail;
    }

    public void setArticleDetail(String articleDetail) {
        this.articleDetail = articleDetail;
    }

    public String getArticlePrice() {
        return articlePrice;
    }

    public void setArticlePrice(String articlePrice) {
        this.articlePrice = articlePrice;
    }

    public String getArticleKind() {
        return articleKind;
    }

    public void setArticleKind(String articleKind) {
        this.articleKind = articleKind;
    }

    public Date getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(Date creatTime) {
        this.creatTime = creatTime;
    }

    public String getArticleImg() {
        return articleImg;
    }

    public void setArticleImg(String articleImg) {
        this.articleImg = articleImg;
    }

    public Date getArticleDate() {
        return articleDate;
    }

    public void setArticleDate(Date articleDate) {
        this.articleDate = articleDate;
    }
}
