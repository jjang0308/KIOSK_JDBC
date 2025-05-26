package app;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import lombok.RequiredArgsConstructor;
import service.print.CategoryCommand;
import service.print.CategoryContext;
import service.print.Command;
import service.print.FoodCommand;
import service.print.HomeCommand;
import service.print.PrintServiceImpl;
import service.print.TakeOutCommand;

@RequiredArgsConstructor
public class TestApp {
	private final Map<String, Command> commands = new HashMap<>();
	private final PrintServiceImpl printService = new PrintServiceImpl();
	private final Scanner sc = new Scanner(System.in);
	private final CategoryContext categoryContext = new CategoryContext();
	private void initializeCommands() {
		commands.put("home", new HomeCommand(sc));
		commands.put("takeout", new TakeOutCommand(sc));
		commands.put("category", new CategoryCommand(sc, categoryContext));
		commands.put("food", new FoodCommand(sc, categoryContext));
		// commands.put("cart", () -> command = printService.printCart());
		// commands.put("phone", () -> command = printService.printPhone());
		// commands.put("payment", () -> command = printService.printPayment());
		// commands.put("point", () -> command = printService.printPoint());
		// commands.put("receipt", () -> command = printService.printReceipt());
	}
	public void run(){
		String command = "home";
		initializeCommands();

		while (!"exit".equals(command)) {
			Command cmd = commands.get(command);
			if (cmd != null) {
				command = cmd.execute();
			} else {
				System.out.println("잘못된 명령어.");
				command = "home";
			}
		}

		System.out.println("종료.");

	}
	public static void main(String[] main) {
		TestApp testApp = new TestApp();
		testApp.run();
	}
}
