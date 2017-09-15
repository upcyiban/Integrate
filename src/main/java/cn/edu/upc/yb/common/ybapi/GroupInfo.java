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
public class GroupInfo {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private QueryService queryService;

    /**
     * 易班https://openapi.yiban.cn/group/group_info接口封装
     *
     * @param token upcyiban token
     * @return
     */

    public Object getGroupInfo(String token ,String groupId) throws IOException {


        String ybtoken = jwtTokenUtil.getYbaccessToken(token);

        String queryString = "access_token=" + ybtoken +  "&group_id=" + groupId;
        String result = queryService.getYbApi("group/group_info", queryString);
        Gson gson = new Gson();
        System.out.println(result);
        try {
            GroupInfoInfo groupInfoInfo = gson.fromJson(result, GroupInfoInfo.class);
            return groupInfoInfo;
        } catch (Exception e) {
            return new ErrorReporter(1, "请求失败");
        }

    }


    class GroupInfoInfo {

        String status;

        public class info {


            String group_id;
            String group_name;
            String group_icon;
            String group_type;
            String adm_uid;
            String adm_nick;
            String group_mamber;
            String group_notice;
            String topic_id;
            String topic_title;
            String topic_content;
            String create_time;
        }
    }
}

