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
	public void testCreateAccount() {;
		Account account = new Account();
		account.setEmail("peterdegraaf1991@hotmail.com");
		account.setPassword("rsvier");
		account.setAccountTypeId(1);
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
				String query1 = "INSERT INTO customer (id = 5, firstname = Peter, middlename = de, surname = Graaf";
				String query2 = "INSERT INTO account email = peterdegraaf1991@hotmail.com, password = rsvier, account_type = 1, customer_id = 5";
				statement.execute(query1);
				statement.execute(query2);
				} 
			catch (SQLException e) {
			e.printStackTrace();
		}
		}
		
/*		@AfterEach
		public void after() {
			try (Statement statement = DatabaseConnection.INSTANCE.getConnection().createStatement()) {
				String query1 = "DELETE FROM account";
				statement.execute(query1);
				} 
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
*/
}