package cn.edu.upc.yb.graduation.model;


import javax.persistence.*;

/**
 * @author liliangbin dumpling1520@gmail.com
 * @date 2018/5/23  22:46
 */
@Entity
@Table(name = "graduation_article")
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String author;

    private String sendTo;

    private String content;

    private String url;

    private long agree;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getSendTo() {
        return sendTo;
    }

    public void setSendTo(String sendTo) {
        this.sendTo = sendTo;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public long getAgree() {
        return agree;
    }

    public void setAgree(long agree) {
        this.agree = agree;
    }
}
