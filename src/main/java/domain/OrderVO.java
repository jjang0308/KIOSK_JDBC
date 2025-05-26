package domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderVO {
    private Long orderId;
    private LocalDateTime orderTime;
    private int totalPrice;
    private String orderType;
    private Long paymentId;
    private String phoneNum;

    private List<OrderItemVO> orderItemList;
}
