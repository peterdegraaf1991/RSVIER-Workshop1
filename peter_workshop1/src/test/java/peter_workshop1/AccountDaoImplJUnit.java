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

//@BeforeAll
//public void before() {
//		Customer customer = new Customer (1, "Peter","de","Graaf");
//		CustomerDao customerDaoImpl = new CustomerDaoImpl();
//		customerDaoImpl.createCustomer(customer);
//		
//		AccountType accountType = new AccountType (1, "testing");
//		AccountTypeDao accountTypeDaoImpl = new AccountTypeDaoImpl();
//		accountTypeDaoImpl.createAccountType(accountType);
//}

	@Test
	public void testCreateAccount() {	
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
//		Customer customer = new Customer (1, "Peter","de","Graaf");
		account.setCustomer (customer);
		
        assertNotNull("Email isn't null", account.getEmail());
        assertNotNull("Password isn't null",account.getPassword());
        assertNotNull("AccountTypeId isn't null", account.getPassword());
        assertNotNull("Customer isn't null", account.getCustomer());
		
		AccountDao accountDaoImpl = new AccountDaoImpl();
		int affectedRows = accountDaoImpl.createAccount(account);
		assertEquals("Equals?: ",1, affectedRows);
	}
		
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
		
		String query1 = "INSERT INTO account ( email,password,account_type_id,customer_id ) VALUES ('test1@hotmail.com','rsvier',1,1)";
//		String query2 = "INSERT INTO account (email = 'test2@hotmail.com' ,password = 'rsvier', account_type_id = 1)";
		statement.execute(query1);
//		statement.execute(query2);
		}
		catch (SQLException e) {
		e.printStackTrace();
	}
		
	AccountDao accountDaoImpl = new AccountDaoImpl();
	Account account1 = accountDaoImpl.readAccountByEmail("test1@hotmail.com");
	LOG.info(account1.toString());
	Customer customer = new Customer (1, "Peter","de","Graaf");
	Account account2 = new Account (customer,"test1@hotmail.com","rsvier",1);
	assertThat(account1,instanceOf(Account.class));
	assertEquals(account1, account2);
//	assertTrue(account.getPassword() == "rsvier");
//	assertTrue(account.getEmail() == "test1@hotmail.com");
//	assertTrue(account.getAccountTypeId() == 1);
	}
	
		
//		@AfterEach
//		public void after() {
//			try (Statement statement = DatabaseConnection.INSTANCE.getConnection().createStatement()) {
//				String query1 = "DELETE * FROM account";
//				String query2 = "DELETE * FROM customer";
//				statement.execute(query1);
//				statement.execute(query2);
//				} 
//			catch (SQLException e) {
//				e.printStackTrace();
//			}
		}

