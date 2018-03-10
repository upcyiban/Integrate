package cn.edu.upc.yb.feedback.controller;

import cn.edu.upc.yb.common.dto.SwaggerParameter;
import cn.edu.upc.yb.feedback.service.FeedBackService;



import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by lylll on 2017/6/4.
 */

@RestController
@RequestMapping("/feedback")
public class FeedBackController {


    @Autowired
    private FeedBackService feedBackService;


    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = SwaggerParameter.Authorization,value = "token",dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "message",  dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = SwaggerParameter.Authorization, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "message", dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "appname", dataType = "String"),

    })
    @PostMapping("/create")
    public ResponseEntity<?> createFeedback(HttpServletRequest request, String message, String appname) {
        return ResponseEntity.ok(feedBackService.doFeedBack(request, message, appname));
    }

    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = SwaggerParameter.Authorization, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = SwaggerParameter.AppName, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "id", value = "该条数据的id", required = true, dataType = "String")})

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public Object deleteOne(int id) {
        return ResponseEntity.ok(feedBackService.delete(id));
    }

    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = SwaggerParameter.Authorization, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = SwaggerParameter.AppName, dataType = "String")})
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public Object showAll() {
        return ResponseEntity.ok(feedBackService.showAll());
    }


    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = SwaggerParameter.Authorization, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = SwaggerParameter.AppName, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "message", required = true, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "appname", required = true, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "id", value = "该条数据的id", required = true, dataType = "String")
    })

  @RequestMapping(value = "/modify",method = RequestMethod.POST)
    public Object modify(String message,String appname,int id) {
       return ResponseEntity.ok(feedBackService.modtify(message, appname, id));

    }

    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = SwaggerParameter.Authorization, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = SwaggerParameter.AppName, dataType = "String"),

    @ApiImplicitParam(paramType = "query", name = "id", value = "该条数据的id", dataType = "String")})
    @RequestMapping(value = "/findOne", method = RequestMethod.GET)
    public Object find(int id) {

        return ResponseEntity.ok(feedBackService.showOne(id));

    }


}
