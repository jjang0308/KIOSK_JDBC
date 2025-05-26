package dao.order;

import config.JDBCUtil;
import domain.OrderVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
}
