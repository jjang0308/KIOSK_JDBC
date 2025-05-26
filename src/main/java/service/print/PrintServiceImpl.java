package service.print;

import java.util.List;
import java.util.Scanner;

import dao.category.CategoryDaoImpl;
import dao.food.FoodDaoImpl;
import domain.category.CategoryVO;
import domain.food.FoodVO;

public class PrintServiceImpl implements PrintService{
	private final FoodDaoImpl foodDao = new FoodDaoImpl();
	private final CategoryDaoImpl categoryDao = new CategoryDaoImpl();
	Long categoryId;
	Scanner sc = new Scanner(System.in);
	@Override
	public String printHome() {
		System.out.println("1.주문하기 2.종료\t\t번호를 입력해주세요.");
		return switch (sc.nextLine()) {
			case "1" -> "takeout";
			case "2" -> "exit";
			default -> "home";    //다르게 처리
		};
	}

	@Override
	public String printTakeOut() {
		System.out.println("1.먹고가기 2.포장하기 3.뒤로가기");
		return switch (sc.nextLine()) {
			case "1", "2" -> "category";
			case "3" -> "home";
			default -> "home";    //다르게
		};
	}

	@Override
	public String printCategory() {
		List<CategoryVO> categoryList = categoryDao.getCategories();
		for (CategoryVO categoryVO : categoryList) {
			System.out.println(categoryVO.getCategory_id() + "." + categoryVO.getName());
		}
		String command = sc.nextLine();
		categoryId = Long.parseLong(command);
		return switch (command) {
			case "1", "2", "3", "4", "5" -> "food";
			default -> "home";
		};
	}

	@Override
	public String printFoodByCategory() {
		List<FoodVO> foodVOList = foodDao.getFoodListByCategory(categoryId);
		for(FoodVO foodVO : foodVOList) {
			System.out.println(foodVO.getFood_id() + "." + foodVO.getName() + "(" + foodVO.getPrice() + "원)");
		}
		if(Integer.parseInt(sc.nextLine()) > 0 && Integer.parseInt(sc.nextLine()) <= foodVOList.size()){
			return "cart";
		} else {
			return "home";
		}
	}

	@Override
	public String printCart() {
		return null;
	}

	@Override
	public String printPhone() {
		return null;
	}

	@Override
	public String printPayment() {
		return null;
	}

	@Override
	public String printPoint() {
		return null;
	}

	@Override
	public String printReceipt() {
		return null;
	}
}
