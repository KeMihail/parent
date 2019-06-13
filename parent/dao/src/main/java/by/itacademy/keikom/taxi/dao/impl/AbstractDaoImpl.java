package by.itacademy.keikom.taxi.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;

public class AbstractDaoImpl {

	@Value("jdbc:mysql://${host}:${port}/${dbname}?useSSL=false")
	private String dbURL;

	@Value("${login}")
	private String dbLogin;

	@Value("${password}")
	private String dbPassword;

	@PostConstruct
	public void unit() {

		try {

			Class.forName("com.mysql.cj.jdbc.Driver");

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public Connection getConnection() throws SQLException {
		return DriverManager.getConnection("jdbc:mysql://localhost:3306/taxi?useSSL=false", "root", "40381172");
	}
}
