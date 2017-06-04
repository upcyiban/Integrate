package cn.edu.upc.yb.manage.service;

import cn.edu.upc.yb.common.dto.ErrorReporter;
import cn.edu.upc.yb.common.security.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lylll on 2017/6/3.
 */
@Service
public class ManageService {

    @Autowired
    private AppRepository appRepository;

    @Autowired
    private AuthorityRepository authorityRepository;

    @Autowired
    private YBUserRepository ybUserRepository;

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

    public Object addAdmin(String appname,int ybid){
        String authorityName = "ROLE_" + appname.toUpperCase() + "_ADMIN";
        Authority authority = authorityRepository.findFirstByName(authorityName);
        if (authority == null){
            return new ErrorReporter(2,"应用不存在");
        }
        YBUser ybUser = ybUserRepository.findFirstByUserid(ybid);
        if(ybUser == null){
            return new ErrorReporter(3,"用户不存在");
        }
        List<Authority> authorities = ybUser.getAuthorities();
        int flag = 0;
        for (Authority a:
             authorities) {
            if(a.getName() == authorityName){
                flag = 1;
                break;
            }
        }
        if(flag == 0){
            ybUser.getAuthorities().add(authority);
            ybUserRepository.save(ybUser);
            return "添加成功";
        }
        return new ErrorReporter(4,"未知错误");

    }
}
