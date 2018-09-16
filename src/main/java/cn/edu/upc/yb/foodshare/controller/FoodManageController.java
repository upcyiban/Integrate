package cn.edu.upc.yb.foodshare.controller;

import cn.edu.upc.yb.foodshare.dto.Message;
import cn.edu.upc.yb.foodshare.model.FoodArticle;
import cn.edu.upc.yb.foodshare.model.FoodReview;
import cn.edu.upc.yb.foodshare.repository.FoodArticleRepository;
import cn.edu.upc.yb.foodshare.repository.FoodReviewRepository;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = "/foodshare/manage")
@RestController
@PreAuthorize("hasRole('ROLE_SECOND_ADMIN')")
public class FoodManageController {

    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    private FoodArticleRepository foodArticleRepository;
    @Autowired
    private FoodReviewRepository foodReviewRepository;

    @ApiOperation("删除已发布的菜品")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "foodId",value = "id",paramType = "Query",dataType = "Integer")
    })
    @RequestMapping(value = "/delete",method = RequestMethod.GET)
    public Object deleteFood(int foodId) {
        FoodArticle foodArticle = foodArticleRepository.findOne(foodId);
        if (foodArticle == null) {
            return new Message(0, "null article");
        }
        foodArticle.setState(-2);
        foodArticleRepository.save(foodArticle);
        return new Message(1, "delete success!");
    }

    @ApiOperation("审核菜品是否通过，0表示通过，-2表示审核未通过")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "foodId",value = "id",paramType = "Query",dataType = "Integer"),
            @ApiImplicitParam(name = "pass",value = "是否通过审核",paramType = "Query",dataType = "Integer")
    })
    @RequestMapping(value = "/check",method = RequestMethod.GET)
    public Object checkFood(int foodId,int pass) {
        FoodArticle foodArticle = foodArticleRepository.findOne(foodId);
        foodArticle.setState(pass);
        foodArticleRepository.save(foodArticle);
        return "审核完成";
    }

    @ApiOperation("删除非法评论")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "reviewId",value = "评论ID",dataType = "Long",paramType = "query"),
    })
    @RequestMapping(value = "/review", method = RequestMethod.GET)
    public Object delete(Long reviewId){
        FoodReview foodReview = foodReviewRepository.findOne(reviewId);
        foodReview.setDelete(1);
        foodReviewRepository.save(foodReview);
        return new Message(1,"delete review success!");
    }

//    @ApiOperation("添加标签")


}
