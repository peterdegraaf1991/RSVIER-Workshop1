package peter_workshop1;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;

import model_class.Customer;
import model_class.Order;
import model_class.OrderLine;
import model_class.Product;



import org.junit.Before;
import org.junit.FixMethodOrder;
// import org.junit.jupiter.api.Test;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dao.OrderLineDao;
import dao.OrderLineDaoImpl;
import utility.DatabaseConnection;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class OrderLineDaoImplJunit {

private static final Logger LOG = LoggerFactory.getLogger(OrderLineDaoImplJunit.class);

OrderLineDao orderLineDaoImpl = new OrderLineDaoImpl();

	@Test
	public void ACleaningDb() {
		LOG.info("entering before()");
		try (Connection connection = DatabaseConnection.INSTANCE.getConnection(); Statement statement = connection.createStatement()) {
			String query1 = "DELETE FROM order_line";
			String query2 = "DELETE FROM customer";
			// why do i need ` for order, while not for the other queries?
			String query3 = "DELETE FROM `order`";
			String query4 = "ALTER TABLE order_line AUTO_INCREMENT = 1";
			String query5 = "ALTER TABLE customer AUTO_INCREMENT = 1";
			String query6 = "ALTER TABLE `order` AUTO_INCREMENT = 1";
			
			LOG.info("prepare for queries...");
			statement.execute(query1);
			LOG.info("query 1 succes");
			statement.execute(query2);
			LOG.info("query 2 succes");
			statement.execute(query3);
			LOG.info("query 3 succes");
			statement.execute(query4);
			LOG.info("query 4 succes");
			statement.execute(query5);
			LOG.info("query 5 succes");
			statement.execute(query6);
			LOG.info("query 6 succes");
//			statement.executeBatch();
			LOG.info("orderline, order, customer info deleted, reset to autoincrement 1");
			} 
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	@Test
	public void BInsertTestCustomer() {
		LOG.info("trying to insert customer(id=1");
		String queryCustomer = "INSERT INTO customer(id,firstname,middlename,surname) VALUES (1,'Peter','de','Graaf')";
		try (Connection connection = DatabaseConnection.INSTANCE.getConnection(); Statement statement = connection.createStatement()){
	    statement.execute(queryCustomer);
	    LOG.info("Customer (id=1) inserted");
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
}
	@Test
	public void CInsertingTestOrder(){
		boolean succes = false;
		LOG.info("trying to create Order (id=1)...0");
		Customer customer = new Customer (1,"Peter", "de", "Graaf");
		LOG.info("trying to create Order (id=1)...1");
		Order order = new Order(new BigDecimal ("4.50"), LocalDateTime.now(), customer);
		LOG.info("trying to create Order (id=1)...2");
		String queryOrder = "INSERT INTO `order`(id, total_cost, customer_id, date) VALUES (?,?,?,?)";
		LOG.info("trying to create Order (id=1)...3 ");

		try (Connection connection = DatabaseConnection.INSTANCE.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(queryOrder)) {
			LOG.info("trying to create Order (id=1)...4");
			 preparedStatement.setInt(1, order.getId()); 
				LOG.info("trying to create Order (id=1)...5");
	         preparedStatement.setBigDecimal(2, order.getTotalCost());
	 		LOG.info("trying to create Order (id=1)...6");
	         preparedStatement.setTimestamp(3, Timestamp.from(order.getDate().atZone(ZoneId.of("Europe/Amsterdam")).toInstant()));
	 		LOG.info("trying to create Order (id=1)...7");
	         preparedStatement.setInt(4, order.getCustomer().getId()); 
	 		LOG.info("trying to create Order (id=1)...8");
	         preparedStatement.executeUpdate(); 
	 		LOG.info("trying to create Order (id=1)...9");
	 		LOG.info("Order (id=1) created");
	 		succes = true;
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
 		assertTrue(succes);
		LOG.info("exiting before()");
}
	
	@Test
	public void testCreateOrderLine() {	
		LOG.info("entering testCreateOrderLine()...");
		OrderLine orderline = new OrderLine();		
		orderline.setAmount(3);
		orderline.setOrderId(1);
		Product product = new Product ("TestOrderName", new BigDecimal("5.10"),15);
		orderline.setProduct (product);

        assertNotNull("Amount isn't null", orderline.getAmount());
        assertNotNull("Product isn't null",orderline.getProduct());

		int affectedRows = orderLineDaoImpl.createOrderLine(orderline);
		assertEquals("Equals?: ",1, affectedRows);
		LOG.info("entering testCreateOrderLine()...5");
	}
}
	/*
	@Test
	public void testUpdateOrderline() {
		Account account = accountDaoImpl.readAccountByEmail("test3@hotmail.com");
		account.setPassword("PasswordGewijzigd");
		accountDaoImpl.updateAccount(account);
		try (Connection connection = DatabaseConnection.INSTANCE.getConnection(); Statement statement = connection.createStatement()) {
		String query = "SELECT * FROM account WHERE email = 'test3@hotmail.com'";
		ResultSet rs = statement.executeQuery(query);
		rs.next();
		assertEquals(rs.getString("password"),"PasswordGewijzigd");
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testReadOrderLineById(){
		LOG.info("Entering testReadAccountById()...");
		Account readAccount = accountDaoImpl.readAccountById(1);
		LOG.info("ReadingAccount1: " + readAccount.toString());
		Customer customer = new Customer (1, "Peter","de","Graaf");
		Account insertedAccount = new Account (customer,"test3@hotmail.com","PasswordGewijzigd",1);
		LOG.info("InsertedAccount: " + insertedAccount.toString());
		assertThat(readAccount,instanceOf(Account.class));
		assertEquals(readAccount,insertedAccount);
		Boolean equalStatus = readAccount.equals(insertedAccount);
		LOG.info("Inserted account equals read Account: " + equalStatus);
		}
	
	public void testReadOrderLinesOfOrderId(){
	}
	
	public void testReadAllOrderLines () {
	}
	
	
	
	@Test
	public void testDeleteOrderLine(){
			assertEquals(accountDaoImpl.deleteAccount(2), 1);
			LOG.info("1 Account deleted = true asserted");
			try (Connection connection = DatabaseConnection.INSTANCE.getConnection(); Statement statement = connection.createStatement()) {
			String query = "SELECT * FROM account WHERE id = 2";
			ResultSet rs = statement.executeQuery(query);
			assertFalse(rs.next());
			LOG.info("AccountId not found in database after deletion asserted");
			} 
	}
	}
*/