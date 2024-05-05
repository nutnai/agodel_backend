package agodel.DTO.PlaceDTO;

import jakarta.validation.constraints.*;

import java.util.Map;

import org.hibernate.validator.constraints.Range;

import agodel.DTO.DTO;
import agodel.exception.ResponseEntityException;
import agodel.util.ValidateType;

import lombok.*;

@Setter
@Getter
public class SearchDTO extends DTO {

    @NotEmpty(message = "keyword is required")
    @Size(min = 1, max = 256, message = "keyword must be between 1 and 256 characters")
    private String keyword;

    @Positive(message = "numberPeople must be positive")
    @Range(min = 1, max = 1000, message = "numberPeople must be between 1 and 1000")
    private Integer numberPeople;

    @PositiveOrZero(message = "lowerPrice must be positive or zero")
    @Range(min = 0, max = 1000000000, message = "lowerPrice must be between 0 and 1000000000")
    private Double lowerPrice;

    @PositiveOrZero(message = "upperPrice must be positive or zero")
    @Range(min = 0, max = 1000000000, message = "upperPrice must be between 0 and 1000000000")
    private Double upperPrice;

    public SearchDTO(Map<String, Object> body) throws ResponseEntityException {
        super(body);
        if (body == null) {
            return;
        }

        this.keyword = ValidateType.validateString(body, "keyword");
        this.numberPeople = ValidateType.validateInteger(body, "numberPeople");
        this.lowerPrice = ValidateType.validateDouble(body, "lowerPrice");
        this.upperPrice = ValidateType.validateDouble(body, "upperPrice");

        validate(this);
    }
}