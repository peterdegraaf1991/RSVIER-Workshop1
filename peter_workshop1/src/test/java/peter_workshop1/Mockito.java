package peter_workshop1;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;
import model_class.Account;
import model_class.Customer;

import org.junit.Test;

import controller.AccountController;
import controller.Controller;
import controller.CustomerController;
import controller.LoginController;
import dao.AccountDao;
import dao.AccountDaoImpl;

public class Mockito {

	@Test
	public void createAccount()  {
	        //  create mocks
	        AccountController mockAccountController = mock(AccountController.class);
	        Controller mockController = mock (Controller.class);
	        CustomerController mockCustomerController = mock(CustomerController.class);
	        AccountDao mockAccountDao = mock(AccountDaoImpl.class);
	        LoginController mockLoginController = mock(LoginController.class);
	       
	        // when statements
	        when(mockController.workerOrAdminPermission()).thenReturn(true);
	        
	        Customer customer = new Customer();
	        customer.setId(111);
	        when(mockCustomerController.ChoosePersonFromList()).thenReturn(customer);
	        
	        Account account = new Account();
	        account.setEmail("test@hotmail.com");
	        when(mockAccountDao.readAccountByCustomerId(customer.getId())).thenReturn(account);
	        when(mockAccountDao.deleteAccount(account.getId())).thenReturn(1);
	        
	        assertEquals(account, )
	}
}
