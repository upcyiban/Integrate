package cn.edu.upc.yb.lottery.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author liliangbin dumpling1520@gmail.com
 * @date 2018/2/22  10:25
 */
@Component
public class AdminConfig {
    @Value("${lottery.adminUserName}")
    public String adminUsername;

    @Value("${lottery.adminPassword}")
    public String adminPassword;

    @Value("${lottery.adminSalt}")
    public String adminSalt;




}
