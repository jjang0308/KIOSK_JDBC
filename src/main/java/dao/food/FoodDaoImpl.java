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
	//카테고리별 음식 목록 조회
	@Override
	public List<FoodVO> getFoodListByCategory(Long categoryId) {
		List<FoodVO> foodVOList = new ArrayList<>();
		String sql = "select * from food_tbl where category_id = ? order by food_id";
		try(PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setLong(1, categoryId);
			try(ResultSet rs = pstmt.executeQuery()) {
				while (rs.next()) {
					FoodVO foodVO = map(rs);
					foodVOList.add(foodVO);
				}
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
		String sql = "select * from food_tbl where food_id = ?";
		try(PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setLong(1, foodId);
			try(ResultSet rs = pstmt.executeQuery()) {
				if(rs.next()){
					foodVO = map(rs);
				} else {
					throw new RuntimeException("food 단일 조회 실패");
				}
			}
		} catch (SQLException e){
			throw new RuntimeException(e);
		}
		return foodVO;
	}

	private FoodVO map(ResultSet rs) throws SQLException {
		return FoodVO.builder()
			.food_id(rs.getLong("food_id"))
			.name(rs.getString("name"))
			.price(rs.getInt("price"))
			.build();
	}
}
