package agodel.DTO.PlaceDTO;

import jakarta.validation.constraints.*;

import java.util.Date;
import java.util.Map;

import org.hibernate.validator.constraints.Range;

import agodel.DTO.DTO;
import agodel.exception.ResponseEntityException;
import agodel.util.ValidateType;

import lombok.*;

@Setter
@Getter
public class ReserveDTO extends DTO {

    @NotNull (message = "roomId is required")
    @Pattern(regexp = "[0-9]+", message = "roomId must be number")
    @Size(min = 1, max = 10, message = "roomId must be between 1 and 10 characters")
    private String roomId;

    @NotNull (message = "dayCount is required")
    @Range(min = 0, max = 1000, message = "dayCount must be between 0 and 1000")
    private Integer dayCount;

    @NotNull (message = "dateCheckIn is required")
    @FutureOrPresent(message = "dateCheckIn must be in the present or future")
    private Date dateCheckIn;

    @NotNull (message = "dateCheckOut is required")
    @FutureOrPresent(message = "dateCheckOut must be in the present or future")
    private Date dateCheckOut;

    public ReserveDTO(Map<String, Object> body) throws ResponseEntityException{
        super(body);
        if (body == null) {
            return;
        }

        this.roomId = ValidateType.validateString(body, "roomId");
        this.dayCount = ValidateType.validateInteger(body, "dayCount");
        this.dateCheckIn = ValidateType.validateDate(body, "dateCheckIn");
        this.dateCheckOut = ValidateType.validateDate(body, "dateCheckOut");

        validate(this);
    }
}