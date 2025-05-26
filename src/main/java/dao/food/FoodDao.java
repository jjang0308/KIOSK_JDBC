package dao.food;

import java.util.List;

import domain.food.FoodVO;

public interface FoodDao {
	List<FoodVO> getFoodListByCategory(Long categoryId);

	FoodVO getFood(Long foodId);
}
