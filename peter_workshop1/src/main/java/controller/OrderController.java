package controller;

import java.math.BigDecimal;
import java.time.LocalDateTime;
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
	CustomerController customerController = new CustomerController();
	OrderView orderView = new OrderView();
	CustomerView customerView = new CustomerView();
	ProductController productController = new ProductController();
	ProductView productView = new ProductView();
	ProductDaoImpl productDaoImpl = new ProductDaoImpl();

	@Override
	public void runController() {
		int keuze = 1;
		Controller.newView = true;
		do{
			if (Controller.newView == true){
				orderView.ClearTerminal();
				orderView.PrintMenuHeader();
				orderView.PrintMenuOptions();
				Controller.newView = false;
			}
			
		keuze = orderView.RequestMenuOption();
		switch (keuze) {
			case 1: ViewOrders(); break;
			case 2: AddOrder(); break;
			case 3: EditOrderMenu(); break;
			
			case 9: keuze = 0; Controller.newView = true; break;
			default:orderView.InvalidInput(); break;
				}
			}
		while(keuze != 0);
		}

	public void EditOrderMenu() {
		int keuze = 1;
		Controller.newView = true;
		do{
			if (Controller.newView == true){
				orderView.ClearTerminal();
				orderView.PrintMenuHeader();
				orderView.PrintMenuOptions();
				Controller.newView = false;
			}
			
		keuze = orderView.RequestMenuOption();
		switch (keuze) {
			case 1: ChangeName(); break;
			case 2: ChangeStock(); break;
			case 3: ChangePrice(); break;
			case 4: Delete(); break;
			
			case 9: keuze = 0; Controller.newView = true; break;
			default:orderView.InvalidInput(); break;
				}
			}
		while(keuze != 0);
		}

	
	private void AddOrder() {
		
		Customer customer = customerController.ChoosePersonFromList();
			if (customer == null){
				// No customer message
				return;
			}
		// Create Order
		Order order = new Order();
		order.setCustomer (customer);
		order.setDate(LocalDateTime.now());
		order.setTotalCost(new BigDecimal(0));
		int generatedId = orderDaoImpl.createOrder(order);
		
		// Create Orderlines
	boolean addProduct = true;
	BigDecimal totalCost = new BigDecimal(0);
	while (addProduct){
		OrderLine orderLine = new OrderLine();
		Product product = productController.SelectProductFromList();
			if(product == null){
				return;
			}
		orderLine.setProduct (product);
		orderLine.setAmount (orderView.requestAmount(product.getStock()));
		orderLine.setOrderId (generatedId);
		
		product.setStock(product.getStock() - orderLine.getAmount());
		productDaoImpl.updateProduct(product);
		
		orderLineDaoImpl.createOrderLine(orderLine);
		totalCost = product.getPrice().multiply(new BigDecimal(orderLine.getAmount()));
		if (orderView.AddMoreProducts() == false)
			addProduct = false;
		System.out.println("AddProduct = " + addProduct);
		}
		
	// Update Order with Cost & Date
	order.setTotalCost(totalCost);
	order.setId(generatedId);
	orderDaoImpl.updateOrder(order);
	
	}


	private void ViewOrders() {
		
	List<Integer> list = orderDaoImpl.readCustomerIdsWithOrder();
	for (int i=0; i<list.size(); i++) {
		Customer customer = customerDaoImpl.readCustomerById((Integer)list.get(i));
		orderView.printCustomerNamesWithOrder("i. " + customer.toString());
		}
	}

	private void Delete() {
		// TODO Auto-generated method stub
		
	}

	private void ChangePrice() {
		// TODO Auto-generated method stub
		
	}

	private void ChangeStock() {
		// TODO Auto-generated method stub
		
	}

	private void ChangeName() {
		// TODO Auto-generated method stub
	}
	
}
	


