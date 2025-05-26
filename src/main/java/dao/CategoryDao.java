package dao;

import java.util.List;

import domain.category.CategoryVO;

public interface CategoryDao {
	List<CategoryVO> getCategories();	//카테고리 목록 전체 조회
}
