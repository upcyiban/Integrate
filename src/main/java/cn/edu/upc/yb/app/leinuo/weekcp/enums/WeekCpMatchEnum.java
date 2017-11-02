package cn.edu.upc.yb.app.leinuo.weekcp.enums;

/**
 * @author leinuoleileinuo
 */

public enum WeekCpMatchEnum {

    USER_HAVE_MATCH(20000,"该用户已经有CP了"),
    TOO_MANY_CP(20001,"一个用户出现了多个CP")
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

    WeekCpMatchEnum(Integer index, String message) {
        this.message = message;
        this.index = index;
    }

    public String getMessage() {
        return message;
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
