package dao;

import model_class.AccountType;

public interface AccountTypeDao {

	public void createAccountType(AccountType accountType);

	public void readAccountType(int id);
	
	public void updateAccountType(AccountType accounttype);
	
	public void deleteAccountType(int accounttype);

//	public void printAccountType();
//	public void printAccountType(int id);
}
