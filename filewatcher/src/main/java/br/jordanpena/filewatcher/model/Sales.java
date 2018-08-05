package br.jordanpena.filewatcher.model;

import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;

public class Sales {
	private String id;
	private String saleId;
	private List<SaleItems> listItems;
	private String salesmanName;
	private Double total;

	public Sales(Matcher m) {
		this.setId(m.group(1));
		this.setSaleId(m.group(2));
		this.createListOfItems(m.group(3));
		this.setSalesmanName(m.group(4));
		this.sumTotal();
	}

	private void createListOfItems(String group) {

		setListItems(new LinkedList<SaleItems>());

		String[] items = group.replace("[", "").replace("]", "").split("[,]");

		for (int i = 0; i < items.length; i++) {
			getListItems().add(new SaleItems(items[i]));
		}

	}

	public void sumTotal() {
		getListItems().stream().forEach(i -> {
			this.setTotal(i.getPrice() * i.getQuantity());
		});
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSaleId() {
		return saleId;
	}

	public void setSaleId(String saleId) {
		this.saleId = saleId;
	}

	public String getSalesmanName() {
		return salesmanName;
	}

	public void setSalesmanName(String salesmanName) {
		this.salesmanName = salesmanName;
	}

	public List<SaleItems> getListItems() {
		return listItems;
	}

	public void setListItems(List<SaleItems> listItems) {
		this.listItems = listItems;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

}
