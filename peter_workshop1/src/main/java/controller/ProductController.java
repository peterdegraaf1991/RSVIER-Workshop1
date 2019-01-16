package controller;

import java.util.List;

import model_class.Product;
import dao.ProductDao;
import dao.ProductDaoImpl;
import view.ProductView;

public class ProductController extends Controller {
	ProductDao productDaoImpl = new ProductDaoImpl();
	ProductView productView = new ProductView();
	
	@Override
	public void runController() {
		int keuze = 1;
		Controller.newView = true;
		do{
			if (Controller.newView == true){
				productView.ClearTerminal();
				productView.PrintMenuHeader();
				productView.PrintMenuOptions();
				Controller.newView = false;
			}
			
		keuze = productView.RequestMenuOption();
		switch (keuze) {
			case 1: PrintProductlist(); break;
			case 2: AddProduct(); break;
			case 3: UpdateProduct(SelectProductFromList()); break;
			case 4: DeleteProduct(); break;
			case 9: keuze = 0; Controller.newView = true; break;
			default:productView.InvalidInput(); break;
				}
			}
		while(keuze != 0);
		}

	private void UpdateProduct(Product product) {
		 if (product == null)
				return;
	product.setName(productView.RequestName());
	product.setPrice(productView.RequestPrice());
	product.setStock(productView.RequestStock());
	productDaoImpl.updateProduct(product);
	// Print succes message
	}

	private void DeleteProduct() {
	Product product = SelectProductFromList();
	 if (product == null)
			return;
	productDaoImpl.deleteProduct(product.getId());
	// Print succes message
	}
	
	//duplicate with ShowProductList, but List<product is needed)
	private Product SelectProductFromList() {
	List<Product> list = PrintProductlist();
	if (list == null){
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
	productDaoImpl.createProduct(product);
	// Print succes message
	}

	private List<Product> PrintProductlist() {
		List<Product> list = productDaoImpl.readAllProducts();
		if (list.size() <= 0){
			productView.NoProductFound(); return null;
		}
		for (int i = 0; i < list.size(); i++){
			productView.printProduct("i." + list.get(i).toString());
		}
		return list;
	}
}
	



