package peter_workshop1;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.math.BigDecimal;

import model_class.OrderLine;
import model_class.Product;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dao.OrderLineDao;
import dao.OrderLineDaoImpl;

public class OrderLineDaoImplJunit {
private static final Logger LOG = LoggerFactory.getLogger(OrderLineDaoImplJunit.class);

OrderLineDao orderLineDaoImpl = new OrderLineDaoImpl();

	@Before
		public void CleanUp() {
			DatabaseInit.DeleteRows();
			DatabaseInit.ResetAutoIncement();
			DatabaseInit.InsertTestCustomer();
			DatabaseInit.InsertTestOrder();
			DatabaseInit.InsertTestProduct();
			}
	
	@Test
	public void CreateOrderLine() {	
		LOG.info("entering testCreateOrderLine()...");
		OrderLine orderline = new OrderLine();		
		orderline.setAmount(3);
		orderline.setOrderId(1);
		Product product = new Product (1,"TestProductName", new BigDecimal("5.10"),15);
		orderline.setProduct (product);

        assertNotNull("Amount isn't null", orderline.getAmount());
        assertNotNull("Product isn't null",orderline.getProduct());
        assertNotNull("OrderId isn;t null", orderline.getOrderId());

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