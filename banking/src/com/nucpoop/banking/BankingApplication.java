package com.nucpoop.banking;

import java.util.Scanner;

import com.nucpoop.banking.dto.DealDto;
import com.nucpoop.banking.dto.UserDto;
import com.nucpoop.banking.manager.DBManager;
import com.nucpoop.banking.manager.LoginManager;

public class BankingApplication {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		UserDto userDto = new UserDto();
		String input = "";
		boolean isEnd = false;

		DBManager.getInstance().init();
		userDto.setId(LoginManager.getInstance().process());
		userDto.setUserKey(DBManager.getInstance().getUserKey(userDto));
		userDto.setBalance(DBManager.getInstance().getBalance(userDto));
		System.out.println("[ USER : " + userDto.getId() + " ]");

		while (!isEnd) {
			intro();

			input = scanner.nextLine();
			char select = input.charAt(0);

			switch (select) {
			case 'A':
			case 'a':
				System.out.println("[ Balance : " + userDto.getBalance() + " ] ");
				System.out.println(">> input Enter to skip <<");
				scanner.nextLine();
				break;

			case 'B':
			case 'b':
				System.out.print("deposit >>");
				input = scanner.nextLine();
				deposit(Integer.parseInt(input), userDto);
				break;

			case 'C':
			case 'c':
				System.out.print("withdraw >>");
				input = scanner.nextLine();
				withdraw(Integer.parseInt(input), userDto);
				break;

			case 'D':
			case 'd':
				System.out.println("previous transaction >>");
				DealDto temp = DBManager.getInstance().getPreviousTransaction(userDto);
				System.out.println("deal : " + temp.getDealType() + " price : " + temp.getPrice());
				System.out.println(">> input Enter to skip <<");
				scanner.nextLine();
				break;
				
			default:
				isEnd = true;
				DBManager.getInstance().closeDB();
				break;

			}
		}
	}

	private static void deposit(int price, UserDto user) {
		int current = DBManager.getInstance().getBalance(user);
		DealDto deal = new DealDto(user.getUserKey(), "deposit", price);
		current += price;
		user.setBalance(current);

		DBManager.getInstance().insertTransaction(deal);
		DBManager.getInstance().updateBalance(user);
	}

	private static void withdraw(int price, UserDto user) {
		int current = DBManager.getInstance().getBalance(user);
		DealDto deal = new DealDto(user.getUserKey(), "withdraw", price);
		current -= price;
		user.setBalance(current);

		DBManager.getInstance().insertTransaction(deal);
		DBManager.getInstance().updateBalance(user);
	}

	private static void intro() {
		System.out.println("======================");
		System.out.println("Jam Bank");
		System.out.println("======================");
		System.out.println("a. Check balance");
		System.out.println("b. Deposit");
		System.out.println("c. Withdraw");
		System.out.println("d. Previous transaction");
		System.out.print("Select menu >>");
	}
}
