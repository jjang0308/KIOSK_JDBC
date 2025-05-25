package dao.orderitem;

import domain.OrderItemVO;
import java.util.List;

public interface OrderItemDao {
    void insertOrderItem(OrderItemVO orderItemVO);

//    Optional<OrderItem> getOrderItem(Long id);

    List<OrderItemVO> getAllOrderItems(Long id);

//    void updateOrderItem(OrderItem orderItem);
//
//    void deleteOrderItem(Long id);
}

