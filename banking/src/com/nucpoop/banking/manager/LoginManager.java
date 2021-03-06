package com.nucpoop.banking.manager;

import java.util.Scanner;

import com.nucpoop.banking.LoginState;
import com.nucpoop.banking.dto.UserDto;

public class LoginManager {

	private static LoginManager instance = new LoginManager();
	private Scanner scanner = new Scanner(System.in);
	private String input;
	private String id;
	private LoginState result;

	private LoginManager() {
		input = "";
		id = "";
		result = LoginState.OFFLINE;
	}

	public static LoginManager getInstance() {
		return instance;
	}

	public String process() {
		while (result != LoginState.SUCCESS) {

			System.out.println("Account");
			System.out.println("======================");
			System.out.println("a. Create account");
			System.out.println("b. Sign in");
			System.out.print("Select menu >>");

			input = scanner.nextLine();
			char select = input.charAt(0);

			switch (select) {
			case 'A':
			case 'a':
				signUp();
				break;
			case 'B':
			case 'b':
				signIn();
				break;
			default:
				break;
			}
		}
		return id;
	}

	private void signUp() {
		String id = "";
		String pswd = "";

		System.out.print("Input NEW id : ");
		id = scanner.nextLine();
		System.out.print("Input NEW password : ");
		pswd = scanner.nextLine();
		System.out.print("Input CONFIRM password : ");
		input = scanner.nextLine();

		if (pswd.equals(input)) {
			UserDto userDto = new UserDto(id, pswd);
			DBManager.getInstance().insertAccount(userDto);
		} else {
			System.out.println("[ Check your password ]");
			return;
		}
	}

	private void signIn() {
		String id = "";
		String pswd = "";

		System.out.print("Input id : ");
		id = scanner.nextLine();
		System.out.print("Input password : ");
		pswd = scanner.nextLine();

		UserDto userDto = new UserDto(id, pswd);
		result = DBManager.getInstance().signIn(userDto);

		switch (result) {
		case SUCCESS:
			this.id = id;
			System.out.println("[ Login Complete ]");
			break;
		case INCORRECT_PASSWORD:
			System.out.println("[ Incorrect Password ]");
			break;
		case NOT_FOUND_ID:
			System.out.println("[ Not valed ID ]");
			break;
		case ERROR:
			System.out.println("[ Not valed ID ]");
			break;
		}
	}

}
