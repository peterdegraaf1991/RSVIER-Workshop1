package main;
import controller.DatabaseController;
import controller.LoginController;

public class Runner extends DatabaseController{

	public static void main(String[] args) {
		ClearDatabase();
		InitDatabase();
		
		LoginController loginController = new LoginController();
		loginController.runController();
	}
}
