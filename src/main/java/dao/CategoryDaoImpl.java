package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import database.JDBCUtil;
import domain.CategoryVO;
public class CategoryDaoImpl implements CategoryDao{

	Connection conn = JDBCUtil.getConnection();

	//카테고리 전체조회
	@Override
	public List<CategoryVO> getCategories() {
		List<CategoryVO> categoryVOList = new ArrayList<>();
		String sql = "select * from category_tbl order by category_id";
		try(PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery()) {
			while(rs.next()) {
				CategoryVO categoryVO = map(rs);
				categoryVOList.add(categoryVO);
			}
			if(categoryVOList.isEmpty()){
				throw new NullPointerException();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return categoryVOList;
	}

	//categoryVOList 변환 mapper
	private CategoryVO map(ResultSet rs) throws SQLException {
		return CategoryVO.builder()
			.id(rs.getLong("category_id"))
			.name(rs.getString("name"))
			.build();
	}
}
