package cn.edu.upc.yb.manage.controller;

import cn.edu.upc.yb.common.dto.SwaggerParameter;
import cn.edu.upc.yb.common.security.model.AppRepository;
import cn.edu.upc.yb.manage.service.ManageService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by lylll on 2017/6/3.
 */
@RestController
@RequestMapping("/manage")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class ManageController {

    @Autowired
    private AppRepository appRepository;

    @Autowired
    private ManageService manageService;

    @ApiOperation(value = "创建app", notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = SwaggerParameter.Authorization, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = SwaggerParameter.AppName, dataType = "String"),
            @ApiImplicitParam(paramType = "query",name = "appname", value = "应用的名字", required = true,dataType = "String"),
            @ApiImplicitParam(paramType = "query",name = "appid",value = "应用的appid",required = true,dataType = "String"),
            @ApiImplicitParam(paramType = "query",name = "appkey",value = "应用的appkey",required = true,dataType = "String")})
    @PostMapping("/create")
    public ResponseEntity<?> createApp(String appname, String appid, String appkey) {
        return ResponseEntity.ok(manageService.createApp(appname, appid, appkey));
    }

    @ApiOperation(value = "添加管理员", notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = SwaggerParameter.Authorization, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = SwaggerParameter.AppName, dataType = "String"),
            @ApiImplicitParam(paramType = "query",name = "appname", value = "应用的名字", required = true,dataType = "String"),
            @ApiImplicitParam(paramType = "query",name = "ybid",value = "易班id",required = true,dataType = "int")})
    @PostMapping("/addadmin")
    public ResponseEntity<?> addAdmin(String appname, int ybid) {
        return ResponseEntity.ok(manageService.addAdmin(appname, ybid));
    }

}
