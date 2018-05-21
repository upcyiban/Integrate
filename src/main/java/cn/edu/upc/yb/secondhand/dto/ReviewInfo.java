package cn.edu.upc.yb.secondhand.dto;

import cn.edu.upc.yb.secondhand.model.SecondReview;

import java.util.Date;

public class ReviewInfo {

    private int reivewId;

    private int articleId;
    private String articleImgUrl;

    private String detail;
    private int ybid;
    private String ybname;
    private String ybhead;

    private Date createtime;
    private Date updatatime;
    private int isdelete = 0;//0为正常显示 -1为用户删除 -2为管理员删除

    public ReviewInfo() {
    }

    public ReviewInfo(SecondReview secondReview){
        reivewId = secondReview.getId();
        articleId = secondReview.getArticleId();
        detail = secondReview.getDetail();
        ybid = secondReview.getYbid();
        ybname = secondReview.getYbname();
        ybhead = secondReview.getYbhead();
        createtime = secondReview.getCreatetime();
        updatatime = secondReview.getUpdatatime();
        isdelete = secondReview.getIsdelete();
    }

    public int getReivewId() {
        return reivewId;
    }

    public void setReivewId(int reivewId) {
        this.reivewId = reivewId;
    }

    public int getArticleId() {
        return articleId;
    }

    public void setArticleId(int articleId) {
        this.articleId = articleId;
    }

    public String getArticleImgUrl() {
        return articleImgUrl;
    }

    public void setArticleImgUrl(String articleImgUrl) {
        this.articleImgUrl = articleImgUrl;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public int getYbid() {
        return ybid;
    }

    public void setYbid(int ybid) {
        this.ybid = ybid;
    }

    public String getYbname() {
        return ybname;
    }

    public void setYbname(String ybname) {
        this.ybname = ybname;
    }

    public String getYbhead() {
        return ybhead;
    }

    public void setYbhead(String ybhead) {
        this.ybhead = ybhead;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getUpdatatime() {
        return updatatime;
    }

    public void setUpdatatime(Date updatatime) {
        this.updatatime = updatatime;
    }

    public int getIsdelete() {
        return isdelete;
    }

    public void setIsdelete(int isdelete) {
        this.isdelete = isdelete;
    }
}
