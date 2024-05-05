package agodel.DTO.UserDTO;

import jakarta.validation.constraints.*;

import java.util.Map;

import agodel.DTO.DTO;
import agodel.exception.ResponseEntityException;
import agodel.util.ValidateType;

import lombok.*;

@Setter
@Getter
public class GetOwnerDTO extends DTO {

    @NotNull (message = "ownerId is required")
    @Pattern(regexp = "[0-9]+", message = "ownerId must be number")
    @Size(min = 1, max = 10, message = "ownerId must be between 1 and 10 characters")
    private String ownerId;

    public GetOwnerDTO(Map<String, Object> body) throws ResponseEntityException{
        super(body);
        if (body == null) {
            return;
        }

        this.ownerId = ValidateType.validateString(body, "ownerId");

        validate(this);
    }
}