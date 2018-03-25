package cn.edu.upc.yb.ybapi;

import cn.edu.upc.yb.common.ybapi.PayYBwx;
import cn.edu.upc.yb.common.ybapi.TradeWx;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PayTest {


    String token = "eyJhbGciOiJIUzUxMiJ9.eyJhdWRpZW5jZSI6IndlYiIsInlidG9rZW4iOiI4ZjU2MDBmZmI0NWNhNzRiZDNjN2VkNmUzYzZhMjkxMTg1ZWFiODNmIiwiYXBwbmFtZSI6Iuefs-Wkp-ivt-mUgOWBhyIsImNyZWF0ZWQiOjE1MTI1NTA3NTk2MjIsInliaWQiOjUzOTQ0MzIsImV4cCI6MTUxMzE1NTU1OX0.uwt3qlH_JzyYTVEaWKRfC6jNr6Ado3PuRnMK2PqndbxrBKuyDnYY65j07018c7jyhQXobloFlKDERb0Eqy5Bgw";
    @Autowired
    PayYBwx payYBwx;
    @Autowired
    TradeWx tradeWx;

    @Test
    public void usePay() throws IOException {

        payYBwx.getYBwx(token, 10);

    }

    @Test
    public void useTrade() throws IOException {
        tradeWx.tradeWx(token, 100, "http://www.baidu.com", 8574001);
    }
}
