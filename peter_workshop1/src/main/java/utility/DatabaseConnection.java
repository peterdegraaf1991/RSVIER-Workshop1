package utility;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zaxxer.hikari.HikariDataSource;

public enum DatabaseConnection {
	INSTANCE;

	private static final Logger LOG = LoggerFactory
			.getLogger(DatabaseConnection.class);

	private String username;
	private String password;
	private String url;
	private Connection connection;
	private HikariDataSource dataSource = new HikariDataSource();

	// Connection managed by DriverManager
	/*
	 * public Connection getConnection() { if (username == null || password ==
	 * null || url == null) getLoginDetails();
	 * 
	 * try { if (connection == null || connection.isClosed()) { connection =
	 * DriverManager.getConnection(url, username, password); } } catch
	 * (SQLException e) { e.printStackTrace(); } return connection; }
	 */

	// Connection managed by Hikari
	public Connection getConnection() {
		if (username == null || password == null || url == null){
			getLoginDetails();
		dataSource.setJdbcUrl(url);
		dataSource.setUsername(username);
		dataSource.setPassword(password);
		}
		try {
			if (connection == null || connection.isClosed()) {
				connection = dataSource.getConnection();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}

	private void getLoginDetails() {
		File xmlFile = new File("src/main/java/utility/LoginDetails.xml");
		try {
			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder documentBuilder = documentBuilderFactory
					.newDocumentBuilder();
			Document document = documentBuilder.parse(xmlFile);
			document.getDocumentElement().normalize();
			username = document.getElementsByTagName("username").item(0)
					.getTextContent();
			password = document.getElementsByTagName("password").item(0)
					.getTextContent();
			url = document.getElementsByTagName("url").item(0).getTextContent();
		} catch (SAXException | IOException | ParserConfigurationException e) {
			e.printStackTrace();
		}
	}
}
