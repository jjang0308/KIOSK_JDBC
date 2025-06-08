package service.category;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import dao.category.CategoryDao;
import domain.category.CategoryVO;
import lombok.RequiredArgsConstructor;

import service.category.context.CategoryContext;

@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService{
	private final CategoryDao categoryDao;
	private final CategoryContext categoryContext;
	private final int CONSOLE_WIDTH = 30;
	@Override
	public void printCategories() {
		List<CategoryVO> categoryVOList =categoryDao.getCategories();
		printCategories(categoryVOList);
		inputCategoryId(categoryVOList);
	}

	private void printCategories(List<CategoryVO> categoryVOList) {
		String info = "번호를 입력하면 선택됩니다.";
		System.out.printf("카테고리 선택" + "%" + CONSOLE_WIDTH + "s\n", info);
		System.out.println();
		for (CategoryVO categoryVO : categoryVOList) {
			System.out.println(categoryVO.getCategory_id() + "."  + categoryVO.getName());
			System.out.println();
		}
		System.out.printf("6.뒤로가기" + "%" + CONSOLE_WIDTH + "s\n", "7.담은상품 결제하기");
	}

	private void inputCategoryId(List<CategoryVO> categoryVOList) {
		Scanner sc = new Scanner(System.in);
		while (true) {
			try {
				String input = sc.nextLine();
				Long inputCategoryId = Long.parseLong(input);

				Optional<CategoryVO> matched = categoryVOList.stream()
					.filter(vo -> vo.getCategory_id().equals(inputCategoryId))
					.findFirst();
				if (matched.isEmpty()) {
					System.out.println("존재하지 않는 카테고리 번호입니다. 다시 입력해주세요.");
					continue;
				}
				categoryContext.setCategoryId(matched.get().getCategory_id());
				categoryContext.setCategoryName(matched.get().getName());
				break;
			} catch (NumberFormatException e) {
				System.out.println("숫자만 입력해야 합니다.");
			}
		}
	}
}
