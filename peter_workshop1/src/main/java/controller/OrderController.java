package controller;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import model_class.Customer;
import model_class.Order;
import model_class.OrderLine;
import model_class.Product;
import dao.CustomerDaoImpl;
import dao.OrderDao;
import dao.OrderDaoImpl;
import dao.OrderLineDaoImpl;
import dao.ProductDaoImpl;
import view.CustomerView;
import view.OrderLineView;
import view.OrderView;

public class OrderController extends Controller {

	OrderDao orderDaoImpl = new OrderDaoImpl();
	CustomerDaoImpl customerDaoImpl = new CustomerDaoImpl();
	OrderLineDaoImpl orderLineDaoImpl = new OrderLineDaoImpl();
	ProductDaoImpl productDaoImpl = new ProductDaoImpl();

	CustomerController customerController = new CustomerController();
	ProductController productController = new ProductController();
	OrderLineController orderLineController = new OrderLineController();

	OrderView orderView = new OrderView();
	CustomerView customerView = new CustomerView();
	OrderLineView orderLineView = new OrderLineView();

	@Override
	public void runController() {
		int keuze = 1;
		Controller.newView = true;
		do {
			if (Controller.newView == true) {
				orderView.ClearTerminal();
				orderView.PrintMenuHeader();
				orderView.PrintMenuOptions();
				Controller.newView = false;
			}

			keuze = orderView.RequestMenuOption();
			switch (keuze) {
			case 1:
				createOrder();
				requestNewMenu();
				break;
			case 2: ViewAllOrders();
					requestNewMenu();
					break;
			case 3:
				Order order = selectOrderFromCustomer(selectCustomersWithOrder());
				if (order == null) {
					break;
				}
				editOrderMenu(order);
				break;
			case 9:
				keuze = 0;
				Controller.newView = true;
				break;
			
			case 0:
				orderView.logoutTimer();
				System.exit(0);
				break;
			default:
				orderView.InvalidInput();
				break;
			}
		} while (keuze != 0);
	}

	private void ViewAllOrders() {
		if (workerOrAdminPermission() == false)
			orderView.noPermission();
		List<Order> list = orderDaoImpl.readAllOrders();
		if (list.size() <= 0) {
			orderView.noOrdersFound();
			return;
		}
		for (int i = 0; i < list.size(); i++) {
			String customerName = customerDaoImpl.readCustomerById(list.get(i).getId()).toString();
			orderView.printString(i + ". " + "name:" + customerName + list.get(i).toString());
		}
	}
		

	public void editOrderMenu(Order order) {
		int keuze = 1;
		Controller.newView = true;
		do {
			if (Controller.newView == true) {
				orderView.ClearTerminal();
				orderView.PrintEditMenuHeader();
				orderView.PrintEditMenuOptions();
				Controller.newView = false;
			}

			keuze = orderView.RequestMenuOption();
			switch (keuze) {
			case 1:
				changeProducts(order);
				keuze = 0;
				requestNewMenu();
				break;
			case 2:
				if (workerOrAdminPermission() == true) {
					changeTotalCost(order);
					keuze = 0;
					Controller.newView = true;
				} else
					orderView.noPermission();
				requestNewMenu();
				break;
			case 3:
				deleteOrder(order);
				keuze = 0;
				requestNewMenu();
				break;

			case 9:
				keuze = 0;
				Controller.newView = true;
				break;
				
			case 0:
				orderView.logoutTimer();
				System.exit(0);
				break;
			default:
				orderView.InvalidInput();
				break;
			}
		} while (keuze != 0);
	}

	private void deleteOrder(Order order) {
		if (order != null) {
			orderDaoImpl.deleteOrder(order.getId());
			// add amount back to stock has to be added here
			orderView.orderSuccesfullyDeleted();
		}
	}

	public void createOrder() {
		Customer customer = customerController.selectCustomer();
		int orderId = createNewOrderId(customer);
		List<OrderLine> orderLineList = orderLineController
				.createOrderLines(orderId);
		BigDecimal totalCost = calculateTotalCost(orderLineList);
		productController.updateStock(orderLineList);
		Order order = orderDaoImpl.readOrderById(orderId);
		order.setTotalCost(totalCost);
		order.setDate(LocalDateTime.now());
		orderDaoImpl.updateOrder(order);
	}

	public int createNewOrderId(Customer customer) {
		Order order = new Order();
		order.setCustomer(customer);
		order.setDate(LocalDateTime.now());
		order.setTotalCost(new BigDecimal(0));
		int generatedId = orderDaoImpl.createOrder(order);
		return generatedId;
	}

