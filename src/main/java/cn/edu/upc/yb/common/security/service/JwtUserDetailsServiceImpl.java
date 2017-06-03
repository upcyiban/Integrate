package cn.edu.upc.yb.common.security.service;

import cn.edu.upc.yb.common.security.model.YBUser;
import cn.edu.upc.yb.common.security.model.YBUserRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


/**
 * Created by stephan on 20.03.16.
 */
@Service
public class JwtUserDetailsServiceImpl implements UserDetailsService {

    private final Log logger = LogFactory.getLog(this.getClass());


    @Autowired
    private YBUserRepository ybUserRepository;

    @Override
    public UserDetails loadUserByUsername(String ybid) throws UsernameNotFoundException {
        int tempybid = Integer.valueOf(ybid);
        YBUser user = ybUserRepository.findFirstByUserid(tempybid);

        if (user == null) {
            throw new UsernameNotFoundException(String.format("No user found with yibanid '%s'.", ybid));
        } else {
            return JwtUserFactory.create(user);
        }
    }
}
