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

    public static String string2Unicode(String string) {
        StringBuffer unicode = new StringBuffer();
        for (int i = 0; i < string.length(); i++) {
            // 取出每一个字符
            char c = string.charAt(i);
            // 转换为unicode
            unicode.append("\\u" + Integer.toHexString(c));
        }

        return unicode.toString();
    }

    public Object setMsgLetter(String token,String toYbUid,String content){
        string2Unicode(content);
        System.out.println("发送站内信："+content);
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
