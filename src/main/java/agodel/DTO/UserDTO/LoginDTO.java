package agodel.DTO.UserDTO;

import jakarta.validation.constraints.*;

import java.util.Map;

import agodel.DTO.DTO;
import agodel.exception.ResponseEntityException;
import agodel.util.ValidateType;

import lombok.*;

@Setter
@Getter
public class LoginDTO extends DTO {
    @NotNull(message = "username is required")
    @Size(min = 3, max = 20, message = "username must be between 3 and 20 characters")
    private String username;

    @NotNull(message = "password is required")
    @Size(min = 8, max = 32, message = "password must be between 8 and 32 characters")
    private String password;

    public LoginDTO(Map<String, Object> body) throws ResponseEntityException{
        super(body);
        if (body == null) {
            return;
        }

        this.username = ValidateType.validateString(body, "username");
        this.password = ValidateType.validateString(body, "password");

        validate(this);
    }
}