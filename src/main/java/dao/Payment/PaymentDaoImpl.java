package dao.Payment;

import domain.Payment.PaymentVO;
import java.sql.*;
import java.util.*;

public class PaymentDaoImpl implements PaymentDao {
    private final Connection conn;

    public PaymentDaoImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(PaymentVO payment) {
        String sql = "INSERT INTO payment_tbl (payment_id, payment_type) VALUES (?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1, payment.getPayment_id());
            pstmt.setString(2, payment.getPayment_type());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getTotalCount() {
        String sql = "SELECT COUNT(*) FROM payment_tbl";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) return rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public List<String> getPayment_type() {
        List<String> types = new ArrayList<>();
        String sql = "SELECT DISTINCT payment_type FROM payment_tbl";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                types.add(rs.getString("payment_type"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return types;
    }
}
