package cn.edu.upc.yb.foodshare.controller;

import cn.edu.upc.yb.common.dto.SwaggerParameter;
import cn.edu.upc.yb.common.security.service.JwtTokenUtil;
import cn.edu.upc.yb.foodshare.dto.Message;
import cn.edu.upc.yb.foodshare.model.FoodArticle;
import cn.edu.upc.yb.foodshare.model.FoodLike;
import cn.edu.upc.yb.foodshare.model.FoodReview;
import cn.edu.upc.yb.foodshare.repository.FoodArticleRepository;
import cn.edu.upc.yb.foodshare.repository.FoodLikeRepository;
import cn.edu.upc.yb.foodshare.repository.FoodReviewRepository;
import cn.edu.upc.yb.foodshare.repository.FoodUserRepository;
import cn.edu.upc.yb.foodshare.service.FoodArticleService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(value = "/foodshare/food")
public class FoodArticleController {

    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    private FoodArticleRepository foodArticleRepository;

    @Autowired
    private FoodLikeRepository foodLikeRepository;
    @Autowired
    private FoodUserRepository foodUserRepository;
    @Autowired
    private FoodReviewRepository foodReviewRepository;
    @Autowired
    private FoodArticleService foodArticleService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @ApiOperation("发布菜品")
    @ApiImplicitParams({
            @ApiImplicitParam(name = SwaggerParameter.Authorization, value = "token", dataType ="String",paramType = "query"),
            @ApiImplicitParam(name = "name",value = "菜品名称",dataType = "String",paramType = "query"),
            @ApiImplicitParam(name = "kind",value = "菜品种类",dataType = "String",paramType = "query"),
            @ApiImplicitParam(name = "detail",value = "菜品详情",dataType = "String",paramType = "query"),
            @ApiImplicitParam(name = "imgurl",value = "菜品图片链接",dataType = "String",paramType = "query"),
            @ApiImplicitParam(name = "price",value = "菜品价格",dataType = "String",paramType = "query"),
            @ApiImplicitParam(name = "address",value = "菜品地址",dataType = "String",paramType = "query")
    })
    @RequestMapping(value = "/public", method = RequestMethod.GET)
    public Object publicFood(String Authorization,String name,String kind,String detail,String imgurl,String price,String address){
        int ybid = Integer.valueOf(jwtTokenUtil.getYBidFromTocken(Authorization));
        return foodArticleService.publicFood(ybid,name,kind,detail,imgurl,price,address);
    }

    @ApiOperation("更新菜品信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = SwaggerParameter.Authorization, value = "token", dataType ="String",paramType = "query"),
            @ApiImplicitParam(name = "foodId",value = "菜品id",dataType = "Integer",paramType = "query"),
            @ApiImplicitParam(name = "name",value = "菜品名称",dataType = "String",paramType = "query"),
            @ApiImplicitParam(name = "kind",value = "菜品种类",dataType = "String",paramType = "query"),
            @ApiImplicitParam(name = "detail",value = "菜品详情",dataType = "String",paramType = "query"),
            @ApiImplicitParam(name = "imgurl",value = "菜品图片链接",dataType = "String",paramType = "query"),
            @ApiImplicitParam(name = "price",value = "菜品价格",dataType = "String",paramType = "query"),
            @ApiImplicitParam(name = "address",value = "菜品地址",dataType = "String",paramType = "query")
    })
    @RequestMapping(value = "/update")
    public Object updateFood(int foodId,String name,String kind,String detail,String imgurl,String price,String address){
            return foodArticleService.updateFood(foodId,name,kind,detail,imgurl,price,address);
    }

    @ApiOperation("点赞，如果用户已经点赞则取消点赞")
    @ApiImplicitParams({
            @ApiImplicitParam(name = SwaggerParameter.Authorization,value = "token",paramType = "Query",dataType = "String"),
            @ApiImplicitParam(name = "foodId",value = "菜品ID",dataType = "Integer",paramType = "Query")
    })
    @RequestMapping(value = "/like",method = RequestMethod.GET)
    public Object like(String Authorization,int foodId){
        int ybid = Integer.valueOf(jwtTokenUtil.getYBidFromTocken(Authorization));
        return foodArticleService.like(ybid,foodId);
    }

    @ApiOperation("判断用户是否点赞")
    @ApiImplicitParams({
            @ApiImplicitParam(name = SwaggerParameter.Authorization,value = "token",paramType = "Query",dataType = "String"),
            @ApiImplicitParam(name = "foodId",value = "菜品ID",dataType = "Integer",paramType = "Query")
    })
    @RequestMapping(value = "/like",method = RequestMethod.GET)
    public Object isLike(String Authorization,int foodId){
        int ybid = Integer.valueOf(jwtTokenUtil.getYBidFromTocken(Authorization));
        return foodArticleService.isLike(ybid,foodId);
    }

    public Object browerFood(){
        return "1";
    }



//    @ApiOperation("获取菜品内容")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = SwaggerParameter.Authorization,value = "token",paramType = "Query",dataType = "String"),
//            @ApiImplicitParam(name = "foodid",value = "id",paramType = "Query",dataType = "Integer")
//    })
//    public Object getFoodInfo(){
//
//    }




}
