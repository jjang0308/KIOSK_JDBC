package domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderVO {
    private Integer orderId;
    private LocalDateTime orderTime;
    private Integer totalPrice;
    private String orderType;
    private Long paymentId;
    private String phoneNum;
}
