package agodel.DTO.RoomDTO;

import jakarta.validation.constraints.*;

import java.util.Map;

import agodel.DTO.DTO;
import agodel.exception.ResponseEntityException;
import agodel.util.ValidateType;

import lombok.*;

@Setter
@Getter
public class GetRoomDTO extends DTO {

    @NotNull (message = "roomId is required")
    @Pattern(regexp = "[0-9]+", message = "roomId must be number")
    @Size(min = 1, max = 10, message = "roomId must be between 1 and 10 characters")
    private String roomId;

    public GetRoomDTO(Map<String, Object> body) throws ResponseEntityException{
        super(body);
        if (body == null) {
            return;
        }

        this.roomId = ValidateType.validateString(body, "roomId");

        validate(this);
    }
}