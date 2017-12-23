package cn.edu.upc.yb.app.leinuo.weekcp.result;


/**
 * @author leinuoleileinuo
 */
public class Result<T> {
    private T data;
    private String msg;
    private int success;
    private Result(String msg , int success , T data) {
        this.data = data;
        this.success = success;
        this.msg = msg;
    }
    public static<T> Result<T> success(String msg, T data) {
        return new Result(msg , 1 , data);
    }
    public static<T> Result<T> fail(String msg , T data) {
        return new Result(msg , 0 , data);
    }
    public static<T> Result<T> success(String msg) {
        return new Result(msg , 1 , null);
    }
    public static<T> Result<T> fail(String msg ) {
        return new Result(msg , 0 , null);
    }
    public static<T> Result<T> success(T data) {
        return new Result("成功" , 1 , data);
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }
}
