package main;
import controller.LoginController;

public class Runner {

	public static void main(String[] args) {
		LoginController loginController = new LoginController();
		loginController.runController();
	}
}
