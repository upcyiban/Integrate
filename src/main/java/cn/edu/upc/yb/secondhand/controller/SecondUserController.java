package cn.edu.upc.yb.secondhand.controller;

import cn.edu.upc.yb.common.dto.SwaggerParameter;
import cn.edu.upc.yb.common.security.service.JwtTokenUtil;
import cn.edu.upc.yb.common.ybapi.UserMe;
import cn.edu.upc.yb.secondhand.dto.Message;
import cn.edu.upc.yb.secondhand.repository.UserRepository;
import cn.edu.upc.yb.secondhand.service.SecondUserService;
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
@RequestMapping("/second/user")
public class SecondUserController {

    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SecondUserService secondUserService;

    @ApiOperation("获取用户信息")
    @ApiImplicitParam(paramType = "query", name = SwaggerParameter.Authorization, dataType = "String")
    @RequestMapping(value = "/info",method = RequestMethod.GET)
    public Object userInfo(HttpServletRequest request){
        if (!secondUserService.isExist(request)){
            return new Message(0,"null user!");
        }
        return secondUserService.userInfo(request);
    }

    @ApiOperation("获取其他用户信息")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = SwaggerParameter.Authorization, dataType = "String"),
            @ApiImplicitParam(paramType = "query",name = "userid",value = "用户易班id",dataType = "String")
    })
    @RequestMapping(value = "/otherinfo",method = RequestMethod.GET)
    public Object otherUserInfo(HttpServletRequest request,int userid){
        return userRepository.findByUserid(userid);
    }

    @ApiOperation("注册用户信息")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = SwaggerParameter.Authorization, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "qq", dataType = "String",value = "用户qq"),
            @ApiImplicitParam(name = "phone",value = "电话",dataType = "String",paramType = "query"),
            @ApiImplicitParam(name = "wchat",value = "微信",dataType = "String",paramType = "query"),
            @ApiImplicitParam(name = "email",value = "邮箱",dataType = "String",paramType = "query")
    })
    @RequestMapping(value = "/signup",method = RequestMethod.POST)
    public Object signup(HttpServletRequest request,String qq,String phone,String wchat,String email){

        return secondUserService.addUser(request,qq,phone,wchat,email);
    }

    @ApiOperation("判断用户是否存在")
    @ApiImplicitParam(paramType = "query", name = SwaggerParameter.Authorization, dataType = "String")
    @RequestMapping(value = "/exist",method = RequestMethod.GET)
    public Object isExist(HttpServletRequest request){
        return secondUserService.isExist(request);
    }


    @ApiOperation("更新用户联系方式信息")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = SwaggerParameter.Authorization, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "qq", dataType = "String",value = "用户qq"),
            @ApiImplicitParam(name = "phone",value = "电话",dataType = "String",paramType = "query"),
            @ApiImplicitParam(name = "wchat",value = "微信",dataType = "String",paramType = "query"),
            @ApiImplicitParam(name = "email",value = "邮箱",dataType = "String",paramType = "query")
    })
    @RequestMapping(value = "/updateuserinfo",method = RequestMethod.POST)
    public Object addPhone(HttpServletRequest request,String qq,String phone,String wchat,String email){
         return secondUserService.updateUserInfo(request,qq,phone,wchat,email);
    }

}
