package uz.softex.payload;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Bekjon Bakhromov
 * @since 29.10.2022
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class ApiResult<T> {

    private boolean success;
    private String message;
    private T data;
    private List<ErrorData> errors;

    public ApiResult(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public ApiResult(T data) {
        this.success = true;
        this.data = data;
    }

    public ApiResult(boolean success ,T data, String message) {
        this.success = true;
        this.data = data;
        this.message = message;
    }

    public ApiResult(boolean success, T data) {
        this.success = success;
        this.data = data;
    }

    public ApiResult(boolean success, List<ErrorData> errors) {
        this.success = success;
        this.errors = errors;
    }

    public  ApiResult(T data, boolean isRegister) {
        this.data=data;
        this.success=isRegister;
    }

    public static <E> ApiResult<E> successResponse(E data) {
        return new ApiResult<>(true, data);
    }

    public static <E> ApiResult<E> successResponse(E data, String message) {
        return new ApiResult<>(true, data,message);
    }
 public static <E> ApiResult<E> successResponse(E data, boolean isRegister) {
        return new ApiResult<>(data,isRegister);
    }

    public static <E> ApiResult<E> successResponse(String message) {
        return new ApiResult<>(true, message);
    }


}
