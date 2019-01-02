package dao;

import model_class.Account;
	
	public interface AccountDao {

		public int createAccount(Account account);

		public void readAccount(int id);
		
		public void updateAccount(Account account);
	
		public void deleteAccount(int id);

		
//		public void printAccount();
//		public void printAccount(String email);
		
	}
