package service.print;

public interface PrintService {
	String printHome();	//첫 화면 출력
	String printTakeOut();	// 두 번째 화면 출력
	String printCategory();	// 카테고리 화면 출력
	String printFoodByCategory();	// 카테고리별 음식 출력
	String printCart();	//장바구니 출력
	String printPhone();	//휴대폰 번호 입력 출력
	String printPayment();	// 결제방식 선택창 출력
	String printPoint(); //포인트 확인 및 사용
	String printReceipt();	//영수증 출력
}
