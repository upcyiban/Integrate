package cn.edu.upc.yb.foodshare.controller;

import cn.edu.upc.yb.common.dto.SwaggerParameter;
import cn.edu.upc.yb.common.security.service.JwtTokenUtil;
import cn.edu.upc.yb.foodshare.dto.Message;
import cn.edu.upc.yb.foodshare.model.FoodArticle;
import cn.edu.upc.yb.foodshare.model.FoodReview;
import cn.edu.upc.yb.foodshare.model.FoodUser;
import cn.edu.upc.yb.foodshare.repository.FoodArticleRepository;
import cn.edu.upc.yb.foodshare.repository.FoodLikeRepository;
import cn.edu.upc.yb.foodshare.repository.FoodReviewRepository;
import cn.edu.upc.yb.foodshare.repository.FoodUserRepository;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping(value = "/foodshare/review")
public class FoodReviewController {
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
    private JwtTokenUtil jwtTokenUtil;

    @ApiOperation("新建一条评论")
    @ApiImplicitParams({
            @ApiImplicitParam(name = SwaggerParameter.Authorization, value = "token", dataType ="String",paramType = "query"),
            @ApiImplicitParam(name = "detail",value = "评论内容",dataType = "String",paramType = "query"),
            @ApiImplicitParam(name = "foodId",value = "评论菜品ID",dataType = "Integer",paramType = "query"),
    })
    @RequestMapping(value = "/public", method = RequestMethod.GET)
    public Object publicFood(String Authorization,String dtail,int foodId){
        int yibanId=Integer.valueOf(jwtTokenUtil.getYBidFromTocken(Authorization));
        FoodArticle foodArticle = foodArticleRepository.findOne(foodId);
        int reviewCount = foodArticle.getReview();
        foodArticle.setReview(reviewCount+1);
        foodArticleRepository.save(foodArticle);

        FoodUser foodUser = foodUserRepository.findOne(yibanId);
        FoodReview foodReview =new FoodReview();

        foodReview.setFoodid(foodId);
        foodReview.setDetail(dtail);
        foodReview.setCreatetime(new Date());
        foodReview.setUserid(yibanId);
        foodReview.setYbhead(foodUser.getYbhead());
        foodReview.setYbname(foodUser.getUsername());

        foodReviewRepository.save(foodReview);
        return foodReview;

    }

    @ApiOperation("在不是管理员的情况下，删除一条评论（只有发布此评论者或者是美食发布者可以删除）")
    @ApiImplicitParams({
            @ApiImplicitParam(name = SwaggerParameter.Authorization, value = "token", dataType ="String",paramType = "query"),
            @ApiImplicitParam(name = "reviewId",value = "评论ID",dataType = "Long",paramType = "query"),
    })
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public Object delete(String Authorization,Long reviewId){
        FoodReview foodReview = foodReviewRepository.findOne(reviewId);
        int yibanId=Integer.valueOf(jwtTokenUtil.getYBidFromTocken(Authorization));
        if(foodReview==null){
            return new Message(0,"none review exist");
        }
        else if(foodReview.getDelete()==true){
            return new Message(0,"this review has been deleted");
        }
        else if(foodReview.getUserid()!=yibanId||(foodArticleRepository.findOne(foodReview.getFoodid()).getUserid()!=yibanId)){
            return new Message(0,"can't delete");
        }
        else{
            foodReviewRepository.delete(foodReview);
            return new Message(1,"delete review success");
        }
    }

    @ApiOperation("获取美食的所有评论")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "foodId",value = "评论菜品ID",dataType = "int",paramType = "query"),
            @ApiImplicitParam(name = "pageSize",value = "页大小",dataType = "int",paramType = "query"),
            @ApiImplicitParam(name = "page",value = "页数",dataType = "int",paramType = "query")
    })
    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public Object getReview(int foodId,int pageSize,int page){

        if(foodArticleRepository.findOne(foodId)==null){
            return new Message(0,"food not exist!");
        }
        PageRequest pageable = new PageRequest(page,pageSize);
        return foodReviewRepository.findByFoodidAndAndDeleteAndOrderByCreatetimeDesc(foodId,false,pageable);
    }


}
