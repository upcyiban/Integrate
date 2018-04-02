package cn.edu.upc.yb.secondhand.controller;

import cn.edu.upc.yb.secondhand.model.Review;
import cn.edu.upc.yb.secondhand.repository.ArticleRepository;
import cn.edu.upc.yb.secondhand.repository.ReviewRepository;
import cn.edu.upc.yb.secondhand.repository.UserRepository;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.ws.ServiceMode;

@RestController
@RequestMapping("/secondhand/browse")
public class SecondBrowseController {

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private UserRepository userRepository;

    /*
    用户浏览已经发布物品
     */
    @ApiOperation(value = "用户浏览已经发布物品")
    @RequestMapping(value = "/article",method = RequestMethod.GET)
    public Object allArticleBrowse(){
        return articleRepository.findByIsdealOrderByCreatetimeDesc(0);
    }
    /*
    用户物品记录
     */
    @ApiOperation(value = "用户物品记录")
    @ApiImplicitParam(name = "userid",value = "用户id",dataType = "int",paramType = "query")
    @RequestMapping(value = "/historyArticle",method = RequestMethod.GET)
    public Object historyAricleBrowse(int userid){

        return articleRepository.findByUseridOrderByCreatetimeDesc(userid);
    }

    /*
    某个物品的所有评论
     */

    @ApiOperation(value = "某个物品的所有评论")
    @ApiImplicitParam(name = "articleid",value = "",dataType = "int",paramType = "query")
    @RequestMapping(value = "/review",method = RequestMethod.GET)
    public Object oneArticleBrowse(int articleid){
        return reviewRepository.findByArticleIdAndIsdeleteOrderByCreatetimeDesc(articleid,0);
    }

    /*
    某用户的评论记录
     */
    @ApiOperation("某用户的评论记录")
    @ApiImplicitParam(name = "userid",value = "用户id",dataType = "int",paramType = "query")
    @RequestMapping(value = "/historyreview",method = RequestMethod.GET)
    public Object historyReviewBrowse(int userid){

        return reviewRepository.findByYbidAndIsdeleteOrderByCreatetimeDesc(userid,0);
    }

    /*
    用户的个人信息获取
     */
    @ApiOperation("用户的个人信息获取")
    @ApiImplicitParam(name = "userid",value = "用户id",dataType = "int",paramType = "query")
    @RequestMapping(value = "/user",method = RequestMethod.GET)
    public Object user(int userid){
        return userRepository.findByUserid(userid);
    }

}
