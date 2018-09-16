package cn.edu.upc.yb.foodshare.controller;

import cn.edu.upc.yb.common.dto.SwaggerParameter;
import cn.edu.upc.yb.common.security.service.JwtTokenUtil;
import cn.edu.upc.yb.foodshare.dto.Message;
import cn.edu.upc.yb.foodshare.model.FoodArticle;
import cn.edu.upc.yb.foodshare.model.FoodCollection;
import cn.edu.upc.yb.foodshare.repository.FoodArticleRepository;
import cn.edu.upc.yb.foodshare.repository.FoodCollectionRepository;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping(value = "/foodshare/collection")
public class FoodCollectionController {
    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    private FoodArticleRepository foodArticleRepository;
    @Autowired
    private FoodCollectionRepository foodCollectionRepository;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @ApiOperation("判断用户是否收藏")
    @ApiImplicitParams({
            @ApiImplicitParam(name = SwaggerParameter.Authorization,value = "token",paramType = "Query",dataType = "String"),
            @ApiImplicitParam(name = "foodId",value = "菜品ID",dataType = "Integer",paramType = "Query")
    })
    @RequestMapping(value = "/check",method = RequestMethod.GET)
    public Object isCollection(String Authorization,int foodId){
        int ybid = Integer.valueOf(jwtTokenUtil.getYBidFromTocken(Authorization));
        FoodCollection foodCollection = foodCollectionRepository.findByFoodidAndUserid(foodId,ybid);
        if(foodCollection ==null){
            return false;
        }
        else return true;
    }

    @ApiOperation("收藏，如果用户已经收藏则取消收藏")
    @ApiImplicitParams({
            @ApiImplicitParam(name = SwaggerParameter.Authorization,value = "token",paramType = "Query",dataType = "String"),
            @ApiImplicitParam(name = "foodId",value = "菜品ID",dataType = "Integer",paramType = "Query")
    })
    @RequestMapping(value = "/add",method = RequestMethod.GET)
    public Object collection(String Authorization,int foodId){
        FoodArticle foodArticle = foodArticleRepository.findOne(foodId);
        if(foodArticle==null){
            return new Message(0,"this article not exist");
        }else if(foodArticle.getState()!=0){
            return new Message(0,"can't collect,this article maybe deleted by administrator or creator");
        }
        int collectionCount = foodArticle.getCollection();
        int ybid = Integer.valueOf(jwtTokenUtil.getYBidFromTocken(Authorization));
        FoodCollection foodCollection = foodCollectionRepository.findByFoodidAndUserid(ybid,foodId);
        if(foodCollection==null){
            foodCollection = new FoodCollection();

            foodCollection.setCreatetime(new Date());
            foodCollection.setUserid(ybid);
            foodCollection.setFoodid(foodId);

            foodCollectionRepository.save(foodCollection);

            foodArticle.setCollection(collectionCount+1);
            foodArticleRepository.save(foodArticle);
            return new Message(1,"collection success!");
        }
        else{
            foodCollectionRepository.delete(foodCollection);
            foodArticle.setCollection(collectionCount-1);
            foodArticleRepository.save(foodArticle);
            return new Message(1,"cancel collection success!");
        }


    }



}
