package fzu.edu.entiy;

/**
 * 反馈结果类，用于服务器端与安卓端的消息通信
 */
public class Result<T> {
    private int code;           //状态码
    private String message;     //状态消息
    private T data;             //数据

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
