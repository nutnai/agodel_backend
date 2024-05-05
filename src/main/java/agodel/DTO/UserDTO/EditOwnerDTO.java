package agodel.DTO.UserDTO;

import jakarta.validation.constraints.*;

import java.util.Map;

import agodel.DTO.DTO;
import agodel.exception.ResponseEntityException;
import agodel.util.ValidateType;

import lombok.*;

@Setter
@Getter
public class EditOwnerDTO extends DTO {

    @NotNull (message = "ownerId is required")
    @Pattern(regexp = "[0-9]+", message = "ownerId must be number")
    @Size(min = 1, max = 10, message = "ownerId must be between 1 and 10 characters")
    private String ownerId;

    @Size(min = 1, max = 32, message = "newFirstname must be between 1 and 32 characters")
    private String newFirstname;

    @Size(min = 1, max = 32, message = "newLastname must be between 1 and 32 characters")
    private String newLastname;

    @Size(min = 10, max = 10, message = "newPhone must be 10 characters")
    @Pattern(regexp = "[0-9]+", message = "newPhone must be number")
    private String newPhone;

    @Email (message = "newEmail must be valid")
    private String newEmail;

    public EditOwnerDTO(Map<String, Object> body) throws ResponseEntityException{
        super(body);
        if (body == null) {
            return;
        }

        this.ownerId = ValidateType.validateString(body, "ownerId");
        this.newFirstname = ValidateType.validateString(body, "newFirstname");
        this.newLastname = ValidateType.validateString(body, "newLastname");
        this.newPhone = ValidateType.validateString(body, "newPhone");
        this.newEmail = ValidateType.validateString(body, "newEmail");

        validate(this);
    }
}