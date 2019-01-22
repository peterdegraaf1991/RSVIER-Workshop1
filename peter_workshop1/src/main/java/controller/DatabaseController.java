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
			statement.addBatch("INSERT INTO customer(id,firstname,middlename,surname) VALUES (1,'Peter','de','Graaf')");
			statement.addBatch("INSERT INTO customer(id,firstname,middlename,surname) VALUES (999,'Mathijs','de','Graaf')");
			statement.addBatch("INSERT INTO account_type (id, description) VALUES (1, 'Customer')");
			statement.addBatch("INSERT INTO account_type (id, description) VALUES (2, 'Worker')");
			statement.addBatch("INSERT INTO account_type (id, description) VALUES (3, 'Admin')");
			statement.addBatch("INSERT INTO account(email,password,hash,customer_id,account_type_id) VALUES ('AdminAccount@email.com','adminaccount','sha1:64000:18:uZ2K5AOUBZhSXewoTvzbTaSV6bafZZRP:fIPrhx2nlTzlOzNQfJrWjgcY',1,3)");
			statement.addBatch("INSERT INTO account(email,password,hash,customer_id,account_type_id) VALUES ('CustomerAccount@email.com','customeraccount','sha1:64000:18:XN0Kvt41W/v7iQHnrmhFOrw2XMERsGHu:DgIc8nqQEE59x8yhJygkPXqV',999,1)");
			statement.addBatch("INSERT INTO product (name,price,stock) VALUES ('Bordspel',49.50,105)");
			statement.addBatch("INSERT INTO `order` (total_cost, customer_id, date) VALUES (10,1,CURRENT_TIMESTAMP)");
			statement.addBatch("INSERT INTO order_line (order_id,product_id,amount) VALUES (1,1,20)");
			statement.executeBatch();
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
		}
	}
}
