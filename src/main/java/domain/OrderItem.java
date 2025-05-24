package domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem {
    private Long orderItemId;
    private int priceAtOrder;
    private String foodName;
    private int subTotal;
    private int quantity;
    private Long orderId;
    private Long foodId;
}
