package dao.order;

import domain.OrderVO;

import java.util.Optional;

public interface OrderDao {
    void create(OrderVO order);

    Optional<OrderVO> getOrder(Long no);
}
