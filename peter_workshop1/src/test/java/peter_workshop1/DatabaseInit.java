package peter_workshop1;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;

import model_class.Customer;
import model_class.Order;
import model_class.Product;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import utility.DatabaseConnection;

public class DatabaseInit {

	private static final Logger LOG = LoggerFactory
			.getLogger(DatabaseInit.class);

	public static void DeleteRows() {
		try (Connection connection = DatabaseConnection.INSTANCE
				.getConnectionSQL();
				Statement statement = connection.createStatement()) {
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
			LOG.info("All rows from database have been deleted");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void ResetAutoIncement() {
		try (Connection connection = DatabaseConnection.INSTANCE
				.getConnectionSQL();
				Statement statement = connection.createStatement()) {
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
			LOG.trace("All AutoIncrements have been reset");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void InsertTestCustomer() {
		String queryCustomer = "INSERT INTO customer(id,firstname,middlename,surname) VALUES (1,'Peter','de','Graaf')";
		try (Connection connection = DatabaseConnection.INSTANCE
				.getConnectionSQL();
				Statement statement = connection.createStatement()) {
			statement.execute(queryCustomer);
			LOG.trace("Customer inserted");
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}

	public static void InsertTestOrder() {
		Customer customer = new Customer(1, "Peter", "de", "Graaf");
		Order order = new Order(new BigDecimal("4.50"), LocalDateTime.now(),
				customer);
		String queryOrder = "INSERT INTO `order`(id, total_cost, customer_id, date) VALUES (?,?,?,?)";
		try (Connection connection = DatabaseConnection.INSTANCE
				.getConnectionSQL();
				PreparedStatement preparedStatement = connection
						.prepareStatement(queryOrder)) {
			preparedStatement.setInt(1, order.getId());
			preparedStatement.setBigDecimal(2, order.getTotalCost());
			preparedStatement.setInt(3, order.getCustomer().getId());
			preparedStatement.setObject(4, order.getDate());
			preparedStatement.executeUpdate();
			LOG.trace("Order inserted");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void InsertTestProduct() {
		String queryProduct = "INSERT INTO product(id,name,stock,price) VALUES (?,?,?,?)";
		Product product = new Product(1, "TestProductName", new BigDecimal(
				"4.50"), 10);
		try (Connection connection = DatabaseConnection.INSTANCE
				.getConnectionSQL();
				PreparedStatement preparedStatement = connection
						.prepareStatement(queryProduct)) {
			preparedStatement.setInt(1, product.getId());
			preparedStatement.setString(2, product.getName());
			preparedStatement.setInt(3, product.getStock());
			preparedStatement.setBigDecimal(4, product.getPrice());
			preparedStatement.executeUpdate();
			LOG.trace("Product inserted");
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}

	public static void InsertTestOrderLine() {
		String queryOrderLine = "INSERT INTO order_line(order_id,product_id,amount) VALUES (1,1,10)";
		try (Connection connection = DatabaseConnection.INSTANCE
				.getConnectionSQL();
				Statement statement = connection.createStatement()) {
			statement.execute(queryOrderLine);
			LOG.info("TestOrderLine inserted");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}