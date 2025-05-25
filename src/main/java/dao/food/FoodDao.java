package dao.food;

import java.util.List;

import domain.food.FoodVO;

public interface FoodDao {
	void insert(FoodVO foodVO);
	List<FoodVO> getFoodListByCategory(Long categoryId);

	// void update(FoodVO foodVO);
	// void delete(Long foodId);
}
