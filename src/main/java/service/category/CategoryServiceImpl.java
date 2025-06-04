package service.category;

import java.util.List;

import dao.category.CategoryDao;
import domain.category.CategoryVO;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService{
	private final CategoryDao categoryDao;
	private final int CONSOLE_WIDTH = 30;
	@Override
	public void printCategories() {
		List<CategoryVO> categoryVOList =categoryDao.getCategories();
		printCategories(categoryVOList);
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
}
