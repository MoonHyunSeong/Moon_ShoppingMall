package moon.moonshop.domain.item;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class Item {

    @NotNull
    private Long id;

    @NotEmpty
    private String itemName;
    @NotEmpty
    private Integer price;
    @NotEmpty
    private Integer stockQuantity;

    /*
    * 멤버 이름 정도 추가해서 누가 올렸는지 구분짓자.
    * */

    public Item() {
    }

    public Item(String itemName, Integer price, Integer stockQuantity) {
        this.itemName = itemName;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }
}
