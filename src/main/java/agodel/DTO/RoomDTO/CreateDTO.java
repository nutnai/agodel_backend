package agodel.DTO.RoomDTO;

import jakarta.validation.constraints.*;

import java.util.Map;

import org.hibernate.validator.constraints.Range;

import agodel.DTO.DTO;
import agodel.exception.ResponseEntityException;
import agodel.util.ValidateType;

import lombok.*;

@Setter
@Getter
public class CreateDTO extends DTO {

    @NotNull(message = "ownerId is required")
    @Pattern(regexp = "[0-9]+", message = "ownerId must be number")
    @Size(min = 1, max = 10, message = "ownerId must be between 1 and 10 characters")
    private String ownerId;

    @NotEmpty(message = "roomType is required")
    @Size(min = 1, max = 512, message = "roomType must be between 1 and 512 characters")
    private String roomType;

    //! must change in the future
    @NotEmpty(message = "facility is required")
    @Size(min = 1, max = 512, message = "facility must be between 1 and 512 characters")
    private String facility;

    @NotEmpty(message = "numberPeople is required")
    @Range(min = 1, max = 1000, message = "numberPeople must be between 1 and 1000")
    private Integer numberPeople;

    @NotEmpty(message = "price is required")
    @Range(min = 0, max = 1000000000, message = "price must be between 0 and 1000000000")
    private Double price;

    @NotEmpty(message = "status is required")
    @Pattern(regexp = "AVAILABLE|UNAVAILABLE", message = "status must be AVAILABLE or UNAVAILABLE")
    private String status;

    public CreateDTO(Map<String, Object> body) throws ResponseEntityException {
        super(body);
        if (body == null) {
            return;
        }

        this.ownerId = ValidateType.validateString(body, "ownerId");
        this.roomType = ValidateType.validateString(body, "roomType");
        this.facility = ValidateType.validateString(body, "facility");
        this.numberPeople = ValidateType.validateInteger(body, "numberPeople");
        this.price = ValidateType.validateDouble(body, "price");
        this.status = ValidateType.validateString(body, "status");

        validate(this);
    }
}