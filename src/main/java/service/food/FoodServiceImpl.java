package service.food;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import dao.food.FoodDao;
import domain.food.FoodVO;
import lombok.RequiredArgsConstructor;
import service.category.context.CategoryContext;

@RequiredArgsConstructor
public class FoodServiceImpl implements FoodService{
	private final FoodDao foodDao;
	private final CartService cartService;
	private final CategoryContext categoryContext;
	private final int CONSOLE_WIDTH = 30;
	@Override
	public void printFoodList() {
		List<FoodVO> foodVOList = foodDao.getFoodListByCategory(categoryContext.getCategoryId());
		printFoodList(foodVOList);
		inputFoodId(foodVOList);
	}

	private void printFoodList(List<FoodVO> foodVOList){
		String info = "번호를 입력하면 선택됩니다.";
		System.out.printf(categoryContext.getCategoryName() + " 선택" + "%" + CONSOLE_WIDTH + "s\n", info);
		System.out.println();
		for (FoodVO foodVO : foodVOList) {
			System.out.println(foodVO.getCategory_id() + "."  + foodVO.getName() + "(" + foodVO.getPrice() + "원)");
			System.out.println();
		}
		System.out.println((foodVOList.size() + 1) + ".뒤로가기");
	}

	private void inputFoodId(List<FoodVO> foodVOList){
		Scanner sc = new Scanner(System.in);
		while (true) {
			try {
				String input = sc.nextLine();
				Long inputFoodId = Long.parseLong(input);
				Optional<FoodVO> matched = foodVOList.stream()
					.filter(vo -> vo.getFood_id().equals(inputFoodId))
					.findFirst();
				if (matched.isEmpty()) {
					System.out.println("존재하지 않는 음식 번호입니다. 다시 입력해주세요.");
					continue;
				}
				cartService.addToCart(matched.get());
				break;
			} catch (NumberFormatException e) {
				System.out.println("숫자만 입력해야 합니다.");
			}
		}
	}
}
