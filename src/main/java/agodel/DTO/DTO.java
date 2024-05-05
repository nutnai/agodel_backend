package agodel.DTO;

import jakarta.validation.*;

import java.util.Set;
import java.util.Map;

import org.springframework.http.HttpStatus;

import agodel.exception.ResponseEntityException;

import lombok.Getter;

@Getter
public class DTO {

    ValidatorFactory factory;
    Validator validator;

    private Map<String, Object> body;

    public DTO(Map<String, Object> body) {
        this.factory = Validation.buildDefaultValidatorFactory();
        this.validator = factory.getValidator();
        this.body = body;
    }

    public void validate(Object object) throws ResponseEntityException {
        Set<ConstraintViolation<Object>> constraintViolations = validator.validate(object);
        if (constraintViolations.size() > 0) {
            StringBuilder message = new StringBuilder();
            for (ConstraintViolation<Object> violation : constraintViolations) {
            message.append(violation.getMessage()).append(", ");
            }
            message.setLength(message.length() - 2); // Remove the last ", "
            throw new ResponseEntityException(message.toString(), HttpStatus.BAD_REQUEST);
        }
    }
}

//getter and setter
//extends DTO
//private attribute
//constructor
// public DTO(Map<String, Object> body) throws ResponseEntityException{
//     super();
//     if(body == null){
//         return;
//     }

//     //validate and set attributes

//     validate(this);
// }
