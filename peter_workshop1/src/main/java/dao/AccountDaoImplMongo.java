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
		DBObject doc = createDBObject(account);
		
		
		
		String query = "INSERT INTO account (email, account_type_id, customer_id, hash) VALUES(?, ?, ?, ?)";
		try (Connection connection = DatabaseConnection.INSTANCE
				.getConnectionSQL();
				PreparedStatement preparedStatement = connection
						.prepareStatement(query,
								PreparedStatement.RETURN_GENERATED_KEYS)) {

			preparedStatement.setString(1, account.getEmail());
//			preparedStatement.setString(2, account.getPassword());
			preparedStatement.setInt(2, account.getAccountTypeId());
			preparedStatement.setInt(3, account.getCustomer().getId());
			preparedStatement.setString(4, account.getHash());

		} catch (SQLException e) {
			e.printStackTrace();

		}
		return affectedRows;
	}



	// getNextSequence() return the generatedId as a Double object. Have to cast
	// object to double
	double generatedIdDouble = (Double) getNextSequence("account_id");
	// Cast double to int. Then you generatedId as int
	int generatedIdInteger = (int) generatedIdDouble;



	try {
		collection.insert(newAccount);
		logger.log(Level.INFO, "Account successfully created.");

	} catch (MongoException e) {
		logger.log(Level.WARNING, "SQL exception occured", e);

	}

	return generatedIdInteger;
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
