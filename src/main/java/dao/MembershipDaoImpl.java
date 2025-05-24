package dao;

import domain.MembershipVO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MembershipDaoImpl implements MembershipDao {
    private final Connection conn;

    public MembershipDaoImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(MembershipVO member) {
        String sql = "INSERT INTO membership_tbl (phone_num, point, nickname) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, member.getPhone_num());
            pstmt.setInt(2, member.getPoint());
            pstmt.setString(3, member.getNickname());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public MembershipVO findByPhone(String phoneNum) {
        String sql = "SELECT * FROM membership_tbl WHERE phone_num = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, phoneNum);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return MembershipVO.builder()
                            .phone_num(rs.getString("phone_num"))
                            .point(rs.getInt("point"))
                            .nickname(rs.getString("nickname"))
                            .build();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void updatePoint(String phoneNum, int point) {
        String sql = "UPDATE membership_tbl SET point = ? WHERE phone_num = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, point);
            pstmt.setString(2, phoneNum);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<MembershipVO> getAllMembers() {
        List<MembershipVO> members = new ArrayList<>();
        String sql = "SELECT * FROM membership_tbl";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                members.add(MembershipVO.builder()
                        .phone_num(rs.getString("phone_num"))
                        .point(rs.getInt("point"))
                        .nickname(rs.getString("nickname"))
                        .build());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return members;
    }
}