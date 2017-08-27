package cn.edu.upc.yb.photo.controller;


import cn.edu.upc.yb.common.dto.SwaggerParameter;
import cn.edu.upc.yb.common.service.UserService;
import cn.edu.upc.yb.photo.service.PhotoService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.jws.Oneway;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/photo")
public class PhotoController {

    @Autowired
    private UserService userService;

    @Autowired
    private PhotoService photoService;


    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = SwaggerParameter.Authorization, dataType = "String"),
    })
    @GetMapping("/getphoto")
    public Object getId(HttpServletRequest request) throws IOException {
        String name = userService.getStuName(request).toString();
        String stuid = userService.getStuId(request).toString();
        boolean isexist = photoService.isExist(stuid);
        String url = "http://yb.upc.edu.cn/picture/photo2017/" + stuid + ".jpg";
        Map<String,Object> map = new HashMap<>();
        map.put("name",name);
        map.put("stuId",stuid);
        map.put("isExist",isexist);
        map.put("url",url);
        return map;
    }
}
