package cn.edu.upc.yb.secondhand.controller;

import cn.edu.upc.yb.common.dto.SwaggerParameter;
import cn.edu.upc.yb.common.security.service.JwtTokenUtil;
import cn.edu.upc.yb.secondhand.dto.Message;
import cn.edu.upc.yb.secondhand.model.Article;
import cn.edu.upc.yb.secondhand.model.Review;
import cn.edu.upc.yb.secondhand.model.User;
import cn.edu.upc.yb.secondhand.repository.ArticleRepository;
import cn.edu.upc.yb.secondhand.repository.ReviewRepository;
import cn.edu.upc.yb.secondhand.repository.UserRepository;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@RestController
@RequestMapping("/secondhand/publish")
public class SecondPublishController {

    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;


    @ApiOperation("发布物品")
    @ApiImplicitParams({
            @ApiImplicitParam(name = SwaggerParameter.Authorization, value = "token", dataType ="String",paramType = "query"),
            @ApiImplicitParam(name = "name",value = "物品名称",dataType = "String",paramType = "query"),
            @ApiImplicitParam(name = "kind",value = "物品种类",dataType = "String",paramType = "query"),
            @ApiImplicitParam(name = "detail",value = "物品详情",dataType = "String",paramType = "query"),
            @ApiImplicitParam(name = "imgurl",value = "物品图片链接",dataType = "String",paramType = "query"),
            @ApiImplicitParam(name = "price",value = "物品价格",dataType = "String",paramType = "query"),
            @ApiImplicitParam(name = "degree",value = "物品崭新度",dataType = "String",paramType = "query")
    })
    @RequestMapping(value = "/article", method = RequestMethod.GET)
    public Object createArticle(HttpServletRequest request, String name, String kind, String detail, String imgurl, String price, String degree){
        String token=request.getParameter(this.tokenHeader);
        int userid=Integer.valueOf(jwtTokenUtil.getYBidFromTocken(token));
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

        articleRepository.save(article);

        return new Message(1,"create article success");
    }

    @ApiOperation("更新物品")
    @ApiImplicitParams({
            @ApiImplicitParam(name = SwaggerParameter.Authorization, value = "token", dataType ="String",paramType = "query"),
            @ApiImplicitParam(name = "articleid",value = "物品id",dataType = "int",paramType = "query"),
            @ApiImplicitParam(name = "name",value = "物品名称",dataType = "String",paramType = "query"),
            @ApiImplicitParam(name = "kind",value = "物品种类",dataType = "String",paramType = "query"),
            @ApiImplicitParam(name = "detail",value = "物品详情",dataType = "String",paramType = "query"),
            @ApiImplicitParam(name = "imgurl",value = "物品图片链接",dataType = "String",paramType = "query"),
            @ApiImplicitParam(name = "price",value = "物品价格",dataType = "String",paramType = "query"),
            @ApiImplicitParam(name = "degree",value = "物品崭新度",dataType = "String",paramType = "query")
    })
    @RequestMapping(value = "/updatearticle",method = RequestMethod.GET)
    public Object updateArticle(HttpServletRequest request,int articleid,String name, String kind, String detail, String imgurl, String price, String degree){
        Article article=articleRepository.findOne(articleid);
        if (article==null){
            return new Message(0,"null article");
        }
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
            @ApiImplicitParam(name = SwaggerParameter.Authorization, value = "token", dataType ="String",paramType = "query"),
            @ApiImplicitParam(name = "articleid",value = "物品id",dataType = "int",paramType = "query"),
            @ApiImplicitParam(name = "detail",value = "详情",dataType = "String",paramType = "query"),
    })
    @RequestMapping(value = "/review",method = RequestMethod.GET)
    public Object createReview(HttpServletRequest request, int articleid,  String detail){
        String token=request.getParameter(this.tokenHeader);
        int userid=Integer.valueOf(jwtTokenUtil.getYBidFromTocken(token));
        User user=userRepository.findByUserid(userid);
        Article article =articleRepository.findOne(articleid);
        if (article==null){
            return new Message(0,"null article");
        }
        if (user==null){
            return new Message(0,"null user");
        }
        Review review =new Review();
        Date createTime = new Date();
        review.setArticleId(articleid);
        review.setDetail(detail);
        review.setYbhead(user.getYbhead());
        review.setYbid(userid);
        review.setYbname(user.getUsername());
        review.setCreatetime(createTime);
        reviewRepository.save(review);
        article.setReviews(article.getReviews()+1);
        articleRepository.save(article);
        return new Message(1,"create review success");
    }

    @ApiOperation("更新评论")
    @ApiImplicitParams({
            @ApiImplicitParam(name = SwaggerParameter.Authorization, value = "token", dataType ="String",paramType = "query"),
            @ApiImplicitParam(name = "reviewid",value = "回复评论id",dataType = "int",paramType = "query"),
            @ApiImplicitParam(name = "detail",value = "详情",dataType = "String",paramType = "query"),
    })
    @RequestMapping(value = "/updatereview",method = RequestMethod.GET)
    public Object updateReview(int reviewid,String detail){

        Review review = reviewRepository.findOne(reviewid);
        if (review==null){
            return new Message(0,"null review");
        }
        Date date=new Date();
        review.setUpdatatime(date);
        review.setDetail(detail);
        reviewRepository.save(review);

        return new Message(1,"update review success");
    }

    @ApiOperation("取消某物品的发布")
    @ApiImplicitParams({
            @ApiImplicitParam(name = SwaggerParameter.Authorization, value = "token", dataType ="String",paramType = "query"),
            @ApiImplicitParam(name = "articleid",value = "物品id",dataType = "int",paramType = "query")
    })

    @RequestMapping(value = "/dealarticle",method = RequestMethod.GET)
    public Object dealarticle(int articleid){
        Article article=articleRepository.findOne(articleid);
        if (article==null){
            return new Message(0,"null article");
        }
        if (article.getIsdeal()==1){
            return new Message(0,"don't deal again");
        }
        article.setIsdeal(1);
        article.setUpdatetime(new Date());
        articleRepository.save(article);
        return new Message(1,"deal success");
    }

    @ApiOperation("删除某物品")
    @ApiImplicitParams({
            @ApiImplicitParam(name = SwaggerParameter.Authorization, value = "token", dataType ="String",paramType = "query"),
            @ApiImplicitParam(name = "articleid",value = "物品id",dataType = "int",paramType = "query")
    })
    @RequestMapping(value = "/deletearticle",method = RequestMethod.GET)
    public Object deletearticle(int articleid){
        Article article=articleRepository.findOne(articleid);
        if (article==null){
            return new Message(0,"null article");
        }
        if (article.getIsdeal()==-1){
            return new Message(0,"don't delete again");
        }
        article.setIsdeal(-1);
        article.setUpdatetime(new Date());
        articleRepository.save(article);
        return new Message(1,"delete success");
    }

}