	public BigDecimal calculateTotalCost(List<OrderLine> list) {
		BigDecimal totalCost = new BigDecimal(0);
		for (int i = 0; i < list.size(); i++) {
			int productId = list.get(i).getProduct().getId();
			Product product = productDaoImpl.readProductById(productId);
			totalCost = totalCost.add(product.getPrice().multiply(
					new BigDecimal(list.get(i).getAmount())));
		}
		return totalCost;
	}

	private Customer selectCustomersWithOrder() {
		List<Integer> customerIdList = orderDaoImpl.readCustomerIdsWithOrder();
		if (workerOrAdminPermission() == false) {
			if (customerIdList.contains(LoginController.loggedInCustomer
					.getId()) == true)
				return LoginController.loggedInCustomer;
			else
				return null;
		}
		if (customerIdList.isEmpty()) {
			orderView.noOrdersFound();
			return null;
		}
		List<Customer> customerList = new ArrayList<>();
		for (int i = 0; i < customerIdList.size(); i++) {
			Customer customer = customerDaoImpl.readCustomerById(customerIdList
					.get(i));
			customerList.add(customer);
		}

		for (int i = 0; i < customerList.size(); i++) {
			orderView.printCustomerNamesWithOrder(i + ". "
					+ customerList.get(i).toString());
		}
		int option = customerView.ChoosePerson(customerIdList.size());
		return customerList.get(option);
	}

	private Order selectOrderFromCustomer(Customer customer) {
		if (customer == null) {
			return null;
		}
		List<Order> orderList = orderDaoImpl.readOrdersOfCustomerId(customer
				.getId());

		for (int i = 0; i < orderList.size(); i++) {
			orderView.printOrders(i + ". " + orderList.get(i));
		}
		int option = orderView.chooseOrder(orderList.size());
		return orderList.get(option);
	}

	private void changeTotalCost(Order order) {
		BigDecimal newTotalCost = orderView.requestTotalCost();
		order.setTotalCost(newTotalCost);
		orderDaoImpl.updateOrder(order);
		orderView.TotalCostUpdated();
	}

	private void updateTotalCostOfOrder(Order order) {
		List<OrderLine> listOrderLines = orderLineDaoImpl
				.readOrderLinesOfOrderId(order.getId());
		BigDecimal newTotalCost = calculateTotalCost(listOrderLines);
		order.setTotalCost(newTotalCost);
		orderDaoImpl.updateOrder(order);
	}

	private void changeProducts(Order order) {
		OrderLine orderLine = selectOrderLineFromOrder(order);
		int oldProductId = orderLine.getProduct().getId();
		int oldAmount = orderLine.getAmount();
		Product oldProduct = productDaoImpl.readProductById(oldProductId);
		Product newProduct = productController.SelectProductFromList();
		int newProductCurrentStock = newProduct.getStock();

		// Update Stocks:
		if (oldProduct.equals(newProduct) == false) {
			int newProductAmount = orderLineView.requestAmount(newProduct
					.getStock());
			orderLine.setAmount(newProductAmount);
			int oldProductStock = oldProduct.getStock();
			oldProduct.setStock(oldProductStock + oldAmount);
			productDaoImpl.updateProduct(oldProduct);
			newProduct.setStock(newProductCurrentStock - newProductAmount);
			productDaoImpl.updateProduct(newProduct);
		}
		if (oldProduct.equals(newProduct) == true) {
			int newProductAmount = orderLineView.requestAmount(oldProduct
					.getStock() + oldAmount);
			orderLine.setAmount(newProductAmount);
			newProduct.setStock(newProductCurrentStock + (oldAmount - newProductAmount));
			productDaoImpl.updateProduct(newProduct);
		}
		orderLine.setProduct(newProduct);
		orderLineDaoImpl.updateOrderLine(orderLine);
		updateTotalCostOfOrder(order);
	}

	// OrderLine Controller
	private OrderLine selectOrderLineFromOrder(Order order) {
		List<OrderLine> listOrderLines = orderLineDaoImpl
				.readOrderLinesOfOrderId(order.getId());
		for (int i = 0; i < listOrderLines.size(); i++) {
			int productId = listOrderLines.get(i).getProduct().getId();
			String productName = productDaoImpl.readProductById(productId)
					.getName();
			
			orderView.printOrders(i + ". " + "[Productname: " + productName
					+ ", Amount: " + listOrderLines.get(i).getAmount() + "]");
		}
		int option = orderView
				.chooseProductFromOrderLine(listOrderLines.size());
		return listOrderLines.get(option);
	}
}
