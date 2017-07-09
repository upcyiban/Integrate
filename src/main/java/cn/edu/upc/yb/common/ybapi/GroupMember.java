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
public class GroupMember {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private QueryService queryService;

    /**
     * 易班https://openapi.yiban.cn/user/me接口封装
     *
     * @param token upcyiban token
     * @return
     */

    public Object getGroupMember(String token,String groupId) throws IOException {


        String ybtoken = jwtTokenUtil.getYbaccessToken(token);
        String queryString = "access_token=" + ybtoken+ "&group_id=" + groupId;
        String result = queryService.getYbApi("group/group_member", queryString);
        Gson gson = new Gson();
        System.out.println(result);
        try {
            GroupMember. GroupMemberInfo  groupMemberInfo = gson.fromJson(result,GroupMember. GroupMemberInfo.class);
            return groupMemberInfo;
        } catch (Exception e) {
            return new ErrorReporter(1, "请求失败");
        }
    }

    class GroupMemberInfo{

        String status;
        public class info {


            String member_uid;
            String member_nick;
            String member_head;
            String member_remark;
        }
    }


/*
成功返回：

{
  "status":"success",
  "info":{
    "list":[
      {
        "member_uid":"群成员用户ID",
        "member_nick":"群成员用户昵称",
        "member_head":"群成员用户头像",
        "member_remark":"当前用户附加的好友备注"
      },
      ......
    ]
  }
  }
}

 */
}

