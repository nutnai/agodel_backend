package agodel.DTO.ReceiptDTO;

import jakarta.validation.constraints.*;

import java.util.Map;

import agodel.DTO.DTO;
import agodel.exception.ResponseEntityException;
import agodel.util.ValidateType;

import lombok.*;

@Setter
@Getter
public class GetReceiptDTO extends DTO {

    @NotNull(message = "receiptId is required")
    @Pattern(regexp = "[0-9]+", message = "customerId must be number")
    @Size(min = 1, max = 10, message = "customerId must be between 1 and 10 characters")
    private String receiptId;

    public GetReceiptDTO(Map<String, Object> body) throws ResponseEntityException {
        super(body);
        if (body == null) {
            return;
        }

        this.receiptId = ValidateType.validateString(body, "receiptId");

        validate(this);
    }
}