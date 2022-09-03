package moon.moonshop.domain.item;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class ItemUpdateDto {

    @NotEmpty
    private Integer price;
    @NotEmpty
    private Integer quantity;
}
