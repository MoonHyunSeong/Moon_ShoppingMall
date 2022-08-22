package moon.moonshop.domain.item;

import lombok.Data;

@Data
public class Item {

    private Long id;

    private String itemName;
    private Integer price;
    private Integer stockQuantity;

}
