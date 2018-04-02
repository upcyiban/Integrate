package cn.edu.upc.yb.secondhand.controller;

import cn.edu.upc.yb.secondhand.dto.Message;
import cn.edu.upc.yb.secondhand.model.Article;
import cn.edu.upc.yb.secondhand.model.Review;
import cn.edu.upc.yb.secondhand.repository.ArticleRepository;
import cn.edu.upc.yb.secondhand.repository.ReviewRepository;
import cn.edu.upc.yb.secondhand.repository.UserRepository;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/secondhand/publish")
public class SecondPublishController {

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private UserRepository userRepository;


    @ApiOperation("发布物品")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userid",value = "用户id",dataType = "int",paramType = "query"),
            @ApiImplicitParam(name = "name",value = "物品名称",dataType = "String",paramType = "query"),
            @ApiImplicitParam(name = "kind",value = "物品种类",dataType = "String",paramType = "query"),
            @ApiImplicitParam(name = "detail",value = "物品详情",dataType = "String",paramType = "query"),
            @ApiImplicitParam(name = "imgurl",value = "物品图片链接",dataType = "String",paramType = "query"),
            @ApiImplicitParam(name = "price",value = "物品价格",dataType = "String",paramType = "query"),
            @ApiImplicitParam(name = "degree",value = "物品崭新度",dataType = "String",paramType = "query")
    })
    @RequestMapping(value = "/article", method = RequestMethod.GET)
    public Object createArticle(int userid, String name, String kind, String detail, String imgurl, String price, String degree){
        Article article=new Article();
        Date createTime=new Date();



        article.setName(name);
        article.setKind(kind);
        article.setDetail(detail);
        article.setDegree(degree);
        article.setImgurl(imgurl);
        article.setPrice(price);
        article.setDegree(degree);
        article.setCreatetime(createTime);
        article.setUserid(userid);
        article.setCollections(0);

        articleRepository.save(article);

        return new Message(1,"create article success");
    }

    @ApiOperation("更新物品")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "articleid",value = "物品id",dataType = "int",paramType = "query"),
            @ApiImplicitParam(name = "name",value = "物品名称",dataType = "String",paramType = "query"),
            @ApiImplicitParam(name = "kind",value = "物品种类",dataType = "String",paramType = "query"),
            @ApiImplicitParam(name = "detail",value = "物品详情",dataType = "String",paramType = "query"),
            @ApiImplicitParam(name = "imgurl",value = "物品图片链接",dataType = "String",paramType = "query"),
            @ApiImplicitParam(name = "price",value = "物品价格",dataType = "String",paramType = "query"),
            @ApiImplicitParam(name = "degree",value = "物品崭新度",dataType = "String",paramType = "query")
    })
    @RequestMapping(value = "/updatearticle",method = RequestMethod.GET)
    public Object updateArticle(int articleid,String name, String kind, String detail, String imgurl, String price, String degree){
        Article article=articleRepository.findOne(articleid);
        Date updateTime=new Date();

        article.setName(name);
        article.setKind(kind);
        article.setDetail(detail);
        article.setDegree(degree);
        article.setImgurl(imgurl);
        article.setPrice(price);
        article.setDegree(degree);
        article.setUpdatetime(updateTime);

        articleRepository.save(article);
        return new Message(1,"update article success");
    }

    @ApiOperation("创建评论")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userid",value = "用户id",dataType = "int",paramType = "query"),
            @ApiImplicitParam(name = "articleid",value = "物品id",dataType = "int",paramType = "query"),
            @ApiImplicitParam(name = "reviewid",value = "回复评论id",dataType = "int",paramType = "query"),
            @ApiImplicitParam(name = "detail",value = "详情",dataType = "String",paramType = "query"),
    })
    @RequestMapping(value = "/review",method = RequestMethod.GET)
    public Object createReview(int userid, int articleid, int reviewid, String detail){
        Review review =new Review();
        Date createTime = new Date();

        review.setArticleId(articleid);
        review.setReviewId(reviewid);
        review.setDetail(detail);

        review.setYbhead("head");
        review.setYbid(userid);
        review.setYbname("name");

        review.setCreatetime(createTime);

        review.setIsdelete(0);
        reviewRepository.save(review);
        return new Message(1,"create review success");
    }

    @ApiOperation("更新评论")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "reviewid",value = "回复评论id",dataType = "int",paramType = "query"),
            @ApiImplicitParam(name = "detail",value = "详情",dataType = "String",paramType = "query"),
    })
    @RequestMapping(value = "/updatereview",method = RequestMethod.GET)
    public Object updateReview(int reviewid,String detail){
        Review review = reviewRepository.findOne(reviewid);
        if (review==null){
            return new Message(0,"null review");
        }
        review.setDetail(detail);
        reviewRepository.save(review);

        return new Message(1,"update review success");
    }

    @ApiOperation("取消某物品的发布")
    @ApiImplicitParam(name = "articleid",value = "物品id",dataType = "int",paramType = "query")
    @RequestMapping(value = "dealarticle",method = RequestMethod.GET)
    public Object dealarticle(int articleid){
        Article article=articleRepository.findOne(articleid);
        if (article==null){
            return new Message(0,"null article");
        }
        article.setIsdeal(1);
        articleRepository.save(article);
        return new Message(1,"deal success");
    }

}
