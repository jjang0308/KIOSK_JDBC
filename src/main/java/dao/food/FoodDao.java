package dao.food;

import java.util.List;

import domain.food.FoodVO;

public interface FoodDao {
	void insert(FoodVO foodVO);
	List<FoodVO> getFoodListByCategory(Long categoryId);

	FoodVO getFood(Long foodId);
	void delete(Long foodId);
}
