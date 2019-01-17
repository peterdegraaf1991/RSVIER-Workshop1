package controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import utility.DatabaseConnection;
import org.beryx.textio.TextIO;
import org.beryx.textio.TextIoFactory;
import org.beryx.textio.TextTerminal;

public class DatabaseController {
	private TextIO textIO = TextIoFactory.getTextIO();
	TextTerminal<?> terminal = textIO.getTextTerminal();

	public static void InitDatabase() {

		try (Connection connection = DatabaseConnection.INSTANCE
				.getConnection();
				Statement statement = connection.createStatement()) {
			String queryCustomer = "INSERT INTO customer(id,firstname,middlename,surname) VALUES (1,'Peter','de','Graaf')";
			String queryCustomer2 = "INSERT INTO customer(id,firstname,middlename,surname) VALUES (999,'Mathijs','de','Graaf')";
			String queryAccountType = "INSERT INTO account_type (id, description) VALUES (1, 'testing')";
			String queryAccount = "INSERT INTO account(email,password,customer_id,account_type_id) VALUES ('testaccount@email.com','rsvier',1,1)";
			String queryProduct = "INSERT INTO product (name,price,stock) VALUES ('Bordspel',49.50,105)";
			String queryOrder = "INSERT INTO `order` (total_cost, customer_id, date) VALUES (10,1,CURRENT_TIMESTAMP)";
			String queryOrderLine = "INSERT INTO order_line (order_id,product_id,amount) VALUES (1,1,20)";
			statement.execute(queryCustomer);
			statement.execute(queryCustomer2);
			statement.execute(queryAccountType);
			statement.execute(queryAccount);
			statement.execute(queryProduct);
			statement.execute(queryOrder);
			statement.execute(queryOrderLine);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void ClearDatabase() {
		try (Connection connection = DatabaseConnection.INSTANCE
				.getConnection();
				Statement statement = connection.createStatement()) {

			statement.addBatch("SET FOREIGN_KEY_CHECKS=0");
			statement.addBatch("TRUNCATE `account`");
			statement.addBatch("TRUNCATE `account_type`");
			statement.addBatch("TRUNCATE `order_line`");
			statement.addBatch("TRUNCATE `order`");
			statement.addBatch("TRUNCATE `customer`");
			statement.addBatch("TRUNCATE `address`");
			statement.addBatch("TRUNCATE `address_type`");
			statement.addBatch("TRUNCATE `product`");
			statement.addBatch("SET FOREIGN_KEY_CHECKS=1");
			statement.executeBatch();
		} catch (SQLException e) {
			e.printStackTrace();
			/*
			 * try (Connection connection =
			 * DatabaseConnection.INSTANCE.getConnection(); Statement statement
			 * = connection.createStatement()) {
			 * 
			 * String query1 = "ALTER TABLE `account` AUTO_INCREMENT = 1";
			 * String query2 = "ALTER TABLE account_type AUTO_INCREMENT = 1";
			 * String query3 = "ALTER TABLE order_line AUTO_INCREMENT = 1";
			 * String query4 = "ALTER TABLE `order` AUTO_INCREMENT = 1"; String
			 * query5 = "ALTER TABLE customer AUTO_INCREMENT = 1"; String query6
			 * = "ALTER TABLE address AUTO_INCREMENT = 1"; String query7 =
			 * "ALTER TABLE address_type AUTO_INCREMENT = 1"; String query8 =
			 * "ALTER TABLE product AUTO_INCREMENT = 1";
			 * 
			 * statement.addBatch(query1); statement.addBatch(query2);
			 * statement.addBatch(query3); statement.addBatch(query4);
			 * statement.addBatch(query5); statement.addBatch(query6);
			 * statement.addBatch(query7); statement.addBatch(query8);
			 * statement.executeBatch(); } catch (SQLException e2) {
			 * e2.printStackTrace(); }
			 */}
	}
}
