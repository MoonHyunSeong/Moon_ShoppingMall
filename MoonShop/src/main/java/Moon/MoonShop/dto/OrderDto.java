package moon.moonshop.dto;

import lombok.Data;

@Data
public class OrderDto {

    private Integer orderStock;
    private Integer totalPrice;
    private String timeStamp;
    private String status;
}
