package moon.moonshop.domain.order;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
public class Order {

    @NotNull
    private Long orderId;

    @NotEmpty
    private String buyer;

    @NotEmpty
    private String itemName;

    @NotEmpty
    private Integer orderStock;

    @NotEmpty
    private Integer totalPrice;

    @NotEmpty
    private String timestamp;

    @NotEmpty
    private String status;

    public void setTimestamp() {
        LocalDateTime dateTime = LocalDateTime.now();
        this.timestamp = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(dateTime);
    }
}
