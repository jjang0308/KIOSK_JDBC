package dao.orderitem;

import domain.OrderItem;
import java.util.List;
import java.util.Optional;

public interface OrderItemDao {
    void insertOrderItem(OrderItem orderItem);

    Optional<OrderItem> getOrderItem(Long id);

    List<OrderItem> getAllOrderItems();

    void updateOrderItem(OrderItem orderItem);

    void deleteOrderItem(Long id);
}

