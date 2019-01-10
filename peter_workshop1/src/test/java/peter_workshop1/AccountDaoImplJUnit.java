package peter_workshop1;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.junit.Before;
// import org.junit.jupiter.Test;
// 5 org.junit.jupiter.Test doesnt import
import org.junit.Test;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.BeforeEach;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import model_class.*;
import dao.*;
import utility.DatabaseConnection;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AccountDaoImplJUnit {

private static final Logger LOG = LoggerFactory.getLogger(AccountDaoImplJUnit.class);
AccountDao accountDaoImpl = new AccountDaoImpl();

/// Leters voor testnaam zodat ze in volgorde worden uitgevoerd.
@Test
public void A_CleanUp() {
DatabaseInit.DeleteRows();
DatabaseInit.ResetAutoIncement();
}

@Test
public void B_InsertCustomer_Account_AccountType() {
		// Inserting Customer(id=1), AccountType(id=1), and Account(email=test3@hotmail.com)
		try (Connection connection = DatabaseConnection.INSTANCE.getConnection(); Statement statement = connection.createStatement()) {
		String queryCustomer = "INSERT INTO customer(id,firstname,middlename,surname) VALUES (1,'Peter','de','Graaf')";
		String queryAccountType = "INSERT INTO account_type (id, description) VALUES (1, 'testing')";
		String queryAccount = "INSERT INTO account(email,password,customer_id,account_type_id) VALUES ('test3@hotmail.com','rsvier',1,1)";
		statement.execute(queryCustomer);
		statement.execute(queryAccountType);
		statement.execute(queryAccount);
		LOG.info("testcustomer(id=1),accounttype(id=1) & account inserted");
		} catch (SQLException e) {
			e.printStackTrace();
		}
}

	@Test
	public void C_testCreateAccount() {	
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
		
		int affectedRows = accountDaoImpl.createAccount(account);
		assertEquals("Equals?: ",1, affectedRows);
	}

	@Test
	public void D_testUpdateAccount() {
		Account accountRead = accountDaoImpl.readAccountByEmail("test3@hotmail.com");
		accountRead.setPassword("PasswordGewijzigd");
		accountDaoImpl.updateAccount(accountRead);
		try (Connection connection = DatabaseConnection.INSTANCE.getConnection(); Statement statement = connection.createStatement()) {
		String query = "SELECT * FROM account WHERE email = 'test3@hotmail.com'";
		ResultSet rs = statement.executeQuery(query);
		rs.first();
		assertEquals(rs.getString("password"),"PasswordGewijzigd");
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// Why does this test return AccountId=0 the first time it runs (notEqual) and AccountId=1 the runs after that (equals).
	@Test
	public void E_testReadAccountByEmail(){
	LOG.info("Entering testReadAccountByEmail()...");
	Account readAccount = accountDaoImpl.readAccountByEmail("test3@hotmail.com");
	LOG.info("ReadingAccount1: " + readAccount.toString());
	Customer customer = new Customer (1, "Peter","de","Graaf");
	Account insertedAccount = new Account (customer,"test3@hotmail.com","PasswordGewijzigd",1);
	LOG.info("password in Account =" + insertedAccount.getPassword());
	LOG.info("InsertedAccount: " + insertedAccount.toString());
	assertThat(readAccount,instanceOf(Account.class));
	assertEquals(insertedAccount,readAccount);
	Boolean equalStatus = insertedAccount.equals(readAccount);
	LOG.info("Inserted account equals read Account: " + equalStatus);
	}

	@Test
	public void F_testReadAccountById(){
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


	@Test
	public void G_testDeleteAccount(){
	assertEquals(1, accountDaoImpl.deleteAccount(2));
	LOG.info("1 Account deleted = true asserted");
	try (Connection connection = DatabaseConnection.INSTANCE.getConnection(); Statement statement = connection.createStatement()) {
	String query = "SELECT * FROM account WHERE id = 2";
	ResultSet rs = statement.executeQuery(query);
	assertFalse(rs.next());
	LOG.info("AccountId not found in database after deletion asserted");
	} 
	
	catch (SQLException e) {
		e.printStackTrace();
	}
	}
}
