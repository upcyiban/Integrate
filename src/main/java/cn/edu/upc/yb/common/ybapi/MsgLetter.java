package cn.edu.upc.yb.common.ybapi;

import cn.edu.upc.yb.common.security.service.JwtTokenUtil;
import cn.edu.upc.yb.common.service.QueryService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MsgLetter {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private QueryService queryService;


    public Object setMsgLetter(String token,String toYbUid,String content){
        String yibantoken = jwtTokenUtil.getYbaccessToken(token);
        String userid = jwtTokenUtil.getYBidFromTocken(token);
        String queryString = "access_token=" + yibantoken + "&to_yb_uid=" + toYbUid + "&content=" +content;
        String result="";
        try {
            result = queryService.postYbApi("msg/letter",queryString);
        }catch (Exception e){
            System.out.println(e.toString());
        }
        Gson gson = new Gson();
        try{
            SuccessInfo successInfo = gson.fromJson(result,SuccessInfo.class);
            System.out.println(successInfo.status);
            if (successInfo.status.equals("success")){
            }
            return true;
        }catch (Exception e){
            FailureInfo failureInfo = gson.fromJson(result,FailureInfo.class);
            System.out.println(userid + "调用msg/letter失败"+failureInfo.toString());
            return false;
        }

    }
     class SuccessInfo {
        public String status;
        public String info;

    }
    class FailureInfo{
        public String status;
        public Info info;
        public class Info{
            public String code;
            public String msgCN;
            public String msgEN;

            @Override
            public String toString() {
                return "Info{" +
                        "code='" + code + '\'' +
                        ", msgCN='" + msgCN + '\'' +
                        ", msgEN='" + msgEN + '\'' +
                        '}';
            }
        }

        @Override
        public String toString() {
            return "FailureInfo{" +
                    "status='" + status + '\'' +
                    '}' + info.toString();
        }
    }

}
