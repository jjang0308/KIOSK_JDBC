package dao.food;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import config.JDBCUtil;
import domain.food.FoodVO;

public class FoodDaoImpl implements FoodDao{
	Connection conn = JDBCUtil.getConnection();

	//화면 구성에는 없지만, 새 메뉴 추가 or 메뉴명이나 가격 변경 시 사용
	@Override
	public void insert(FoodVO foodVO) {
		String sql = "insert into food_tbl(name, price, category_id)";
		try(PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, foodVO.getName());
			pstmt.setInt(2, foodVO.getPrice());
			pstmt.setLong(3, foodVO.getCategory_id());
			pstmt.executeUpdate();
		} catch (SQLException e){
			throw new RuntimeException(e);
		}
	}

	//카테고리별 음식 목록 조회
	@Override
	public List<FoodVO> getFoodListByCategory(Long categoryId) {
		List<FoodVO> foodVOList = new ArrayList<>();
		String sql = "select * from food_tbl where category_id = ? order by food_id";
		try(PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery()) {
			while (rs.next()) {
				FoodVO foodVO = map(rs);
				foodVOList.add(foodVO);
			}
		} catch (SQLException e){
			throw new RuntimeException(e);
		}
		return foodVOList;
	}

	//음식 단일 조회
	@Override
	public FoodVO getFood(Long foodId) {
		FoodVO foodVO;
		String sql = "select name from food_tbl where food_id = ?";
		try(PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery()) {
			foodVO = map(rs);
		} catch (SQLException e){
			throw new RuntimeException(e);
		}
		return foodVO;
	}

	//음식 삭제
	//필요시 사용
	@Override
	public void delete(Long foodId) {
		String sql = "delete from food_tbl where food_id = ?";
		try(PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery()) {
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	private FoodVO map(ResultSet rs) throws SQLException {
		return FoodVO.builder()
			.food_id(rs.getLong("food_id"))
			.name(rs.getString("name"))
			.price(rs.getInt("price"))
			.build();
	}
}
