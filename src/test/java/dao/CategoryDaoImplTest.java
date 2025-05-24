
package dao;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import config.JDBCUtil;
import dao.category.CategoryDaoImpl;
import domain.category.CategoryVO;

class CategoryDaoImplTest {
	@Test
	@DisplayName("jdbc_ex에 접속한다.(자동 닫기)")
	public void testConnection2() throws SQLException {
		try (Connection conn = JDBCUtil.getConnection()) {
			System.out.println("DB 연결 성공");
		}
	}

	@Test
	@DisplayName("카테고리 목록을 조회 후 출력한다.")
	void getCategories() {
		CategoryDaoImpl dao = new CategoryDaoImpl();

		List<CategoryVO> categoryVOList = dao.getCategories();

		assertNotNull(categoryVOList);
		assertFalse(categoryVOList.isEmpty());

		for (CategoryVO category : categoryVOList) {
			assertNotNull(category.getCategory_id());
			assertNotNull(category.getName());
			System.out.println(category.getCategory_id() + ". " + category.getName());
		}
	}
}