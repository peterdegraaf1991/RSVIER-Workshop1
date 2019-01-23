package view;

import java.util.concurrent.TimeUnit;

public class MainView extends View {

	@Override
	public void PrintMenuHeader() {
		terminal.println("Main Menu\n");

	}

	@Override
	public void PrintMenuOptions() {
		terminal.println("1. Account Management");
		terminal.println("2. Orders");
		terminal.println("3. Products");
		terminal.println("4. Customer");
		terminal.println("9. Logout");
		terminal.println("0. Not Implemented");
	}

	public void logoutTimer() {
		try {
			TimeUnit.SECONDS.sleep(1);
			terminal.println("Logging out in 5");
			TimeUnit.SECONDS.sleep(1);
			terminal.println("Logging out in 4");
			TimeUnit.SECONDS.sleep(1);
			terminal.println("Logging out in 3");
			TimeUnit.SECONDS.sleep(1);
			terminal.println("Logging out in 2");
			TimeUnit.SECONDS.sleep(1);
			terminal.println("Logging out in 1");
			TimeUnit.SECONDS.sleep(1);

		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}
}
