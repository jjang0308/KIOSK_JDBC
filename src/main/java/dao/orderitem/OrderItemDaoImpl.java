package dao.orderitem;

import config.JDBCUtil;
import domain.OrderItem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrderItemDaoImpl implements OrderItemDao {

    Connection conn = JDBCUtil.getConnection();

    public OrderItem map(ResultSet rs) throws SQLException {
        OrderItem orderItem = new OrderItem();
        orderItem.setPriceAtOrder(rs.getInt("price_at_order"));
        orderItem.setFoodName(rs.getString("food_name"));
        orderItem.setSubTotal(rs.getInt("subtotal"));
        orderItem.setQuantity(rs.getInt("quantity"));
        orderItem.setOrderId(rs.getLong("order_id"));
        orderItem.setFoodId(rs.getLong("food_id"));
        return orderItem;
    }

    @Override
    public void insertOrderItem(OrderItem orderItem) {
        String sql = "insert into order_item_tbl(price_at_order, food_name, subtotal, quantity, order_id, food_id) values (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, orderItem.getPriceAtOrder());
            pstmt.setString(2, orderItem.getFoodName());
            pstmt.setInt(3, orderItem.getSubTotal());
            pstmt.setInt(4, orderItem.getQuantity());
            pstmt.setLong(5, orderItem.getOrderId());
            pstmt.setLong(6, orderItem.getFoodId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<OrderItem> getAllOrderItems() {
        String sql = "select * from order_item_tbl";
        List<OrderItem> orderItemList = new ArrayList<>();
        try (PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()){
                OrderItem orderItem = map(rs);
                orderItemList.add(orderItem);
            }
            return orderItemList;
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
