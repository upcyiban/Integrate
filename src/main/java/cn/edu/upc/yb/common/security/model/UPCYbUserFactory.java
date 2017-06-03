package cn.edu.upc.yb.common.security.model;

import cn.edu.upc.yb.common.dto.YibanBasicUserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by lylll on 2017/6/3.
 */

@Service
public class UPCYbUserFactory {

    @Autowired
    private YBUserRepository userRepository;


    /**
     * 基础数据保存到我们自己的数据库中，不存在创建，存在则更新
     * @param yibaninfo
     */
    public void createUser(YibanBasicUserInfo yibaninfo) {
        int ybid = yibaninfo.visit_user.userid;
        YBUser user = userRepository.findFirstByUserid(ybid);
        if (user == null) {
            user = new YBUser(
                    yibaninfo.visit_user.userid,
                    yibaninfo.visit_user.username,
                    yibaninfo.visit_user.usernick,
                    yibaninfo.visit_user.usersex);
            userRepository.save(user);
        } else {
            user.setUsername(yibaninfo.visit_user.username);
            user.setUsernick(yibaninfo.visit_user.usernick);
            user.setUsersex(yibaninfo.visit_user.usersex);
            userRepository.save(user);
        }
    }
}
