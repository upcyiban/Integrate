package cn.edu.upc.yb.app.leinuo.weekcp.exception;



/**
 * @author leinuoleileinuo
 */
public class WeekCpUserException extends Exception {
    private String message;
    public WeekCpUserException() {
    }

    
    public WeekCpUserException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
