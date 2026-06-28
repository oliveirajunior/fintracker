package manoel.fintracker.infra;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> illegalArgumentErrorHandler(IllegalArgumentException exception){
        HttpStatus status = HttpStatus.PRECONDITION_REQUIRED;
        String message = exception.getMessage();
        ErrorClass errorClass = new ErrorClass(status, message);
        return ResponseEntity.status(status).body(errorClass);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> runtimeErrorHandler(RuntimeException exception){
        HttpStatus status = HttpStatus.NOT_FOUND;
        String message = exception.getMessage();
        return ResponseEntity.status(status).body(message);
    }

}
