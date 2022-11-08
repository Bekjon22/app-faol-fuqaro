package uz.softex.exception;


import lombok.Data;
import org.springframework.http.HttpStatus;
import uz.softex.common.MessageService;


@Data
public class RestException extends RuntimeException {

    private String message;

    private HttpStatus status;

    private String type;


    public RestException(String message, HttpStatus status) {
        this.message = message;
        this.status = status;
    }

    public RestException(String message, HttpStatus status,String type) {
        this.message = message;
        this.status = status;
        this.type = type;
    }

    public static RestException restThrow(String message, HttpStatus status) {
        return new RestException(message,status);
    }

    public static RestException notFound(String message) {
        return new RestException(message,HttpStatus.NOT_FOUND);
    }


    public static RestException forbidden() {
        return new RestException(MessageService.getMessage("FORBIDDEN"), HttpStatus.FORBIDDEN);
    }

    public static RestException forbidden(String message ,String type) {
        return new RestException(message,HttpStatus.FORBIDDEN,type);
    }

    public static RestException badRequest() {
        return new RestException(MessageService.getMessage("BAD_REQUEST"), HttpStatus.BAD_REQUEST);
    }

    public static RestException serverError() {
        return new RestException(MessageService.getMessage("INTERNAL_SERVER_ERROR"),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
