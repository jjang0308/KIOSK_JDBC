package dao.food;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import config.JDBCUtil;
import domain.food.FoodVO;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
class FoodDaoImplTest {
	private FoodDao foodDao = new FoodDaoImpl();
	Connection conn = JDBCUtil.getConnection();

	@Test
	@DisplayName("카테고리별로 목록을 조회한다.")
	void getFoodListByCategory() {
		Long categoryId = 1L;
		FoodVO food1 = FoodVO.builder()
			.name("불고기버거")
			.price(4500)
			.category_id(categoryId)
			.build();
		FoodVO food2 = FoodVO.builder()
			.name("맥스파이시")
			.price(6000)
			.category_id(categoryId)
			.build();
		foodDao.insert(food1);
		foodDao.insert(food2);

		List<FoodVO> foodVOList = foodDao.getFoodListByCategory(categoryId);
		assertNotNull(foodVOList);
		assertEquals(2, foodVOList.size());
		assertTrue(foodVOList.stream().anyMatch(f -> f.getName().equals("불고기버거")));
		assertTrue(foodVOList.stream().anyMatch(f -> f.getName().equals("맥스파이시")));
	}

	@Test
	@DisplayName("메뉴 단일 조회")
	void getFood() {
		Long foodId = 1L;
		FoodVO food1 = FoodVO.builder()
			.name("불고기버거")
			.price(4500)
			.category_id(foodId)
			.build();

		foodDao.insert(food1);
		FoodVO foodVO = foodDao.getFood(foodId);
		assertNotNull(foodVO);
		assertEquals("불고기버거", foodVO.getName());
	}
}