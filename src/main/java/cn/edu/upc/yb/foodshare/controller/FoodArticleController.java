package cn.edu.upc.yb.foodshare.controller;

import cn.edu.upc.yb.common.dto.SwaggerParameter;
import cn.edu.upc.yb.common.security.service.JwtTokenUtil;
import cn.edu.upc.yb.foodshare.repository.FoodArticleRepository;
import cn.edu.upc.yb.foodshare.service.FoodArticleService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/foodshare/food")
public class FoodArticleController {

    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    private FoodArticleRepository foodArticleRepository;

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
    @RequestMapping(value = "/public", method = RequestMethod.POST)
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
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public Object updateFood(String Authorization,int foodId,String name,String kind,String detail,String imgurl,String price,String address){
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

    @ApiOperation("获取点赞的菜品")
    @ApiImplicitParams({
            @ApiImplicitParam(name = SwaggerParameter.Authorization,value = "token",paramType = "Query",dataType = "String"),
    })
    @RequestMapping(value = "/getlike",method = RequestMethod.GET)
    public Object getLikeFood(String Authorization){
        int ybid = Integer.valueOf(jwtTokenUtil.getYBidFromTocken(Authorization));
        return foodArticleService.getLikeFood(ybid);
    }


    @ApiOperation("获取收藏的菜品")
    @ApiImplicitParams({
            @ApiImplicitParam(name = SwaggerParameter.Authorization,value = "token",paramType = "Query",dataType = "String"),
    })
    @RequestMapping(value = "/collection",method = RequestMethod.GET)
    public Object getCollectionFood(String Authorization){
        int ybid = Integer.valueOf(jwtTokenUtil.getYBidFromTocken(Authorization));
        return foodArticleService.getCollectionFood(ybid);
    }

    @ApiOperation("获取评论过的菜品")
    @ApiImplicitParams({
            @ApiImplicitParam(name = SwaggerParameter.Authorization,value = "token",paramType = "Query",dataType = "String"),
    })
    @RequestMapping(value = "/review",method = RequestMethod.GET)
    public Object getReviewFood(String Authorization){
        int ybid = Integer.valueOf(jwtTokenUtil.getYBidFromTocken(Authorization));
        return foodArticleService.getReviewFood(ybid);
    }


    @ApiOperation("获取已发布的菜品包括通过审核和为通过审核以及在审核的,美食状态0表示通过审核，1表示待审核，-2表示未通过审核")
    @ApiImplicitParams({
            @ApiImplicitParam(name = SwaggerParameter.Authorization,value = "token",paramType = "Query",dataType = "String"),
    })
    @RequestMapping(value = "/own",method = RequestMethod.GET)
    public Object getOwnFood(String Authorization){
        int ybid = Integer.valueOf(jwtTokenUtil.getYBidFromTocken(Authorization));
        return foodArticleService.getOwnFood(ybid);
    }

    @ApiOperation("浏览菜品，需要分页参数")
    @ApiImplicitParams({
            @ApiImplicitParam(name = SwaggerParameter.Authorization, value = "token", dataType ="String",paramType = "query"),
            @ApiImplicitParam(name = "pagesize",value = "页大小",dataType = "int",paramType = "query"),
            @ApiImplicitParam(name = "page",value = "页数",dataType = "int",paramType = "query")
    })
    @RequestMapping(value = "/browse",method = RequestMethod.GET)
    public Object browseFood(String Authorization,int pagesize,int page){
        Pageable pageable=new PageRequest(page,pagesize);
        return foodArticleRepository.findByStateOrderByCreatetime(0,pageable);
    }

    @ApiOperation("标签搜索，分页参数")
    @ApiImplicitParams({
            @ApiImplicitParam(name = SwaggerParameter.Authorization, value = "token", dataType ="String",paramType = "query"),
            @ApiImplicitParam(name = "kind", value = "标签", dataType = "String", paramType = "query")
    })
    @RequestMapping(value = "/kind",method = RequestMethod.GET)
    public Object titleSearch(String Authorization,String kind){
        return foodArticleService.kindSearch(kind);
    }

    @ApiOperation("菜品名称搜索")
    @ApiImplicitParams({
            @ApiImplicitParam(name = SwaggerParameter.Authorization, value = "token", dataType ="String",paramType = "query"),
            @ApiImplicitParam(name = "name", value = "菜品名", dataType = "String", paramType = "query")
    })
    @RequestMapping(value = "/name",method = RequestMethod.GET)
    public Object nameSearch(String Authorization,String name){
        return foodArticleService.nameSearch(name);
    }
}
