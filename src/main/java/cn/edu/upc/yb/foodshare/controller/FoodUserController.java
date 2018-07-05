package cn.edu.upc.yb.foodshare.controller;

import cn.edu.upc.yb.common.dto.SwaggerParameter;
import cn.edu.upc.yb.common.security.service.JwtTokenUtil;
import cn.edu.upc.yb.foodshare.model.FoodArticle;
import cn.edu.upc.yb.foodshare.model.FoodReview;
import cn.edu.upc.yb.foodshare.repository.FoodArticleRepository;
import cn.edu.upc.yb.foodshare.repository.FoodLikeRepository;
import cn.edu.upc.yb.foodshare.repository.FoodReviewRepository;
import cn.edu.upc.yb.foodshare.repository.FoodUserRepository;
import cn.edu.upc.yb.foodshare.service.FoodArticleService;
import cn.edu.upc.yb.foodshare.service.FoodUserService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(value = "/foodshare/user")
public class FoodUserController {

    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    private FoodArticleService foodArticleService;

    @Autowired
    private FoodUserRepository foodUserRepository;

    @Autowired
    private FoodArticleRepository foodArticleRepository;
    @Autowired
    private FoodLikeRepository foodLikeRepository;
    @Autowired
    private FoodReviewRepository foodReviewRepository;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private FoodUserService foodUserService;

    @ApiOperation("判断用户是否存在")
    @ApiImplicitParam(paramType = "Query", name = SwaggerParameter.Authorization, value = "token",dataType = "String")
    @RequestMapping(value = "/exist",method = RequestMethod.GET)
    public Object isExist(String Authorization){
        return foodUserService.isExist(Authorization);
    }

    @ApiOperation("注册用户信息")
    @ApiImplicitParam(paramType = "Query", name = SwaggerParameter.Authorization,value = "token",dataType = "String")
    @RequestMapping(value = "/sign",method = RequestMethod.POST)
    public Object signUp(String Authorization){
        return foodUserService.addUser(Authorization);
    }

    @ApiOperation("返回用户基本信息")
    @ApiImplicitParam(paramType = "Query",name = SwaggerParameter.Authorization, value = "token",dataType = "String")
    @RequestMapping(value = "/info",method = RequestMethod.GET)
    public Object getInfo(String Authorization){

        return foodUserService.userInfo(Authorization);
    }

    @ApiOperation("是否有审核通过的消息，true代表有新消息，false代表没有，当查看发布消息更改ischeck属性时要根据是否通过审核")
    @ApiImplicitParam(paramType = "Query", name = SwaggerParameter.Authorization, dataType = "String")
    @RequestMapping("/new")
    public Object isnew(HttpServletRequest request){
        String token = request.getParameter(this.tokenHeader);
        return foodUserService.ischeck(token);
    }

    @ApiOperation("删除已发布的菜品")
    @ApiImplicitParams({
            @ApiImplicitParam(name = SwaggerParameter.Authorization,value = "token",paramType = "Query",dataType = "String"),
            @ApiImplicitParam(name = "foodid",value = "id",paramType = "Query",dataType = "Integer")
    })
    @RequestMapping(value = "/delete")
    public Object deleteFood(String Authorization,int foodid){
        return foodArticleService.deleteFood(foodid);
    }

    @ApiOperation("获取点赞的菜品")
    @ApiImplicitParams({
            @ApiImplicitParam(name = SwaggerParameter.Authorization,value = "token",paramType = "Query",dataType = "String"),
            @ApiImplicitParam(name = "pageSize",value = "页大小",dataType = "int",paramType = "query"),
            @ApiImplicitParam(name = "page",value = "页数",dataType = "int",paramType = "query")

    })
    @RequestMapping(value = "/getLike")
    public Object getLikeFood(String Authorization,int pageSize,int page){
        int ybid = Integer.valueOf(jwtTokenUtil.getYBidFromTocken(Authorization));
        return foodArticleService.getLikeFood(ybid,pageSize,page);
    }


    @ApiOperation("获取收藏的菜品")
    @ApiImplicitParams({
            @ApiImplicitParam(name = SwaggerParameter.Authorization,value = "token",paramType = "Query",dataType = "String"),
            @ApiImplicitParam(name = "pageSize",value = "页大小",dataType = "int",paramType = "query"),
            @ApiImplicitParam(name = "page",value = "页数",dataType = "int",paramType = "query")
    })
    @RequestMapping(value = "/getCollectionFood")
    public Object getCollectionFood(String Authorization,int pageSize,int page){
        int ybid = Integer.valueOf(jwtTokenUtil.getYBidFromTocken(Authorization));
        return foodArticleService.getCollectionFood(ybid,pageSize,page);
    }

    @ApiOperation("获取评论过的菜品")
    @ApiImplicitParams({
            @ApiImplicitParam(name = SwaggerParameter.Authorization,value = "token",paramType = "Query",dataType = "String"),
            @ApiImplicitParam(name = "pageSize",value = "页大小",dataType = "int",paramType = "query"),
            @ApiImplicitParam(name = "page",value = "页数",dataType = "int",paramType = "query")
    })
    @RequestMapping(value = "/reviewFood")
    public Object getReviewFood(String Authorization,int pageSize,int page){
        int ybid = Integer.valueOf(jwtTokenUtil.getYBidFromTocken(Authorization));
        return foodArticleService.getReviewFood(ybid,pageSize,page);
    }


    @ApiOperation("获取已发布的菜品包括通过审核和为通过审核以及在审核的,美食状态0表示通过审核，1表示待审核，-2表示未通过审核")
    @ApiImplicitParams({
            @ApiImplicitParam(name = SwaggerParameter.Authorization,value = "token",paramType = "Query",dataType = "String"),
            @ApiImplicitParam(name = "pageSize",value = "页大小",dataType = "int",paramType = "query"),
            @ApiImplicitParam(name = "page",value = "页数",dataType = "int",paramType = "query")
    })
    @RequestMapping(value = "/getOwnFood")
    public Object getOwnFood(String Authorization,int pageSize,int page){

        int ybid = Integer.valueOf(jwtTokenUtil.getYBidFromTocken(Authorization));
        return foodArticleService.getOwnFood(ybid,pageSize,page);
    }
}
