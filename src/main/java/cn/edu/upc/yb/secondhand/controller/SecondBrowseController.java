package cn.edu.upc.yb.secondhand.controller;

import cn.edu.upc.yb.common.dto.SwaggerParameter;
import cn.edu.upc.yb.common.security.service.JwtTokenUtil;
import cn.edu.upc.yb.secondhand.dto.Message;
import cn.edu.upc.yb.secondhand.model.SecondArticle;
import cn.edu.upc.yb.secondhand.model.SecondKind;
import cn.edu.upc.yb.secondhand.repository.ArticleRepository;
import cn.edu.upc.yb.secondhand.repository.KindRepository;
import cn.edu.upc.yb.secondhand.repository.ReviewRepository;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
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

    @Autowired
    private KindRepository kindRepository;

    @ApiOperation(value = "用户根据物品名称进行模糊查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = SwaggerParameter.Authorization, value = "token", dataType ="String",paramType = "query"),
            @ApiImplicitParam(name = "name",value = "物品名称",dataType = "String",paramType = "query")
    })
    @RequestMapping(value = "/findbyarticlename",method = RequestMethod.GET)
    public Object findByArticleName(HttpServletRequest request,String name){
        return articleRepository.findByNameLike(name);
    }


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
        SecondArticle secondArticle =articleRepository.findOne(articleid);
        if (secondArticle ==null){
            return new Message(0,"null secondArticle");
        }
        if (secondArticle.getIsdeal()==-1){
            return new Message(0,"user delete secondArticle");
        }
        if (secondArticle.getIsdeal()==-2){
            return new Message(0,"admin delete secondArticle");
        }
        return secondArticle;
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
        List<SecondArticle> secondArticleList =articleRepository.findByUseridOrderByCreatetimeDesc(userid);
        int i;
        int n= secondArticleList.size();
        System.out.println(n);
        SecondArticle secondArticle =new SecondArticle();
        for (i=0;i<n;i++){
            secondArticle = secondArticleList.get(i);
            System.out.println(secondArticle.getId());
            if (secondArticle.getIsdeal()==-1){
                secondArticleList.remove(secondArticle);
                n--;
            }
        }
        return secondArticleList;
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

    @ApiOperation("查看所有种类")
    @ApiImplicitParams({
            @ApiImplicitParam(name = SwaggerParameter.Authorization, value = "token", dataType ="String",paramType = "query")
    })
    @RequestMapping(value = "/allkind",method = RequestMethod.GET)
    public Object allKind(HttpServletRequest request){
        return kindRepository.findAll();
    }

}
