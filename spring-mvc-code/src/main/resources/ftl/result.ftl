package ${package};

import lombok.Data;
import java.io.Serializable;

/**
* Result
*
* @author wanqiuli
* @date ${.now?datetime}
*/
@Data
@SuppressWarnings({"unchecked", "rawtypes"})
public class Result<T> implements Serializable {
    private boolean result = true;
    private int code;
    private String message;
    private T data;


    public static Result<Void> success() {
        return new Result();
    }

    public static <R> Result<R> success(R data) {
        return new Result(1, "success", data);
    }

    public static <R> Result<R> success(String message) {
        return new Result(1, message);
    }

    public static <R> Result<R> success(R data, String message) {
        return new Result(1, message, data);
    }

    public static Result<?> failure() {
        Result<Object> responseData = new Result(0, "failure");
        responseData.setResult(false);
        return responseData;
    }

    public static Result<?> failure(String message) {
        Result<Object> responseData = new Result(0, message);
        responseData.setResult(false);
        return responseData;
    }

    public static Result<?> failure(int errorCode, String message) {
        Result<Object> responseData = new Result(errorCode, message);
        responseData.setResult(false);
        return responseData;
    }

    public static <R> Result<R> failure(R data, String message) {
        Result<R> responseData = new Result(0, message, data);
        responseData.setResult(false);
        return responseData;
    }


    public static <R> Result<R> failure(int errorCode, R data, String message) {
        Result<R> responseData = new Result(errorCode, message, data);
        responseData.setResult(false);
        return responseData;
    }


    public Result() {
        this.code = 1;
        this.message = "success";
    }

    public Result(T data) {
        this.code = 1;
        this.message = "success";
        this.data = data;
    }

    public Result(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public Result(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }
}