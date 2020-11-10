package com.nucpoop.banking;

import java.util.Scanner;

import com.nucpoop.banking.manager.DBManager;
import com.nucpoop.banking.manager.LoginManager;

public class BankingApplication {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String id = "";
		String input = "";
		boolean isEnd = false;

		DBManager.getInstance().init();
		id = LoginManager.getInstance().process();
		System.out.println("[ USER : " + id + " ]");
		
		while (!isEnd) {
			intro();
			input = scanner.nextLine();
			char select = input.charAt(0);
			switch (select) {
			case 'A':
			case 'a':
				System.out.println("[ Balance : " + DBManager.getInstance().getBalance(id) + " ] ");
				scanner.nextLine();
				break;
			case 'B':
			case 'b':
				break;
			case 'C':
			case 'c':
				break;
			case 'D':
			case 'd':
				break;
			}
		}
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
