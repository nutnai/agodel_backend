package agodel.DTO.PlaceDTO;

import jakarta.validation.constraints.*;

import java.util.Map;

import agodel.DTO.DTO;
import agodel.exception.ResponseEntityException;
import agodel.util.ValidateType;

import lombok.*;

@Setter
@Getter
public class EditDTO extends DTO {

    @NotNull(message = "ownerId is required")
    @Pattern(regexp = "[0-9]+", message = "ownerId must be number")
    @Size(min = 1, max = 10, message = "ownerId must be between 1 and 10 characters")
    private String ownerId;

    @Size(min = 1, max = 128, message = "newName must be between 1 and 128 characters")
    private String newName;

    @Size(min = 1, max = 128, message = "newAddress must be between 1 and 128 characters")
    private String newAddress;

    @Pattern(regexp = "AVAILABLE|UNAVAILABLE", message = "newStatus must be AVAILABLE or UNAVAILABLE")
    private String newStatus;

    public EditDTO(Map<String, Object> body) throws ResponseEntityException {
        super(body);
        if (body == null) {
            return;
        }

        this.ownerId = ValidateType.validateString(body, "ownerId");
        this.newName = ValidateType.validateString(body, "newName");
        this.newAddress = ValidateType.validateString(body, "newAddress");
        this.newStatus = ValidateType.validateString(body, "newStatus");

        validate(this);
    }
}