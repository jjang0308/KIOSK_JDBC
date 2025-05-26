package domain;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemVO {
    private Long orderItemId;
    private int priceAtOrder;
    private String foodName;
    private int subTotal;
    private int quantity;
    private Long orderId;
    private Long foodId;
}
