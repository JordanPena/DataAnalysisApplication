package br.jordanpena.filewatcher.model;

import java.util.regex.Matcher;

public class Customer {
	private String id;
	private String cnpj;
	private String name;
	private String businessArea;
	
	public Customer(Matcher m) {
		this.setId(m.group(1));
		this.setCnpj(m.group(2));
		this.setName(m.group(3));
		this.setBusinessArea(m.group(4));
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBusinessArea() {
		return businessArea;
	}

	public void setBusinessArea(String businessArea) {
		this.businessArea = businessArea;
	}

}
