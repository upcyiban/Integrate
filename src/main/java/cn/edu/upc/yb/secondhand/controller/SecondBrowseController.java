package cn.edu.upc.yb.secondhand.controller;

import cn.edu.upc.yb.common.dto.SwaggerParameter;
import cn.edu.upc.yb.common.security.service.JwtTokenUtil;
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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.ServiceMode;
import java.util.Iterator;
import java.util.List;

@RestController
@RequestMapping("/secondhand/browse")
public class SecondBrowseController {

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    /*
    用户浏览已经发布物品
     */
    @ApiOperation(value = "用户浏览已经发布物品")
    @ApiImplicitParams({
            @ApiImplicitParam(name = SwaggerParameter.Authorization, value = "token", dataType ="String",paramType = "query")
    })
    @RequestMapping(value = "/article",method = RequestMethod.GET)
    public Object allArticleBrowse(){
        return articleRepository.findByIsdealOrderByCreatetimeDesc(0);
    }
    /*
    用户浏览单个物品
     */
    @ApiOperation(value = "用户浏览单个物品")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "articleid",value = "物品id",dataType = "int",paramType = "query"),
            @ApiImplicitParam(name = SwaggerParameter.Authorization, value = "token", dataType ="String",paramType = "query")
    })
    @RequestMapping(value = "/onearticle",method = RequestMethod.GET)
    public Object oneArticleBrowse(HttpServletRequest request,int articleid){
        Article article=articleRepository.findOne(articleid);
        if (article==null){
            return new Message(0,"null article");
        }
        if (article.getIsdeal()==-1){
            return new Message(0,"user delete article");
        }
        if (article.getIsdeal()==-2){
            return new Message(0,"admin delete article");
        }
        return article;
    }
    /*
    用户物品记录
     */
    @ApiOperation(value = "用户物品记录")
    @ApiImplicitParam(name = SwaggerParameter.Authorization, value = "token", dataType ="String",paramType = "query")
    @RequestMapping(value = "/historyArticle",method = RequestMethod.GET)
    public Object historyAricleBrowse(HttpServletRequest request){
        String token=request.getParameter(this.tokenHeader);
        int userid=Integer.valueOf(jwtTokenUtil.getYBidFromTocken(token));
        List<Article> articleList=articleRepository.findByUseridOrderByCreatetimeDesc(userid);
        int i;
        int n=articleList.size();
        System.out.println(n);
        Article article=new Article();
        for (i=0;i<n;i++){
            article=articleList.get(i);
            System.out.println(article.getId());
            if (article.getIsdeal()==-1){
                articleList.remove(article);
                n--;
            }
        }
        return articleList;
    }

    /*
    某个物品的所有评论
     */

    @ApiOperation(value = "某个物品的所有评论")
    @ApiImplicitParams({
            @ApiImplicitParam(name = SwaggerParameter.Authorization, value = "token", dataType ="String",paramType = "query"),
            @ApiImplicitParam(name = "articleid",value = "",dataType = "int",paramType = "query")
    })
    @RequestMapping(value = "/review",method = RequestMethod.GET)
    public Object oneArticleReviewBrowse(int articleid){
        return reviewRepository.findByArticleIdAndIsdeleteOrderByCreatetimeDesc(articleid,0);
    }

    /*
    某用户的评论记录
     */
    @ApiOperation("某用户的评论记录")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = SwaggerParameter.Authorization, dataType = "String")
    })
    @RequestMapping(value = "/historyreview",method = RequestMethod.GET)
    public Object historyReviewBrowse(HttpServletRequest request){
        String token=request.getParameter(this.tokenHeader);
        int userid=Integer.valueOf(jwtTokenUtil.getYBidFromTocken(token));
        return reviewRepository.findByYbidAndIsdeleteOrderByCreatetimeDesc(userid,0);
    }

}
