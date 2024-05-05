package agodel.DTO.UserDTO;

import jakarta.validation.constraints.*;

import java.util.Map;

import agodel.DTO.DTO;
import agodel.exception.ResponseEntityException;
import agodel.util.ValidateType;

import lombok.*;

@Setter
@Getter
public class GetCustomerDTO extends DTO {

    @NotNull (message = "customerId is required")
    @Pattern(regexp = "[0-9]+", message = "customerId must be number")
    @Size(min = 1, max = 10, message = "customerId must be between 1 and 10 characters")
    private String customerId;

    public GetCustomerDTO(Map<String, Object> body) throws ResponseEntityException{
        super(body);
        if (body == null) {
            return;
        }

        this.customerId = ValidateType.validateString(body, "customerId");

        validate(this);
    }
}