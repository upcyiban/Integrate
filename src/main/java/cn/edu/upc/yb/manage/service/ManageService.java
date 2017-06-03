package cn.edu.upc.yb.manage.service;

import cn.edu.upc.yb.common.dto.ErrorReporter;
import cn.edu.upc.yb.common.security.model.App;
import cn.edu.upc.yb.common.security.model.AppRepository;
import cn.edu.upc.yb.common.security.model.Authority;
import cn.edu.upc.yb.common.security.model.AuthorityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by lylll on 2017/6/3.
 */
@Service
public class ManageService {

    @Autowired
    private AppRepository appRepository;

    @Autowired
    private AuthorityRepository authorityRepository;

    public Object createApp(String appname,String appid,String appkey){

        App app = appRepository.findFirstByAppname(appname);
        if(app != null){
            return new ErrorReporter(1,"应用名已存在");
        }
        app = new App(appname,appid,appkey);
        Authority authority = new Authority();
        authority.setName("ROLE_" + appname.toUpperCase() + "_ADMIN");
        authorityRepository.save(authority);
        return appRepository.save(app);
    }
}
