package controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import utility.DatabaseConnection;

public class DatabaseController {

	public void InitDatabase() {
		try (Connection connection = DatabaseConnection.INSTANCE.getConnection(); Statement statement = connection.createStatement()) {
		String queryCustomer = "INSERT INTO customer(id,firstname,middlename,surname) VALUES (1,'Peter','de','Graaf')";
		String queryAccountType = "INSERT INTO account_type (id, description) VALUES (1, 'testing')";
		String queryAccount = "INSERT INTO account(email,password,customer_id,account_type_id) VALUES ('test3@hotmail.com','rsvier',1,1)";
		statement.execute(queryCustomer);
		statement.execute(queryAccountType);
		statement.execute(queryAccount);
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void ClearDatabase(){
		try (Connection connection = DatabaseConnection.INSTANCE.getConnection(); Statement statement = connection.createStatement()) {
				String query1 = "DELETE FROM account";
				String query2 = "DELETE FROM account_type";
				String query3 = "DELETE FROM order_line";
				String query4 = "DELETE FROM `order`";
				String query5 = "DELETE FROM customer";
				String query6 = "DELETE FROM address";
				String query7 = "DELETE FROM address_type";
				String query8 = "DELETE FROM product";
				
				statement.addBatch(query1);
				statement.addBatch(query2);
				statement.addBatch(query3);
				statement.addBatch(query4);
				statement.addBatch(query5);
				statement.addBatch(query6);
				statement.addBatch(query7);
				statement.addBatch(query8);
				statement.executeBatch();
				} catch (SQLException e) {
					e.printStackTrace();
				try (Connection connection = DatabaseConnection.INSTANCE.getConnection(); Statement statement = connection.createStatement()) {
				 
				String query1 = "ALTER TABLE account AUTO_INCREMENT = 1";
				String query2 = "ALTER TABLE account_type AUTO_INCREMENT = 1";
				String query3 = "ALTER TABLE order_line AUTO_INCREMENT = 1";
				String query4 = "ALTER TABLE `order` AUTO_INCREMENT = 1";
				String query5 = "ALTER TABLE customer AUTO_INCREMENT = 1";
				String query6 = "ALTER TABLE address AUTO_INCREMENT = 1";
				String query7 = "ALTER TABLE address_type AUTO_INCREMENT = 1";
				String query8 = "ALTER TABLE product AUTO_INCREMENT = 1";
		
				statement.addBatch(query1);
				statement.addBatch(query2);
				statement.addBatch(query3);
				statement.addBatch(query4);
				statement.addBatch(query5);
				statement.addBatch(query6);
				statement.addBatch(query7);
				statement.addBatch(query8);
				statement.executeBatch();
				} 
				catch (SQLException e2) {
				e.printStackTrace();
				}
				}
}
}
