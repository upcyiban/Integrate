package cn.edu.upc.yb.app.leinuo.weekcp.exception;

public class WeekCpMatchException extends Exception {
    private String message;

    public WeekCpMatchException() {
    }

    public WeekCpMatchException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
