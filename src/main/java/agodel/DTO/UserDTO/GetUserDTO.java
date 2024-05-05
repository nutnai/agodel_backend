package agodel.DTO.UserDTO;

import jakarta.validation.constraints.*;

import java.util.Map;

import agodel.DTO.DTO;
import agodel.exception.ResponseEntityException;
import agodel.util.ValidateType;

import lombok.*;

@Setter
@Getter
public class GetUserDTO extends DTO {

    @NotNull (message = "userId is required")
    @Pattern(regexp = "[0-9]+", message = "userId must be number")
    @Size(min = 1, max = 10, message = "userId must be between 1 and 10 characters")
    private String userId;

    public GetUserDTO(Map<String, Object> body) throws ResponseEntityException{
        super(body);
        if (body == null) {
            return;
        }

        this.userId = ValidateType.validateString(body, "userId");

        validate(this);
    }
}