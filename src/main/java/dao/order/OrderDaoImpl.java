package dao.order;

import config.JDBCUtil;
import domain.OrderItemVO;
import domain.OrderVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrderDaoImpl implements OrderDao{

    Connection conn = JDBCUtil.getConnection();

    @Override
    public void create(OrderVO order) {
        String sql = "insert into order_tbl(total_price,order_type,payment_id,phone_num) values (?,?,?,?)";

        try(PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setInt(1,order.getTotalPrice());
            pstmt.setString(2, order.getOrderType());
            pstmt.setLong(3,order.getPaymentId());
            pstmt.setString(4,order.getPhoneNum());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private OrderVO mapOrder(ResultSet rs) throws SQLException {
        return OrderVO.builder()
                .orderId(rs.getLong("o_order_id"))
                .orderTime(rs.getTimestamp("order_time").toLocalDateTime())
                .totalPrice(rs.getInt("total_price"))
                .orderType(rs.getString("order_type"))
                .paymentId(rs.getLong("payment_id"))
                .phoneNum(rs.getString("phone_num"))
                .build();
    }

    private OrderItemVO mapOrderItem(ResultSet rs) throws SQLException {
        return OrderItemVO.builder()
                .orderItemId(rs.getLong("order_item_id"))
                .priceAtOrder(rs.getInt("price_at_order"))
                .foodName(rs.getString("food_name"))
                .subTotal(rs.getInt("subtotal"))
                .quantity(rs.getInt("quantity"))
                .orderId(rs.getLong("oi_order_id"))
                .foodId(rs.getLong("food_id"))
                .build();
    }

    @Override
    public Optional<OrderVO> getOrder(Long no) {

        OrderVO order= null;

        String sql = """
                SELECT
                  o.order_id AS o_order_id,
                  o.order_time,
                  o.total_price,
                  o.order_type,
                  o.payment_id,
                  o.phone_num,
                  oi.order_item_id,
                  oi.price_at_order,
                  oi.food_name,
                  oi.subtotal,
                  oi.quantity,
                  oi.food_id,
                  oi.order_id AS oi_order_id
                from order_tbl o
                inner join order_item_tbl oi
                on o.order_id=oi.order_id
                where o.order_id=?
                """;

        try(PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1,no);

            try(ResultSet rs = pstmt.executeQuery()){

                if(rs.next()){
                    order=mapOrder(rs);
                    List<OrderItemVO> orderItems = new ArrayList<>();
                    try {
                        do {
                            OrderItemVO orderItem = mapOrderItem(rs);
                            orderItems.add(orderItem);
                        } while(rs.next());
                    } catch (SQLException e){
                        //order있는데 item 없는 경우
                    }
                    order.setOrderItemList(orderItems);
                    return Optional.of(order);
                }
                else{
                    return Optional.empty();
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
