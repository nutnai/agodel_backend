package agodel.DTO.PlaceDTO;

import java.util.Map;

import agodel.DTO.DTO;
import agodel.exception.ResponseEntityException;
import agodel.util.ValidateType;

import jakarta.validation.constraints.*;

import lombok.*;

@Getter
@Setter
public class CreateDTO extends DTO{
    @NotNull (message = "name is required")
    @Size(min = 1, max = 128, message = "name must be between 1 and 128 characters")
    private String name;

    @NotNull (message = "address is required")
    @Size(min = 1, max = 128, message = "address must be between 1 and 128 characters")
    private String address;

    @NotNull (message = "status is required")
    @Pattern(regexp = "AVAILABLE|UNAVAILABLE", message = "status must be AVAILABLE or UNAVAILABLE")
    private String status;

    public CreateDTO(Map<String, Object> body) throws ResponseEntityException {
        super(body);
        if(body == null){
            return;
        }
        this.name = ValidateType.validateString(body, "name");
        this.address = ValidateType.validateString(body, "address");
        this.status = ValidateType.validateString(body, "status");
        validate(this);
    }
}
