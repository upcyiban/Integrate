package cn.edu.upc.yb.lottery.utils;

/**
 * @author liliangbin dumpling1520@gmail.com
 * @date 2018/2/22  11:18
 */

public  class ResponseBean {

    public int code;
    public String msg;
    public Object object;

    public ResponseBean(int code, String msg, Object object) {
        this.code = code;
        this.msg = msg;
        this.object = object;
    }

    public ResponseBean() {
    }

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

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }
}
