package agodel.DTO.UserDTO;

import jakarta.validation.constraints.*;

import java.util.Map;

import agodel.DTO.DTO;
import agodel.exception.ResponseEntityException;
import agodel.util.ValidateType;

import lombok.*;

@Setter
@Getter
public class RegisterDTO extends DTO{
    @NotNull (message = "username is required")
    @Size(min = 3, max = 20, message = "username must be between 3 and 20 characters")
    private String username;

    @NotNull (message = "password is required")
    @Size(min = 8, max = 32, message = "password must be between 8 and 32 characters")
    private String password;

    @NotNull (message = "type is required")
    @Pattern(regexp = "customer|owner", message = "type must be customer or owner")
    private String type;

    @NotNull (message = "firstname is required")
    @Size(min = 1, max = 32, message = "firstname must be between 1 and 32 characters")
    private String firstname;

    @NotNull (message = "lastname is required")
    @Size(min = 1, max = 32, message = "lastname must be between 1 and 32 characters")
    private String lastname;

    @NotNull (message = "phone is required")
    @Size(min = 10, max = 10, message = "phone must be 10 characters")
    @Pattern(regexp = "[0-9]+", message = "phone must be number")
    private String phone;

    @NotNull (message = "email is required")
    @Email (message = "email must be valid")
    private String email;

    public RegisterDTO(Map<String, Object> body) throws ResponseEntityException{
        super(body);
        if(body == null){
            return;
        }
        this.username = ValidateType.validateString(body, "username");
        this.password = ValidateType.validateString(body, "password");
        this.type = ValidateType.validateString(body, "type");
        this.firstname = ValidateType.validateString(body, "firstname");
        this.lastname = ValidateType.validateString(body, "lastname");
        this.phone = ValidateType.validateString(body, "phone");
        this.email = ValidateType.validateString(body, "email");
        validate(this);
    }
}
