package controller;

import java.util.List;

import model_class.OrderLine;
import model_class.Product;
import dao.DaoFactory;
import dao.ProductDao;
import dao.ProductDaoImpl;
import view.ProductView;

public class ProductController extends Controller {
	ProductView productView = new ProductView();

	@Override
	public void runController() {
		int keuze = 1;
		Controller.newView = true;
		do {
			if (Controller.newView == true) {
				productView.ClearTerminal();
				productView.PrintMenuHeader();
				productView.PrintMenuOptions();
				Controller.newView = false;
			}

			keuze = productView.RequestMenuOption();
			switch (keuze) {
			case 1:
				PrintProductlist();
				requestNewMenu();
				break;
			case 2:
				if (workerOrAdminPermission() == true)
					AddProduct();
				else
					productView.noPermission();
				requestNewMenu();
				break;
			case 3:
				if (workerOrAdminPermission() == true)
					UpdateProduct(SelectProductFromList());
				else
					productView.noPermission();
				requestNewMenu();
				break;
			case 4:
				if (workerOrAdminPermission() == true)
					DeleteProduct();
				else
					productView.noPermission();
				requestNewMenu();
				break;
			case 9:
				keuze = 0;
				Controller.newView = true;
				break;
			case 0:
				productView.logoutTimer();
				System.exit(0);
				break;
			default:
				productView.InvalidInput();
				break;
			}
		} while (keuze != 0);
	}

	private void UpdateProduct(Product product) {
		if (product == null)
			return;
		product.setName(productView.RequestName());
		product.setPrice(productView.RequestPrice());
		product.setStock(productView.RequestStock());
		DaoFactory.getProductDao().updateProduct(product);
		// Print succes message
	}

	private void DeleteProduct() {
		Product product = SelectProductFromList();
		if (product == null)
			return;

			String error = DaoFactory.getProductDao().deleteProduct(product.getId());
			productView.printMessage(error);
		}

	public Product SelectProductFromList() {
		List<Product> list = PrintProductlist();
		if (list == null) {
			return null;
		}
		int index = productView.RequestProductNumber(list.size());
		return list.get(index);
	}

	private void AddProduct() {
		Product product = new Product();
		product.setName(productView.RequestName());
		product.setPrice(productView.RequestPrice());
		product.setStock(productView.RequestStock());
		DaoFactory.getProductDao().createProduct(product);
		// Print succes message
	}

	private List<Product> PrintProductlist() {
		List<Product> productList = DaoFactory.getProductDao()
				.readAllProducts();
		if (productList.size() <= 0) {
			productView.NoProductFound();
			return null;
		}
		productView.printProductList(productList);
		return productList;
	}

	public void updateStock(List<OrderLine> list) {
		for (int i = 0; i < list.size(); i++) {
			int productId = list.get(i).getProduct().getId();
			Product product = DaoFactory.getProductDao().readProductById(
					productId);
			product.setStock(product.getStock() - list.get(i).getAmount());
			DaoFactory.getProductDao().updateProduct(product);
		}
	}

}
