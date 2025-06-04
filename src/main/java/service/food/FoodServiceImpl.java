package service.food;

import java.util.List;

import dao.food.FoodDaoImpl;
import domain.food.FoodVO;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class FoodServiceImpl implements FoodService{
	private final FoodDaoImpl foodDao;
	@Override
	public void printFoodList() {
		List<FoodVO> foodVOList = foodDao.getFoodListByCategory();
	}

	@Override
	public void printFood() {

	}

	// private void printFoodList(){
	//
	// }
}
