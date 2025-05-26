package service.print;

import java.util.List;
import java.util.Scanner;

import dao.category.CategoryDao;
import dao.category.CategoryDaoImpl;
import domain.category.CategoryVO;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CategoryCommand implements Command{
	private final Scanner sc;
	private final CategoryDao categoryDao = new CategoryDaoImpl();
	private final CategoryContext categoryContext;
	@Override
	public String execute() {
		List<CategoryVO> categoryList = categoryDao.getCategories();
		for (CategoryVO categoryVO : categoryList) {
			System.out.println(categoryVO.getCategory_id() + "." + categoryVO.getName());
		}
		String command = sc.nextLine();
		categoryContext.setCategoryId(Long.parseLong(command));
		return switch (command) {
			case "1", "2", "3", "4", "5" -> "food";
			default -> "home";
		};
	}
}
