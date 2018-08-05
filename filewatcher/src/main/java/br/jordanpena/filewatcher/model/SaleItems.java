package br.jordanpena.filewatcher.model;

public class SaleItems {
	private String id;
	private int quantity;
	private Double price;

	public SaleItems(String s) {
		this.setId(s.split("-")[0]);
		this.setQuantity(Integer.parseInt(s.split("-")[1]));
		this.setPrice(Double.parseDouble(s.split("-")[2]));
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}
