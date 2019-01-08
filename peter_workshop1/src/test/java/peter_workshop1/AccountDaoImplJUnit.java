package peter_workshop1;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import model_class.*;
import dao.*;
import utility.DatabaseConnection;


public class AccountDaoImplJUnit {

private static final Logger LOG = LoggerFactory
		.getLogger(AccountDaoImplJUnit.class);


@Test
public void before() {

		try (Connection connection = DatabaseConnection.INSTANCE.getConnection(); Statement statement = connection.createStatement()) {
			String query1 = "DELETE FROM account";
			String query2 = "DELETE FROM customer";
			String query3 = "DELETE FROM account_type";
			String query4 = "ALTER TABLE account AUTO_INCREMENT = 1";
			String query5 = "ALTER TABLE customer AUTO_INCREMENT = 1";
			String query6 = "ALTER TABLE account_type AUTO_INCREMENT = 1";
			statement.addBatch(query1);
			statement.addBatch(query2);
			statement.addBatch(query3);
			statement.addBatch(query4);
			statement.addBatch(query5);
			statement.addBatch(query6);
			statement.executeBatch();
			LOG.info("account,customer,account_type info deleted, reset to autoincrement 1");
			} 
		catch (SQLException e) {
			e.printStackTrace();
		}

		try (Connection connection = DatabaseConnection.INSTANCE.getConnection(); Statement statement = connection.createStatement()) {
		String queryCustomer = "INSERT INTO customer(id,firstname,middlename,surname) VALUES (1,'Peter','de','Graaf')";
		String queryAccountType = "INSERT INTO account_type (id, description) VALUES (1, 'testing')";
		String queryAccount = "INSERT INTO account(email,password,customer_id,account_type_id) VALUES ('test3@hotmail.com','rsvier',1,1)";
		statement.execute(queryCustomer);
		statement.execute(queryAccountType);
		statement.execute(queryAccount);
		LOG.info("testcustomer,accounttype & account inserted");
		} catch (SQLException e) {
			e.printStackTrace();
		}
}

/*	@Test
	
	public void testCreateAccount() {	
		try (Statement statement = DatabaseConnection.INSTANCE.getConnection().createStatement()) {
			String query1 = "DELETE FROM account";
			String query2 = "DELETE FROM customer";
			statement.execute(query1);
			statement.execute(query2);
			} 
		catch (SQLException e) {
			e.printStackTrace();
		}
	
		Customer customer = new Customer (1, "Peter","de","Graaf");
		CustomerDao customerDaoImpl = new CustomerDaoImpl();
		customerDaoImpl.createCustomer(customer);
		
		AccountType accountType = new AccountType (1, "testing");
		AccountTypeDao accountTypeDaoImpl = new AccountTypeDaoImpl();
		accountTypeDaoImpl.createAccountType(accountType);
		
		Account account = new Account();
		account.setEmail("peterdegraaf1991@hotmail.com");
		account.setPassword("rsvier");
		account.setAccountTypeId(1);
		Customer customer = new Customer (1, "Peter","de","Graaf");
		account.setCustomer (customer);
		
        assertNotNull("Email isn't null", account.getEmail());
        assertNotNull("Password isn't null",account.getPassword());
        assertNotNull("AccountTypeId isn't null", account.getPassword());
        assertNotNull("Customer isn't null", account.getCustomer());
		
		AccountDao accountDaoImpl = new AccountDaoImpl();
		int affectedRows = accountDaoImpl.createAccount(account);
		assertEquals("Equals?: ",1, affectedRows);
	}
*/		
	@Test
	public void testUpdateAccount() {
	}
	
	@Test
	public void testDeleteAccount(){
	}
	
	@Test
	public void testReadAccount(){
		try (Connection connection = DatabaseConnection.INSTANCE.getConnection(); Statement statement = connection.createStatement()) {
//		Account account = new Account (1, "Peter","de","Graaf");
//		CustomerDao customerDaoImpl = new CustomerDaoImpl();
//		customerDaoImpl.createCustomer(customer);
		
//		String query1 = "INSERT INTO account ('email', 'password', 'account_type_id', `customer_id`) VALUES ('test1@hotmail.com','rsvier',1,1)";
//		String query2 = "INSERT INTO account (email = 'test2@hotmail.com' ,password = 'rsvier', account_type_id = 1)";
//		statement.execute(query1);
//		statement.execute(query2);
		}
		catch (SQLException e) {
		e.printStackTrace();
	}
	LOG.info("Entering testReadAccount()...");
	AccountDao accountDaoImpl = new AccountDaoImpl();
	Account account1 = accountDaoImpl.readAccountByEmail("test3@hotmail.com");
	LOG.info("ReadingAccount1 = " + account1.toString());
	Customer customer = new Customer (1, "Peter","de","Graaf");
	Account account2 = new Account (customer,"test3@hotmail.com","rsvier",1);
	LOG.info("InputAccount2 = " + account2.toString());
	assertThat(account1,instanceOf(Account.class));
	assertEquals(account1,account2);
	Boolean equalStatus = account1.equals(account2);
	LOG.info("equalStatus: " + equalStatus);
//	assertTrue(account.getPassword() == "rsvier");
//	assertTrue(account.getEmail() == "test1@hotmail.com");
//	assertTrue(account.getAccountTypeId() == 1);
	}
/*	
	@BeforeAll
	public void beforeAll() {
		try (Statement statement = DatabaseConnection.INSTANCE.getConnection().createStatement()) {
			String query1 = "DELETE FROM account";
			String query2 = "DELETE FROM customer";
			statement.execute(query1);
			statement.execute(query2);
			} 
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
		@AfterEach
		public void after() {
			try (Statement statement = DatabaseConnection.INSTANCE.getConnection().createStatement()) {
				String query1 = "DELETE FROM account";
				String query2 = "DELETE FROM customer";
				statement.execute(query1);
				statement.execute(query2);
				} 
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
		*/
}
