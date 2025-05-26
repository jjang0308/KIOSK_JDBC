package service.print;

import java.util.List;
import java.util.Scanner;

import dao.food.FoodDao;
import dao.food.FoodDaoImpl;
import domain.food.FoodVO;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class FoodCommand implements Command{
	private final FoodDao foodDao = new FoodDaoImpl();
	private final Scanner sc;
	private final CategoryContext categoryContext;
	@Override
	public String execute() {
		Long categoryId = categoryContext.getCategoryId(); // 제대로 들어오고 있는지 확인
		List<FoodVO> foodList = foodDao.getFoodListByCategory(categoryId);

		for (FoodVO food : foodList) {
			System.out.println(food.getFood_id() + "." + food.getName() + "(" + food.getPrice() + "원)");
		}

		// 입력 받고 다음 단계로 넘어가도록
		String input = sc.nextLine();
		return "cart"; // 임시로, 실제 흐름 맞춰서 처리
	}
}
