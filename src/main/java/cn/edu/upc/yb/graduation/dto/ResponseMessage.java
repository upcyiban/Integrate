package cn.edu.upc.yb.graduation.dto;

/**
 * @author liliangbin dumpling1520@gmail.com
 * @date 2018/5/25  23:06
 */
public class ResponseMessage {

    public int code;
    public String msg;
    public Object obj;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }
}
