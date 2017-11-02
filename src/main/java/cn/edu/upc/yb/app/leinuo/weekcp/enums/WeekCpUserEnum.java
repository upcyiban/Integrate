package cn.edu.upc.yb.app.leinuo.weekcp.enums;

/**
 * @author leinuoleileinuo
 */

public enum WeekCpUserEnum {
    NOT_FOUND_USER_ID(1000,"userId不存在"),
    USER_IS_DELETED(10001,"用户已经被删除"),
    USER_IS_NOT_DELETED(10002,"用户并未被删除"),
    NOT_FOUND_YIBAN_ID(10003,"yibanId不存在")
    ;


    private String message;
    private Integer index;

    public static String getMessage(Integer index) {
        for (WeekCpUserEnum userEnum : WeekCpUserEnum.values()) {
            if (userEnum.getIndex().equals(index)) {
                return userEnum.getMessage();
            }
        }
        return "找不到枚举类";
    }

    WeekCpUserEnum(Integer index, String message) {
        this.message = message;
        this.index = index;
    }

    public String getMessage() {
        return message;
    }

    public String contactMessage(String message) {
        return message+" "+this.message;
    }
    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }
}
