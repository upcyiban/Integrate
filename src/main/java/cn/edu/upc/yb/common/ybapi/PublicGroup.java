package cn.edu.upc.yb.common.ybapi;

import cn.edu.upc.yb.common.dto.ErrorReporter;
import cn.edu.upc.yb.common.security.service.JwtTokenUtil;
import cn.edu.upc.yb.common.service.QueryService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * Created by fang on 2017/7/8.
 */
@Service
public class PublicGroup {


    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private QueryService queryService;

    /**
     * 易班https://openapi.yiban.cn/group/public_group接口封装
     *
     * @param token upcyiban token
     * @return
     */

    public  Object getPublicGroup(String   token)  throws IOException{



        String ybtoken = jwtTokenUtil.getYbaccessToken(token);
        String queryString = "access_token=" + ybtoken;
        String result = queryService.getYbApi("group/public_group", queryString);
        Gson gson = new Gson();
        System.out.println(result);
        try {
           PublicGroupInfo publicGroupInfo = gson.fromJson(result,PublicGroupInfo.class);
            return publicGroupInfo;
        }catch (Exception e){
            return new ErrorReporter(1,"请求失败");
        }


    }

    class PublicGroupInfo{

        String status;
        public class info {


            String group_id;
            String  group_name;
            String  group_icon;
            String   group_type;
            String   adm_uid;
            String   adm_nick;
            String   group_mamber;
        }
    }

/*
成功返回：

{
  "status":"success",
  "info":{
    "public_group":[
      {
        "group_id":"群ID",
        "group_name":"群名称",
        "group_icon":"群图标",
        "group_type":"群类型",
        "adm_uid":"群创建者用户ID",
        "adm_nick":"群创建者用户昵称",
        "group_mamber":"群成员数"
      },
      ......
    ],
    "num":"群总数"
  }
}
 */

}
