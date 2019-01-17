package controller;

import java.util.ArrayList;
import java.util.List;

import dao.OrderLineDao;
import dao.OrderLineDaoImpl;
import view.OrderLineView;
import model_class.OrderLine;
import model_class.Product;

public class OrderLineController {

	ProductController productController = new ProductController();
	OrderLineView orderLineView = new OrderLineView();
	OrderLineDao orderLineDaoImpl = new OrderLineDaoImpl();

	// OrderLine Controller
	public List<OrderLine> createOrderLines(int orderId) {
		List<OrderLine> orderList = new ArrayList<>();
		List<Product> productList = new ArrayList<>();
		boolean addProduct = true;
		OrderLine orderLine = new OrderLine();
		while (addProduct) {
			Product product = productController.SelectProductFromList();
			if (productList.contains(product)) {
				orderLineView.ProductAlreadyAdded();
			} else {
				orderLine.setProduct(product);
				orderLine.setAmount(orderLineView.requestAmount(product
						.getStock()));
				orderLine.setOrderId(orderId);
				orderLineDaoImpl.createOrderLine(orderLine);
				orderList.add(orderLine);
				productList.add(product);
			}
			if (orderLineView.AddMoreProducts() == false)
				addProduct = false;
		}
		return orderList;
	}

}
