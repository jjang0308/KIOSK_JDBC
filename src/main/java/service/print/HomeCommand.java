package service.print;

import java.util.Scanner;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class HomeCommand implements Command{
	private final Scanner sc;
	@Override
	public String execute() {
		System.out.println("1.주문하기  2.종료\t\t번호를 입력해주세요.");
		return switch (sc.nextLine()) {
			case "1" -> "takeout";
			case "2" -> "exit";
			default -> "home";
		};
	}
}
