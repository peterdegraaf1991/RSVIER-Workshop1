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
import dao.OrderLineDao;
import dao.OrderLineDaoImpl;
import dao.ProductDaoImpl;
import view.CustomerView;
import view.OrderView;
import view.ProductView;

public class OrderController extends Controller {

	OrderDao orderDaoImpl = new OrderDaoImpl();
	OrderLineDao orderLineDaoImpl = new OrderLineDaoImpl();
	CustomerDaoImpl customerDaoImpl = new CustomerDaoImpl();
	ProductDaoImpl productDaoImpl = new ProductDaoImpl();

	CustomerController customerController = new CustomerController();
	ProductController productController = new ProductController();
	OrderView orderView = new OrderView();

	ProductView productView = new ProductView();
	CustomerView customerView = new CustomerView();

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
//				viewOrdersOfCustomer();
				break;
			case 2:
				createOrder();
				break;
			case 3:
				editOrderMenu(selectOrderFromCustomer(selectCustomersWithOrder()));
				break;

			case 9:
				keuze = 0;
				Controller.newView = true;
				break;
			default:
				orderView.InvalidInput();
				break;
			}
		} while (keuze != 0);
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
				break;
			case 2:
				changeTotalCost(order);
				break;
			case 3:
				deleteOrder(order);
				break;

			case 9:
				keuze = 0;
				Controller.newView = true;
				break;
			default:
				orderView.InvalidInput();
				break;
			}
		} while (keuze != 0);
	}

	private void deleteOrder(Order order) {
		// TODO Auto-generated method stub
		
	}

	public void createOrder() {
		Customer customer = selectCustomer();
		int orderId = createNewOrderId(customer);
		List<OrderLine> orderLineList = createOrderLines(orderId);
		BigDecimal totalCost = calculateTotalCost(orderLineList);
		updateStock(orderLineList);
		Order order = orderDaoImpl.readOrderById(orderId);
		order.setTotalCost(totalCost);
		order.setDate(LocalDateTime.now());
		orderDaoImpl.updateOrder(order);
	}

	// Customer Controller
	public Customer selectCustomer() {
		Customer customer = customerController.ChoosePersonFromList();
		if (customer == null) {
			// No customer message
			return null;
		}
		return customer;
	}

	public int createNewOrderId(Customer customer) {
		Order order = new Order();
		order.setCustomer(customer);
		order.setDate(LocalDateTime.now());
		order.setTotalCost(new BigDecimal(0));
		int generatedId = orderDaoImpl.createOrder(order);
		return generatedId;
	}

	// OrderLine Controller
	public List<OrderLine> createOrderLines(int orderId) {
		List<OrderLine> orderList = new ArrayList<>();
		List<Product> productList = new ArrayList<>();
		boolean addProduct = true;
		OrderLine orderLine = new OrderLine();
		while (addProduct) {
			Product product = productController.SelectProductFromList();
			if (productList.contains(product)) {
				productView.ProductAlreadyAdded();
			} else {
				orderLine.setProduct(product);
				orderLine
						.setAmount(orderView.requestAmount(product.getStock()));
				orderLine.setOrderId(orderId);
				orderLineDaoImpl.createOrderLine(orderLine);
				orderList.add(orderLine);
				productList.add(product);
			}
			if (orderView.AddMoreProducts() == false)
				addProduct = false;
		}
		return orderList;
	}

	public BigDecimal calculateTotalCost(List<OrderLine> list) {
		BigDecimal totalCost = new BigDecimal(0);
		for (int i = 0; i < list.size(); i++) {
			Product product = list.get(i).getProduct();
			totalCost = product.getPrice().multiply(
					new BigDecimal(list.get(i).getAmount()));
		}
		return totalCost;
	}

	// Product Controller
	public void updateStock(List<OrderLine> list) {
		for (int i = 0; i < list.size(); i++) {
			Product product = list.get(i).getProduct();
			product.setStock(product.getStock() - list.get(i).getAmount());
			productDaoImpl.updateProduct(product);
		}
	}

	private Customer selectCustomersWithOrder() {
		List<Integer> customerIdList = orderDaoImpl.readCustomerIdsWithOrder();
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
		List<Order> orderList = orderDaoImpl.readOrdersOfCustomerId(customer
				.getId());

		for (int i = 0; i < orderList.size(); i++) {
			orderView.printOrders(i + ". " + orderList.get(i).toString());
		}
		int option = orderView.chooseOrder(orderList.size());
		return orderList.get(option);

	}


	private void changeTotalCost(Order order) {
	BigDecimal newTotalCost = orderView.requestTotalCost();
		order.setTotalCost(newTotalCost);
		orderDaoImpl.updateOrder(order);

	}


	private void changeProducts(Order order) {
		// TODO Auto-generated method stub

	}
}
