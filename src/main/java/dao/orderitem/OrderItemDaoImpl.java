package dao.orderitem;

import domain.OrderItem;

import java.util.List;
import java.util.Optional;

public class OrderItemDaoImpl implements OrderItemDao {
    @Override
    public void insertOrderItem(OrderItem orderItem) {
        String sql = "insert into order_item_tbl(price_at_order, food_name, subtotal, quantity, order_id, food_id) values (?, ?, ?, ?, ?, ?)";
    }

    @Override
    public Optional<OrderItem> getOrderItem(Long id) {
        return Optional.empty();
    }

    @Override
    public List<OrderItem> getAllOrderItems() {
        return List.of();
    }

    @Override
    public void updateOrderItem(OrderItem orderItem) {

    }

    @Override
    public void deleteOrderItem(Long id) {

    }
}
