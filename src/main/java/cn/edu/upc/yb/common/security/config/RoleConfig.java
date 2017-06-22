package cn.edu.upc.yb.common.security.config;

import cn.edu.upc.yb.common.security.model.Authority;
import cn.edu.upc.yb.common.security.model.AuthorityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Created by lylllcc on 2017/6/22.
 */
@Component
public class RoleConfig {

    @Autowired
    private AuthorityRepository authorityRepository;

    @PostConstruct
    public void createRoll(){
        Authority authority = authorityRepository.findFirstByName("ROLE_ORDINARY");
        if (authority == null){
            authorityRepository.save(new Authority("ROLE_ORDINARY"));
        }
    }
}
