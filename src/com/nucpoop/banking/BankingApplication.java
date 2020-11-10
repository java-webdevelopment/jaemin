package com.nucpoop.banking;

import com.nucpoop.banking.manager.DBManager;
import com.nucpoop.banking.manager.LoginManager;

public class BankingApplication {

	public static void main(String[] args) {
		String id = "";
		
		DBManager.getInstance().init();
		id = LoginManager.getInstance().process();
		
		System.out.println("[ USER : " + id + " ]");
		
	}

}
