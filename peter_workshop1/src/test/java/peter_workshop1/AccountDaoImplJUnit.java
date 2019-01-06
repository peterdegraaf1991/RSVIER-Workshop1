package peter_workshop1;

import static org.junit.Assert.*;

import java.sql.PreparedStatement;
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
	public void testCreateAccount() {;
		Account account = new Account();
		account.setEmail("peterdegraaf1991@hotmail.com");
		account.setPassword("rsvier");
		account.setAccountType(1);
		account.getCustomer().setId(1);
		AccountDao accountDaoImpl = new AccountDaoImpl();
		int affectedRows = accountDaoImpl.createAccount(account);
		assertEquals("Equals?: ",3, affectedRows);
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
				String query1 = "INSERT INTO account (email, password,VALUES (account2@hotmail.com,rsvier1,1,1)";
				String query2 = "INSERT INTO account email = account2@hotmail.com, password = rsvier1,account_type = 2, customer_id = 2";
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
				statement.execute(query1);
				} 
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
}