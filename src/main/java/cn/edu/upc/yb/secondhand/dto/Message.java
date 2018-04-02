package cn.edu.upc.yb.secondhand.dto;

public class Message {
    private int code;
    private String detail;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Message() {
    }

    public Message(int code, String detail) {
        this.code = code;
        this.detail = detail;
    }
}
