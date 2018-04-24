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


    @ApiOperation("注册用户信息")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = SwaggerParameter.Authorization, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "Qq", dataType = "String",value = "用户qq")
    })
    @RequestMapping(value = "/signup",method = RequestMethod.GET)
    public Object signup(HttpServletRequest request,String Qq){
        return secondUserService.addUser(request,Qq);
    }

    @ApiOperation("判断用户是否存在")
    @ApiImplicitParam(paramType = "query", name = SwaggerParameter.Authorization, dataType = "String")
    @RequestMapping(value = "/exist",method = RequestMethod.GET)
    public Object isExist(HttpServletRequest request){
        return secondUserService.isExist(request);
    }

    @ApiOperation("增加用户email")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = SwaggerParameter.Authorization, dataType = "String"),
            @ApiImplicitParam(name = "email",value = "邮箱",dataType = "String",paramType = "query")
    })
    @RequestMapping(value = "/addemail",method = RequestMethod.GET)
    public Object addEmail(HttpServletRequest request,String email){
        return secondUserService.addemail(request,email);
    }

    @ApiOperation("增加用户wcaht")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = SwaggerParameter.Authorization, dataType = "String"),
            @ApiImplicitParam(name = "wchat",value = "微信",dataType = "String",paramType = "query")
    })
    @RequestMapping(value = "/addwchat",method = RequestMethod.GET)
    public Object addWchat(HttpServletRequest request,String wchat){
        return secondUserService.addwchat(request,wchat);
    }

    @ApiOperation("增加用户phone")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = SwaggerParameter.Authorization, dataType = "String"),
            @ApiImplicitParam(name = "phone",value = "电话",dataType = "String",paramType = "query")
    })
    @RequestMapping(value = "/addphone",method = RequestMethod.GET)
    public Object addPhone(HttpServletRequest request,String phone){
        return secondUserService.addphone(request,phone);
    }

}
