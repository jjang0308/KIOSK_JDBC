package service.category;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import dao.category.CategoryDaoImpl;
import lombok.RequiredArgsConstructor;

class CategoryServiceImplTest {
	@Test
	@DisplayName("카테고리 목록이 제대로 출력되는지 확인한다.")
	void printCategories() {
		CategoryDaoImpl categoryDao = new CategoryDaoImpl();
		CategoryServiceImpl categoryService = new CategoryServiceImpl(categoryDao);
		categoryService.printCategories();
	}
}