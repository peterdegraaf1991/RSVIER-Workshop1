package peter_workshop1;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.List;

import model_class.Account;
import model_class.Customer;
import model_class.OrderLine;
import model_class.Product;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dao.OrderLineDao;
import dao.OrderLineDaoImpl;
import utility.DatabaseConnection;

public class OrderLineDaoImplJunit {

private static final Logger LOG = LoggerFactory.getLogger(OrderLineDaoImplJunit.class);

OrderLineDao orderLineDaoImpl = new OrderLineDaoImpl();
/*
public void createOrderLine(OrderLine orderLine);
public OrderLine readOrderLineById (int id);
public List <OrderLine> readOrderLinesOfOrderId (int id);
public List <OrderLine> readAllOrderLines ();
public void updateOrderLine(OrderLine orderLine);
public void deleteOrderLine(int id); 
}
*/

	@Test
	public void before() {
		try (Connection connection = DatabaseConnection.INSTANCE.getConnection(); Statement statement = connection.createStatement()) {
			String query1 = "DELETE FROM order_line";
			String query2 = "DELETE FROM order";
//			String query3 = "DELETE FROM account_type";
			String query4 = "ALTER TABLE orderline AUTO_INCREMENT = 1";
			String query5 = "ALTER TABLE order AUTO_INCREMENT = 1";
//			String query6 = "ALTER TABLE account_type AUTO_INCREMENT = 1";
			statement.addBatch(query1);
			statement.addBatch(query2);
//			statement.addBatch(query3);
			statement.addBatch(query4);
			statement.addBatch(query5);
//			statement.addBatch(query6);
			statement.executeBatch();
			LOG.info("orderline info deleted, reset to autoincrement 1");
			} 
		catch (SQLException e) {
			e.printStackTrace();
		}
	
		// Inserting Order(id=1)
		BigDecimal total_cost = new BigDecimal("3,99");
		LocalDateTime now = LocalDateTime.now();
		// bigdecimal and localdatetime in query?
		String queryOrder = "INSERT INTO order(id, total_cost, customer_id, date) VALUES (1,total_cost,1,now)";
				
				
//				"INSERT INTO order(id,total_cost,customer_id,date)" VALUES (1,new BigDecimal("3,99"),1,2)";
		try (Connection connection = DatabaseConnection.INSTANCE.getConnection(); Statement statement = connection.createStatement()) {
		statement.execute(queryOrder);
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
}
/*		String queryAccountType = "INSERT INTO account_type (id, description) VALUES (1, 'testing')";
		String queryAccount = "INSERT INTO account(email,password,customer_id,account_type_id) VALUES ('test3@hotmail.com','rsvier',1,1)";
		statement.execute(queryCustomer);
		statement.execute(queryAccountType);
		statement.execute(queryAccount);
		LOG.info("testcustomer(id=1),accounttype(id=1) & account inserted");
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
}
}
*/

	@Test
	public void testCreateOrderLine() {	
		OrderLine orderline = new OrderLine();		
		orderline.setAmount(3);
		orderline.setOrderId(1);
		Product product = new Product ("TestOrderName", new BigDecimal("5.10"),15);
		orderline.setProduct (product);
	    	
        assertNotNull("Amount isn't null", orderline.getAmount());
        assertNotNull("Product isn't null",orderline.getProduct());

		int affectedRows = orderLineDaoImpl.createOrderLine(orderline);
		assertEquals("Equals?: ",1, affectedRows);
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