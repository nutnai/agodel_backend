package agodel.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import java.util.Map;

public class ResponseEntityException extends Exception{

    private ResponseEntity<Map<String, Object>> responseEntity;

    public ResponseEntityException(String message, HttpStatus httpStatus) {
        super(message);
        Map<String, Object> res = Map.of("message", message);
        this.responseEntity = ResponseEntity.status(httpStatus).body(res);
    }

    public ResponseEntity<Map<String, Object>> getResponseEntity() {
        return responseEntity;
    }
}
