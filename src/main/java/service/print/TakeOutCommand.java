package service.print;

import java.util.Scanner;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TakeOutCommand implements Command{
	private final Scanner sc;

	@Override
	public String execute() {
		System.out.println("1.먹고가기 2.포장하기 3.뒤로가기");
		return switch (sc.nextLine()) {
			case "1", "2" -> "category";
			case "3" -> "home";
			default -> "home";
		};
	}
}
