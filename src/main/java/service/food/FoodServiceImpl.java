package service.food;

import java.util.List;

import dao.food.FoodDaoImpl;
import domain.category.CategoryVO;
import domain.food.FoodVO;
import lombok.RequiredArgsConstructor;
import service.category.context.CategoryContext;

@RequiredArgsConstructor
public class FoodServiceImpl implements FoodService{
	private final FoodDaoImpl foodDao;
	private final CategoryContext categoryContext;
	private final int CONSOLE_WIDTH = 30;
	@Override
	public void printFoodList() {
		List<FoodVO> foodVOList = foodDao.getFoodListByCategory(categoryContext.categoryId());
		printFoodList(foodVOList);
	}

	private void printFoodList(List<FoodVO> foodVOList){
		String info = "번호를 입력하면 선택됩니다.";
		long lastIdx = 0L;
		System.out.printf("단품 선택" + "%" + CONSOLE_WIDTH + "s\n", info);
		System.out.println();
		for (FoodVO foodVO : foodVOList) {
			System.out.println(foodVO.getCategory_id() + "."  + foodVO.getName() + "(" + foodVO.getPrice() + "원)");
			System.out.println();
			lastIdx = foodVO.getCategory_id();
		}
		System.out.println(lastIdx + 1 + ".뒤로가기");
	}
}
