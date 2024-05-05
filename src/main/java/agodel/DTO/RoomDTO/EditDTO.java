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
public class EditDTO extends DTO {

    @NotNull(message = "roomId is required")
    @Pattern(regexp = "[0-9]+", message = "roomId must be number")
    @Size(min = 1, max = 10, message = "roomId must be between 1 and 10 characters")
    private String roomId;

    @Size(min = 1, max = 512, message = "roomType must be between 1 and 512 characters")
    private String newRoomType;

    // ! must change in the future
    @Size(min = 1, max = 512, message = "facility must be between 1 and 512 characters")
    private String newFacility;

    @Range(min = 1, max = 1000, message = "numberPeople must be between 1 and 1000")
    private Integer newNumberPeople;

    @Range(min = 0, max = 1000000000, message = "price must be between 0 and 1000000000")
    private Double newPrice;

    @Pattern(regexp = "AVAILABLE|UNAVAILABLE", message = "status must be AVAILABLE or UNAVAILABLE")
    private String newStatus;

    public EditDTO(Map<String, Object> body) throws ResponseEntityException {
        super(body);
        if (body == null) {
            return;
        }

        this.roomId = ValidateType.validateString(body, "roomId");
        this.newRoomType = ValidateType.validateString(body, "newRoomType");
        this.newFacility = ValidateType.validateString(body, "newFacility");
        this.newNumberPeople = ValidateType.validateInteger(body, "newNumberPeople");
        this.newPrice = ValidateType.validateDouble(body, "newPrice");
        this.newStatus = ValidateType.validateString(body, "newStatus");

        validate(this);
    }
}