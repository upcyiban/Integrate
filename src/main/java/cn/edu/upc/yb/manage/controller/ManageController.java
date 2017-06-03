package cn.edu.upc.yb.manage.controller;

import cn.edu.upc.yb.common.security.model.App;
import cn.edu.upc.yb.common.security.model.AppRepository;
import cn.edu.upc.yb.manage.service.ManageService;
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
public class ManageController {

    @Autowired
    private AppRepository appRepository;

    @Autowired
    private ManageService manageService;

    @PostMapping("/create")
    @PreAuthorize("hasRole('MANAGE_ADMIN')")
    public ResponseEntity<?> createApp(String appname,String appid,String appkey){
        return ResponseEntity.ok(manageService.createApp(appname,appid,appkey));
    }

}
