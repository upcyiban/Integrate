package cn.edu.upc.yb.foodshare.controller;

import cn.edu.upc.yb.common.dto.SwaggerParameter;
import cn.edu.upc.yb.common.ybapi.SchoolAwardwx;
import cn.edu.upc.yb.foodshare.dto.Message;
import cn.edu.upc.yb.foodshare.model.FoodArticle;
import cn.edu.upc.yb.foodshare.model.FoodReview;
import cn.edu.upc.yb.foodshare.model.FoodUser;
import cn.edu.upc.yb.foodshare.repository.FoodArticleRepository;
import cn.edu.upc.yb.foodshare.repository.FoodReviewRepository;
import cn.edu.upc.yb.foodshare.repository.FoodUserRepository;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RequestMapping(value = "/foodshare/manage")
@RestController
@PreAuthorize("hasRole('ROLE_SECOND_ADMIN')")
public class FoodManageController {

    @Value("${jwt.header}")
    private String tokenHeader;

    private SchoolAwardwx schoolAwardwx = new SchoolAwardwx();

    @Autowired
    private FoodArticleRepository foodArticleRepository;
    @Autowired
    private FoodReviewRepository foodReviewRepository;

    @ApiOperation("获取已经通过审核的菜品，分页查询,首页页数为0，待审核状态为1，0表示通过审核，-2表示未通过审核")
    @ApiImplicitParams({
            @ApiImplicitParam(name = SwaggerParameter.Authorization,value = "token",paramType = "Query",dataType = "String"),
            @ApiImplicitParam(name = "pageSize",value = "页大小",dataType = "int",paramType = "query"),
            @ApiImplicitParam(name = "page",value = "页数",dataType = "int",paramType = "query")
    })
    @RequestMapping(value = "/getCheckFood",method = RequestMethod.GET)
    public Page<FoodArticle> getCheckFood(String Authorization, int pageSize, int page){
        Pageable pageable=new PageRequest(page,pageSize);
        return foodArticleRepository.findByStateOrderByCreatetime(0,pageable);
    }

    @ApiOperation("获取未通过审核的菜品，分页查询，首页页数为0,待审核状态为1，0表示通过审核，-2表示未通过审核")
    @ApiImplicitParams({
            @ApiImplicitParam(name = SwaggerParameter.Authorization,value = "token",paramType = "Query",dataType = "String"),
            @ApiImplicitParam(name = "pageSize",value = "页大小",dataType = "int",paramType = "query"),
            @ApiImplicitParam(name = "page",value = "页数",dataType = "int",paramType = "query")
    })
    @RequestMapping(value = "/getFailFood",method = RequestMethod.GET)
    public Page<FoodArticle> getFailFood(String Authorization, int pageSize, int page){
        Pageable pageable=new PageRequest(page,pageSize);
        return foodArticleRepository.findByStateOrderByCreatetime(-2,pageable);
    }

    @ApiOperation("获取待审核的菜品，分页查询，首页页数为0,待审核状态为1，0表示通过审核，-2表示未通过审核")
    @ApiImplicitParams({
            @ApiImplicitParam(name = SwaggerParameter.Authorization,value = "token",paramType = "Query",dataType = "String"),
            @ApiImplicitParam(name = "pageSize",value = "页大小",dataType = "int",paramType = "query"),
            @ApiImplicitParam(name = "page",value = "页数",dataType = "int",paramType = "query")
    })
    @RequestMapping(value = "/getFood",method = RequestMethod.GET)
    public Page<FoodArticle> getFood(String Authorization, int pageSize, int page){
        Pageable pageable=new PageRequest(page,pageSize);
        return foodArticleRepository.findByStateOrderByCreatetime(1,pageable);
    }


    @ApiOperation("删除已发布的菜品")
    @ApiImplicitParams({
            @ApiImplicitParam(name = SwaggerParameter.Authorization,value = "token",paramType = "Query",dataType = "String"),
            @ApiImplicitParam(name = "foodId",value = "id",paramType = "Query",dataType = "Integer")
    })
    @RequestMapping(value = "/delete",method = RequestMethod.GET)
    public Object deleteFood(String Authorization,int foodId) {
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
            @ApiImplicitParam(name = SwaggerParameter.Authorization,value = "token",paramType = "Query",dataType = "String"),
            @ApiImplicitParam(name = "foodId",value = "id",paramType = "Query",dataType = "Integer"),
            @ApiImplicitParam(name = "pass",value = "是否通过审核",paramType = "Query",dataType = "Integer")
    })
    @RequestMapping(value = "/check",method = RequestMethod.GET)
    public Object checkFood(String Authorization,int foodId,int pass) throws IOException {
        FoodArticle foodArticle = foodArticleRepository.findOne(foodId);
        if(foodArticle==null){
            return new Message(0,"此菜品不存在！请输入正确的菜品ID");
        }
        else {
            if(pass==0){
                foodArticle.setState(pass);
                foodArticleRepository.save(foodArticle);
                Boolean flag = schoolAwardwx.schoolAwardwx(Authorization,foodArticle.getUserid(),20);
                if(flag){
                    return new Message(1,"菜品通过审核,用户获取20网薪");
                }
                else{
                    foodArticle.setState(1);
                    foodArticleRepository.save(foodArticle);
                    return new Message(0,"请求网薪奖励失败，请重新审核");
                }

            }
            else if(pass==-2){
                foodArticle.setState(pass);
                foodArticleRepository.save(foodArticle);
                return new Message(1,"菜品未通过审核");
            }
            else{
                return new Message(0,"请输入0或-2");
            }
        }
    }

    @ApiOperation("删除非法评论")
    @ApiImplicitParams({
            @ApiImplicitParam(name = SwaggerParameter.Authorization,value = "token",paramType = "Query",dataType = "String"),
            @ApiImplicitParam(name = "reviewId",value = "评论ID",dataType = "Long",paramType = "query"),
    })
    @RequestMapping(value = "/review", method = RequestMethod.GET)
    public Object delete(String Authorization,Long reviewId){
        FoodReview foodReview = foodReviewRepository.findOne(reviewId);
        foodReview.setDelete(1);
        foodReviewRepository.save(foodReview);
        return new Message(1,"delete review success!");
    }

    @ApiOperation(value = "判断管理员")
    @ApiImplicitParams({
            @ApiImplicitParam(name = SwaggerParameter.Authorization,value = "token",paramType = "Query",dataType = "String"),
    })
    @RequestMapping(value = "/login",method = RequestMethod.GET)
    Object login(String Authorization){
        return true;
    }

}
