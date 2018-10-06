package cn.edu.upc.yb.foodshare.controller;

import cn.edu.upc.yb.common.dto.SwaggerParameter;
import cn.edu.upc.yb.common.security.service.JwtTokenUtil;
import cn.edu.upc.yb.foodshare.service.FoodArticleService;
import cn.edu.upc.yb.foodshare.service.FoodUserService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/foodshare/user")
public class FoodUserController {

    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    private FoodArticleService foodArticleService;
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
    @RequestMapping(value = "/new",method = RequestMethod.GET)
    public Object isnew(HttpServletRequest request){
        String token = request.getParameter(this.tokenHeader);
        return foodUserService.ischeck(token);
    }

    @ApiOperation("删除已发布的菜品")
    @ApiImplicitParams({
            @ApiImplicitParam(name = SwaggerParameter.Authorization,value = "token",paramType = "Query",dataType = "String"),
            @ApiImplicitParam(name = "foodId",value = "id",paramType = "Query",dataType = "Integer")
    })
    @RequestMapping(value = "/delete",method = RequestMethod.GET)
    public Object deleteFood(String Authorization,int foodId){
        int ybid = Integer.valueOf(jwtTokenUtil.getYBidFromTocken(Authorization));
        return foodArticleService.deleteFood(foodId,ybid);
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

}


