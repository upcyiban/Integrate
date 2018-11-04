package cn.edu.upc.yb.common.controller;


import cn.edu.upc.yb.common.dto.ErrorReporter;
import cn.edu.upc.yb.common.dto.SwaggerParameter;
import cn.edu.upc.yb.common.dto.YibanBasicUserInfo;
import cn.edu.upc.yb.common.security.auth.YibanOAuth;
import cn.edu.upc.yb.common.security.model.App;
import cn.edu.upc.yb.common.security.model.AppRepository;
import cn.edu.upc.yb.common.service.UserService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RestController
@RequestMapping("/common/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private AppRepository appRepository;

    private final Log logger = LogFactory.getLog(this.getClass());

    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = SwaggerParameter.Authorization, dataType = "String"),
    })

    @GetMapping("/getStuId")
    public Object getId(HttpServletRequest request) throws IOException {
        return userService.getStuId(request);
    }

    @PostMapping("/getStuIdByVq")
    public Object getStuId(String appName, String vq) {
        logger.info("应用:" + appName + " 获取权限");

        App app = appRepository.findFirstByAppname(appName);
        if (app == null) {
            return ResponseEntity.ok(new ErrorReporter(1, "未找到应用" + appName));
        }

        YibanOAuth yibanOAuth = new YibanOAuth(vq, app);
        yibanOAuth.dealYibanOauth();
        /*解析vq得到用户得授权信息*/
        if (yibanOAuth.isHasError() == false) {
            YibanBasicUserInfo yibanBasicUserInfo = yibanOAuth.getYibanBasicUserInfo();
            System.out.println(yibanBasicUserInfo.visit_oauth);

            try {
                /*用户得基本授权信息得到后，去访问接口拿到基本得用户信息*/
                Object studentInfo = userService.getStuInfoByAccessToken(yibanBasicUserInfo.visit_oauth.access_token);

                return studentInfo;
            } catch (IOException e) {
                e.printStackTrace();
                return ResponseEntity.ok(new ErrorReporter(-1, e.getMessage()));
            }

        } else {
            return ResponseEntity.ok(new ErrorReporter(2, "解析vq失败，可能是由于密钥长度不匹配"));
        }
    }
}
