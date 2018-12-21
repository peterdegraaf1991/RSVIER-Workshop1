package model_class;

public class OrderLine {

	private final int id;
	private final int orderId;
	private final int productId;
	private int amount;

	public OrderLine(int id, int orderId, int productId, int amount) {
		this.id = id;
		this.orderId = orderId;
		this.productId = productId;
		this.amount = amount;
	}

	public int getId() {
		return id;
	}

	public int getOrderId() {
		return orderId;
	}

	public int getProductId() {
		return productId;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}
}
