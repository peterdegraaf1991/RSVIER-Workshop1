package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mongodb.BasicDBObject;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoException;

import utility.DatabaseConnection;
import model_class.Account;

public class AccountDaoImplMongo implements AccountDao {
	
	private static final Logger LOG = LoggerFactory
			.getLogger(AccountDaoImplMongo.class);
	
	
	private static DBObject createDBObject(Account account) {
		BasicDBObjectBuilder docBuilder = BasicDBObjectBuilder.start();
								
		docBuilder.append("_id", account.getId());
		docBuilder.append("email", account.getEmail());
		docBuilder.append("account_type_id", account.getAccountTypeId());
		docBuilder.append("hash", account.getHash());
		docBuilder.append("customer_id", account.getCustomer().getId());
		return docBuilder.get();
	}
	
	@Override
	public int createAccount(Account account) {
		DB db = DatabaseConnection.INSTANCE.getConnectionMongo();
		DBCollection collection = db.getCollection("account");
		createDBObject(account);
		// probably have to close connection somewhere
		mongo.close();

		// returning 1 for affected rows
		return 1;
	}

	
	@Override
	public Account readAccountByCustomerId(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Account readAccountById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Account readAccountByEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Account> readAllAccounts() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateAccount(Account account) {
		// TODO Auto-generated method stub

	}

	@Override
	public int deleteAccount(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String readHash(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
