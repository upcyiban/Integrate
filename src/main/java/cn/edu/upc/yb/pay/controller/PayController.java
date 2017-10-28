package cn.edu.upc.yb.pay.controller;

import cn.edu.upc.yb.common.dto.SwaggerParameter;
import cn.edu.upc.yb.common.ybapi.PayYBwx;
import cn.edu.upc.yb.pay.service.PayService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RestController
@RequestMapping("/pay")
public class PayController {

    @Autowired
    PayService payService;

    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = SwaggerParameter.Authorization,value = "token",dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "message",  dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "appname", dataType = "String"),

    })
    @PostMapping("/")
    public ResponseEntity<?> usePay(String token, int pay ) throws IOException {

        return ResponseEntity.ok(payService.doPay(token,pay));
    }

}
