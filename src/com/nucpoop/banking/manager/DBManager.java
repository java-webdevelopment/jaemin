package com.nucpoop.banking.manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.nucpoop.banking.LoginState;
import com.nucpoop.banking.dto.UserDto;

public class DBManager {
	private static final String driver = "org.mariadb.jdbc.Driver";
	private static final String jdbcUrl = "jdbc:mariadb://kjm5546.iptime.org:3306/banking?autoReconnect=true&verifyServerCeritificate=false&useSSL=false";
	private static final String userId = "pi";
	private static final String userPass = "1101";

	private static DBManager instance = new DBManager();
	private static Connection conn;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet;

	private DBManager() {
		conn = connectToDB();
	}

	public static DBManager getInstance() {
		return instance;
	}

	private static Connection connectToDB() {
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {

			Connection connection = DriverManager.getConnection(jdbcUrl, userId, userPass);
			System.out.println("DB connect success");
			return connection;
		} catch (Exception e) {
			System.out.println("DB connect fail");
		}
		return null;
	}

	public void init() {
	}

	public void insertAccount(UserDto userDto) {
		String sql = "insert into User(id, pw) values (?,?)";

		try {
			preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, userDto.getId());
			preparedStatement.setString(2, userDto.getPw());
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			System.out.println("[SQL Error : " + e.getMessage() + "]");
		}
	}
	
	public LoginState signIn(UserDto userDto) {
		String sql = "select pw from User where id=?";
		
		try {
			preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, userDto.getId());
			resultSet = preparedStatement.executeQuery();
			
			if(resultSet.next()) {
				if(resultSet.getString(1).equals(userDto.getPw())) {
					return LoginState.SUCCESS;
				}else {
					return LoginState.INCORRECT_PASSWORD;
				}
			}
			return LoginState.NOT_FOUND_ID;
			

		} catch (SQLException e) {
			System.out.println("[SQL Error : " + e.getMessage() + "]");
			return LoginState.ERROR;
		}
	}
}
