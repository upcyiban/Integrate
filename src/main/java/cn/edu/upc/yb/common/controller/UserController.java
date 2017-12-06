package cn.edu.upc.yb.common.controller;


import cn.edu.upc.yb.common.dto.SwaggerParameter;
import cn.edu.upc.yb.common.service.UserService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RestController
@RequestMapping("/common/user")
public class UserController {

    @Autowired
    private UserService userService;


    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = SwaggerParameter.Authorization, dataType = "String"),
    })
    @GetMapping("/getStuId")
    public Object getId(HttpServletRequest request) throws IOException {


        return userService.getStuId(request);

    }
}
