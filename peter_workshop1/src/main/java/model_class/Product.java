package model_class;

import java.math.BigDecimal;

public class Product {

	private String name;
	private int id;
	private int stock;
	private BigDecimal price;

    public Product(String name, int id, BigDecimal price, int stock) {
      this.name = name;
      this.id = id;
      this.price = price;
      this.stock = stock;
    }

	public Product() {
		// TODO Auto-generated constructor stub
	}

	public String getName() {
		return name;
		}

	public int getId() {
		return id;
		}

	public BigDecimal getPrice() {
		return price;
		}

	public int getStock() {
		return stock;
		}

	public void setName(String name) {
		this.name = name;
		}

	public void setPrice(BigDecimal price) {
		this.price = price;
		}

	public void setStock(int stock) {
		this.stock = stock;
		}

	public void setId(int id) {
		this.id = id;
	}

	
	}


