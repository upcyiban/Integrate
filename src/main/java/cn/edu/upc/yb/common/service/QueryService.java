package cn.edu.upc.yb.common.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by lylllcc on 2017/6/22.
 */
@Service
public class QueryService {


    @Autowired



    /**
     *
     * @param apiName 请求的api地址
     * @param query    除了tocken以外的参数构成的字符串
     * @return
     * @throws IOException
     */
    public String getYbApi(String apiName,String query) throws IOException {
        String url = "https://openapi.yiban.cn/" + apiName;
        String charset = "UTF-8";

        URLConnection connection = new URL(url + "?" + query).openConnection();
        connection.setRequestProperty("Accept-Charset", charset);
        InputStream response = connection.getInputStream();
        StringBuilder sb=new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(response));
        String read;

        while((read=br.readLine()) != null) {
            sb.append(read);
        }
        br.close();
        return sb.toString();
    }
}
