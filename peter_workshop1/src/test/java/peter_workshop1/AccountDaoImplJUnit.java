package peter_workshop1;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import model_class.*;
import dao.*;
import utility.DatabaseConnection;


public class AccountDaoImplJUnit {

private static final Logger LOG = LoggerFactory.getLogger(AccountDaoImplJUnit.class);

	@Test
	public void testCreateAccount() {	
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
		
	@Test
	public void testUpdateAccount() {
	}
	
	@Test
	public void testDeleteAccount(){
	}
	
	@Test
	public void testReadAccount(){
	}
	
		@BeforeEach
		public void before() {
			try (Statement statement = DatabaseConnection.INSTANCE.getConnection().createStatement()) {
			Customer customer = new Customer (1, "Peter","de","Graaf");
			CustomerDao customerDaoImpl = new CustomerDaoImpl();
			customerDaoImpl.createCustomer(customer);
			
			AccountType accountType = new AccountType (1, "testing");
			AccountTypeDao accountTypeDaoImpl = new AccountTypeDaoImpl();
			accountTypeDaoImpl.createAccountType(accountType);
			}
			catch (SQLException e) {
			e.printStackTrace();
		}
		}
		@AfterEach
		public void after() {
			try (Statement statement = DatabaseConnection.INSTANCE.getConnection().createStatement()) {
				String query1 = "DELETE * FROM account";
				String query2 = "DELETE * FROM customer";
				statement.execute(query1);
				statement.execute(query2);
				} 
			catch (SQLException e) {
				e.printStackTrace();
			}
		}

}