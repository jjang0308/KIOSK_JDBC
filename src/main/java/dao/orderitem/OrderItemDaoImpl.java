package dao.orderitem;

import config.JDBCUtil;
import domain.OrderItemVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderItemDaoImpl implements OrderItemDao {

    Connection conn = JDBCUtil.getConnection();

    public OrderItemVO map(ResultSet rs) throws SQLException {
//        OrderItemVO orderItemVO = new OrderItemVO();
//        orderItemVO.setPriceAtOrder(rs.getInt("price_at_order"));
//        orderItemVO.setFoodName(rs.getString("food_name"));
//        orderItemVO.setSubTotal(rs.getInt("subtotal"));
//        orderItemVO.setQuantity(rs.getInt("quantity"));
//        orderItemVO.setOrderId(rs.getLong("order_id"));
//        orderItemVO.setFoodId(rs.getLong("food_id"));
        return OrderItemVO.builder()
                .priceAtOrder(rs.getInt("price_at_order"))
                .foodName(rs.getString("food_name"))
                .subTotal(rs.getInt("subtotal"))
                .quantity(rs.getInt("quantity"))
                .orderId(rs.getLong("order_id"))
                .foodId(rs.getLong("food_id"))
                .build();
    }

    @Override
    public void insertOrderItem(OrderItemVO orderItemVO) {
        String sql = "insert into order_item_tbl(price_at_order, food_name, subtotal, quantity, order_id, food_id) values (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, orderItemVO.getPriceAtOrder());
            pstmt.setString(2, orderItemVO.getFoodName());
            pstmt.setInt(3, orderItemVO.getSubTotal());
            pstmt.setInt(4, orderItemVO.getQuantity());
            pstmt.setLong(5, orderItemVO.getOrderId());
            pstmt.setLong(6, orderItemVO.getFoodId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<OrderItemVO> getAllOrderItems() {
        String sql = "select * from order_item_tbl";
        List<OrderItemVO> orderItemVOList = new ArrayList<>();
        try (PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()){
                OrderItemVO orderItemVO = map(rs);
                orderItemVOList.add(orderItemVO);
            }
            return orderItemVOList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

//    @Override
//    public Optional<OrderItem> getOrderItem(Long id) {
//        String sql = "select * from order_item_tbl where id = ?";
//        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
//            pstmt.setLong(1, id);
//            try (ResultSet rs = pstmt.executeQuery()) {
//                while (rs.next()) {
//                    return Optional.of(map(rs));
//                }
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        return Optional.empty();
//    }




//    @Override
//    public void updateOrderItem(OrderItem orderItem){
//        StringBuilder sql = new StringBuilder("update order_item_tbl set");
//        List<Object> params = new ArrayList<>();
//        //DB에 default 0 설정 필요️해용~
//        if(orderItem.getQuantity() != 0) {
//            sql.append("quantity = ?, ");
//            params.add(orderItem.getQuantity());
//        }
//        //DB에 default 0 설정 필요해용~
//        if(orderItem.getSubTotal() != 0){
//            sql.append("subTotal = ?, ");
//            params.add(orderItem.getSubTotal());
//        }
//        sql.substring(sql.length()-2);
//        sql.append(" where id = ?");
//        params.add(orderItem.getOrderItemId());
//        try (PreparedStatement pstmt = conn.prepareStatement(sql.toString())){
//            for (int i = 0; i<params.size(); i++){
//                pstmt.setObject(i+1, params.get(i));
//            }
//            pstmt.executeUpdate();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }

//    @Override
//    public void deleteOrderItem(Long id) {
//        String sql = "delete from order_item_tbl where id = ?";
//        try(PreparedStatement pstmt = conn.prepareStatement(sql)) {
//            pstmt.setLong(1, id);
//            pstmt.executeUpdate();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
}
