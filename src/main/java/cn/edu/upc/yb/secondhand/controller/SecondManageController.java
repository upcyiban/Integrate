package cn.edu.upc.yb.secondhand.controller;

import cn.edu.upc.yb.common.dto.SwaggerParameter;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("/secondhand/manage")
public class SecondManageController {

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private ReviewRepository reviewRepository;;

    @Autowired
    private UserRepository userRepository;


    /*
    删除非法用户
     */
    @ApiOperation("删除非法用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userid",value = "用户id",dataType = "int",paramType = "query"),
            @ApiImplicitParam(name = SwaggerParameter.Authorization, value = "token", dataType ="String",paramType = "query"),
    })
    @RequestMapping(value = "/deleteuser",method = RequestMethod.GET)
    public Object deleteUser(HttpServletRequest request,int userid){
        SecondUser user = userRepository.findByUserid(userid);
        user.setIsdelete(true);
        userRepository.save(user);
        return new Message(1,"delete user success");
    }

    @ApiOperation("恢复已经删除用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = SwaggerParameter.Authorization, value = "token", dataType ="String",paramType = "query"),
            @ApiImplicitParam(name = "userid",value = "用户id",dataType = "int",paramType = "query")
    })
    @RequestMapping(value = "/recoveruser",method = RequestMethod.GET)
    public Object recoverUser(HttpServletRequest request,int userid){
        SecondUser user = userRepository.findByUserid(userid);
        user.setIsdelete(false);
        userRepository.save(user);
        return new Message(1,"recover user success");
    }
    /*
    删除非法物品
     */
    @ApiOperation("删除非法物品")
    @ApiImplicitParams({
            @ApiImplicitParam(name = SwaggerParameter.Authorization, value = "token", dataType ="String",paramType = "query"),
            @ApiImplicitParam(name = "articleid",value = "物品id",dataType = "int",paramType = "query")
    })
    @RequestMapping(value = "/deletearticle",method = RequestMethod.GET)
    public Object deleteArticle(HttpServletRequest request,int articleid){
        SecondArticle secondArticle =articleRepository.findOne(articleid);
        secondArticle.setIsdeal(-2);
        articleRepository.save(secondArticle);
        return new Message(1,"delete secondArticle success");
    }

    @ApiOperation("恢复删除物品")
    @ApiImplicitParams({
            @ApiImplicitParam(name = SwaggerParameter.Authorization, value = "token", dataType ="String",paramType = "query"),
            @ApiImplicitParam(name = "articleid",value = "物品id",dataType = "int",paramType = "query")
    })
    @RequestMapping(value = "/recoverarticle",method = RequestMethod.GET)
    public Object recover(HttpServletRequest request,int articleid){
        SecondArticle secondArticle =articleRepository.findOne(articleid);
        secondArticle.setIsdeal(0);
        articleRepository.save(secondArticle);
        return new Message(1,"recover secondArticle success");
    }

    /*
    删除非法评论
     */
    @ApiOperation("删除非法评论")
    @ApiImplicitParams({
            @ApiImplicitParam(name = SwaggerParameter.Authorization, value = "token", dataType ="String",paramType = "query"),
            @ApiImplicitParam(name = "reviewid",value = "评论id",dataType = "int",paramType = "query")
    })
    @RequestMapping(value = "/deletereview",method = RequestMethod.GET)
    public Object deleteReview(HttpServletRequest request,int reviewid){
        SecondReview secondReview =reviewRepository.findOne(reviewid);
        secondReview.setIsdelete(-2);
        reviewRepository.save(secondReview);
        return new Message(1,"delete secondReview success");
    }

    @ApiOperation("恢复已删除非法评论")
    @ApiImplicitParams({
            @ApiImplicitParam(name = SwaggerParameter.Authorization, value = "token", dataType ="String",paramType = "query"),
            @ApiImplicitParam(name = "reviewid",value = "评论id",dataType = "int",paramType = "query")
    })
    @RequestMapping(value = "/recoverreview",method = RequestMethod.GET)
    public Object recoverReview(HttpServletRequest request,int reviewid){
        SecondReview secondReview =reviewRepository.findOne(reviewid);
        secondReview.setIsdelete(0);
        reviewRepository.save(secondReview);
        return new Message(1,"recover secondReview success");
    }

    /*
    查看所有用户信息
     */
    @ApiOperation("查看所有用户信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = SwaggerParameter.Authorization, value = "token", dataType ="String",paramType = "query")
    })
    @RequestMapping(value = "/alluser",method = RequestMethod.GET)
    public Object allUser(HttpServletRequest request){
        return userRepository.findAll();
    }

    /*
    查看所有物品
     */
    @ApiOperation("查看所有物品")
    @ApiImplicitParams({
            @ApiImplicitParam(name = SwaggerParameter.Authorization, value = "token", dataType ="String",paramType = "query")
    })
    @RequestMapping(value = "/allarticle",method = RequestMethod.GET)
    public Object allArticle(HttpServletRequest request){
        return articleRepository.findAll();
    }

    /*
    查看所有评论
     */
    @ApiOperation("查看所有评论")
    @ApiImplicitParams({
            @ApiImplicitParam(name = SwaggerParameter.Authorization, value = "token", dataType ="String",paramType = "query")
    })
    @RequestMapping(value = "/allreview",method = RequestMethod.GET)
    public Object allReview(HttpServletRequest request){
        return reviewRepository.findAll();
    }
}
