package cn.edu.upc.yb.foodshare.controller;

import cn.edu.upc.yb.common.security.controller.AuthenticationRestController;
import cn.edu.upc.yb.common.security.service.JwtTokenUtil;
import org.junit.Test;
import org.springframework.mobile.device.Device;
import org.springframework.mobile.device.DevicePlatform;

import static org.junit.Assert.*;

/**
 * Created By Kazusa in 2018/7/9 9:10
 */
public class FoodUserControllerTest {
    JwtTokenUtil jwtTokenUtil = new JwtTokenUtil();

    AuthenticationRestController authenticationRestController =new AuthenticationRestController();

    FoodUserController foodUserController = new FoodUserController();

    String vq = "5719ecdccd8e7f15d53a861ee4085fc41d8986b8f9892a87159f65b195f86a5203660062e83633f05c9d7ded30a232d2767e401a0fd8e95ad9b9262e08ca7d4083cee06e9c44935573363c54fa62406731b53948547acf575ada597f5723dc2cfe746bf0036fb365295a228a6820a229e5be614ccb6b23c2a42476b41d73b90babc57ff00ea7cbbe73479cb8a42d78f8250870dc3a91170e766125cb984631c40bd3097430ff31e69d5fad8c9871c0d7ef15dbaa51943827c3643c549addfa4ecd333cdd531dbb0abde473e7c0d319819b7273cff60d8c950282dd0e800bb7452319768bc93071c05440211bed732ed58e861c035db3fecac3e70575a2983f52";

    public String getToken(){
        Device device = new Device() {
            @Override
            public boolean isNormal() {
                return false;
            }

            @Override
            public boolean isMobile() {
                return true;
            }

            @Override
            public boolean isTablet() {
                return false;
            }

            @Override
            public DevicePlatform getDevicePlatform() {
                return null;
            }
        };
        return authenticationRestController.genToken(device);
    }

    @Test
    public void isExist() {
//        String token = getToken();
//        foodUserController.isExist(token);
    }

    @Test
    public void signUp() {
    }

    @Test
    public void getInfo() {
    }

    @Test
    public void isnew() {
    }

    @Test
    public void deleteFood() {
    }

    @Test
    public void getLikeFood() {
    }

    @Test
    public void getCollectionFood() {
    }

    @Test
    public void getReviewFood() {
    }

    @Test
    public void getOwnFood() {
    }
}