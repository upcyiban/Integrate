package cn.edu.upc.yb.secondhand.controller;

import cn.edu.upc.yb.common.dto.SwaggerParameter;
import cn.edu.upc.yb.common.security.service.JwtTokenUtil;
import cn.edu.upc.yb.secondhand.dto.Message;
import cn.edu.upc.yb.secondhand.model.SecondArticle;
import cn.edu.upc.yb.secondhand.model.SecondReview;
import cn.edu.upc.yb.secondhand.model.SecondUser;
import cn.edu.upc.yb.secondhand.repository.ArticleRepository;
import cn.edu.upc.yb.secondhand.repository.ReviewRepository;
import cn.edu.upc.yb.secondhand.repository.UserRepository;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
    @RequestMapping(value = "/article", method = RequestMethod.POST)
    public Object createArticle(HttpServletRequest request, String name, String kind, String detail, String imgurl, String price, String degree){
        String token=request.getParameter(this.tokenHeader);
        int userid=Integer.valueOf(jwtTokenUtil.getYBidFromTocken(token));
        SecondUser user=userRepository.findByUserid(userid);
        SecondArticle secondArticle =new SecondArticle();
        Date createTime=new Date();

        secondArticle.setName(name);
        secondArticle.setKind(kind);
        secondArticle.setDetail(detail);
        secondArticle.setDegree(degree);
        secondArticle.setImgurl(imgurl);
        secondArticle.setPrice(price);
        secondArticle.setDegree(degree);
        secondArticle.setCreatetime(createTime);
        secondArticle.setUserid(userid);

        secondArticle.setYbhead(user.getYbhead());
        secondArticle.setYbname(user.getUsername());

        articleRepository.save(secondArticle);

        return new Message(1,"create secondArticle success");
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
    @RequestMapping(value = "/updatearticle",method = RequestMethod.POST)
    public Object updateArticle(HttpServletRequest request,int articleid,String name, String kind, String detail, String imgurl, String price, String degree){
        SecondArticle secondArticle =articleRepository.findOne(articleid);
        if (secondArticle ==null){
            return new Message(0,"null secondArticle");
        }
        Date updateTime=new Date();

        secondArticle.setName(name);
        secondArticle.setKind(kind);
        secondArticle.setDetail(detail);
        secondArticle.setDegree(degree);
        secondArticle.setImgurl(imgurl);
        secondArticle.setPrice(price);
        secondArticle.setDegree(degree);
        secondArticle.setUpdatetime(updateTime);

        articleRepository.save(secondArticle);
        return new Message(1,"update secondArticle success");
    }

    @ApiOperation("创建评论")
    @ApiImplicitParams({
            @ApiImplicitParam(name = SwaggerParameter.Authorization, value = "token", dataType ="String",paramType = "query"),
            @ApiImplicitParam(name = "articleid",value = "物品id",dataType = "int",paramType = "query"),
            @ApiImplicitParam(name = "detail",value = "详情",dataType = "String",paramType = "query"),
    })
    @RequestMapping(value = "/review",method = RequestMethod.POST)
    public Object createReview(HttpServletRequest request, int articleid,  String detail){
        String token=request.getParameter(this.tokenHeader);
        int userid=Integer.valueOf(jwtTokenUtil.getYBidFromTocken(token));
        SecondUser user=userRepository.findByUserid(userid);
        SecondArticle secondArticle =articleRepository.findOne(articleid);
        if (secondArticle ==null){
            return new Message(0,"null secondArticle");
        }
        if (user==null){
            return new Message(0,"null user");
        }
        SecondReview secondReview =new SecondReview();
        Date createTime = new Date();
        secondReview.setArticleId(articleid);
        secondReview.setDetail(detail);
        secondReview.setYbhead(user.getYbhead());
        secondReview.setYbid(userid);
        secondReview.setYbname(user.getUsername());
        secondReview.setCreatetime(createTime);
        reviewRepository.save(secondReview);
        secondArticle.setReviews(secondArticle.getReviews()+1);
        articleRepository.save(secondArticle);
        return new Message(1,"create secondReview success");
    }

    @ApiOperation("更新评论")
    @ApiImplicitParams({
            @ApiImplicitParam(name = SwaggerParameter.Authorization, value = "token", dataType ="String",paramType = "query"),
            @ApiImplicitParam(name = "reviewid",value = "回复评论id",dataType = "int",paramType = "query"),
            @ApiImplicitParam(name = "detail",value = "详情",dataType = "String",paramType = "query"),
    })
    @RequestMapping(value = "/updatereview",method = RequestMethod.POST)
    public Object updateReview(int reviewid,String detail){

        SecondReview secondReview = reviewRepository.findOne(reviewid);
        if (secondReview ==null){
            return new Message(0,"null secondReview");
        }
        Date date=new Date();
        secondReview.setUpdatatime(date);
        secondReview.setDetail(detail);
        reviewRepository.save(secondReview);

        return new Message(1,"update secondReview success");
    }

    @ApiOperation("取消某物品的发布")
    @ApiImplicitParams({
            @ApiImplicitParam(name = SwaggerParameter.Authorization, value = "token", dataType ="String",paramType = "query"),
            @ApiImplicitParam(name = "articleid",value = "物品id",dataType = "int",paramType = "query")
    })

    @RequestMapping(value = "/dealarticle",method = RequestMethod.POST)
    public Object dealarticle(int articleid){
        SecondArticle secondArticle =articleRepository.findOne(articleid);
        if (secondArticle ==null){
            return new Message(0,"null secondArticle");
        }
        if (secondArticle.getIsdeal()==1){
            return new Message(0,"don't deal again");
        }
        secondArticle.setIsdeal(1);
        secondArticle.setUpdatetime(new Date());
        articleRepository.save(secondArticle);
        return new Message(1,"deal success");
    }

    @ApiOperation("删除某物品")
    @ApiImplicitParams({
            @ApiImplicitParam(name = SwaggerParameter.Authorization, value = "token", dataType ="String",paramType = "query"),
            @ApiImplicitParam(name = "articleid",value = "物品id",dataType = "int",paramType = "query")
    })
    @RequestMapping(value = "/deletearticle",method = RequestMethod.POST)
    public Object deletearticle(HttpServletRequest request,int articleid){
        String token=request.getParameter(this.tokenHeader);
        SecondArticle secondArticle =articleRepository.findOne(articleid);
        if (secondArticle ==null){
            return new Message(0,"null secondArticle");
        }
        int userId=Integer.valueOf(jwtTokenUtil.getYBidFromTocken(token));
        if (secondArticle.getUserid()!=userId){
            return new Message(0,"not the user");
        }
        if (secondArticle.getIsdeal()!=0){
            return new Message(0,"don't delete again");
        }
        secondArticle.setIsdeal(-1);
        secondArticle.setUpdatetime(new Date());
        articleRepository.save(secondArticle);
        return new Message(1,"delete success");
    }

    @ApiOperation("删除某评论")
    @ApiImplicitParams({
            @ApiImplicitParam(name = SwaggerParameter.Authorization, value = "token", dataType ="String",paramType = "query"),
            @ApiImplicitParam(name = "reviewid",value = "评论id",dataType = "int",paramType = "query")
    })
    @RequestMapping(value = "/deletereview",method = RequestMethod.POST)
    public Object deleteReview(HttpServletRequest request,int reviewid){
        String token=request.getParameter(this.tokenHeader);
        SecondReview review=reviewRepository.findOne(reviewid);
        if (review==null){
            return new Message(0,"null secondReview");
        }
        int userId=Integer.valueOf(jwtTokenUtil.getYBidFromTocken(token));
        if (review.getYbid()!=userId){
            return new Message(0,"not the user");
        }
        if (review.getIsdelete()!=0){
            return new Message(0,"don't delete again");
        }
        review.setIsdelete(-1);
        review.setUpdatatime(new Date());
        reviewRepository.save(review);

        return new Message(1,"delete success");
    }

}
