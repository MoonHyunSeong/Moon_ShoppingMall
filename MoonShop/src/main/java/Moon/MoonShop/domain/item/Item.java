package moon.moonshop.domain.item;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class Item {

    @NotNull
    private Long itemId;

    @NotNull
    private String itemName;

    private String seller;

    @NotEmpty
    private Integer price;

    @NotEmpty
    private Integer quantity;

    @NotEmpty
    private String category;



    public Item() {
    }

}
