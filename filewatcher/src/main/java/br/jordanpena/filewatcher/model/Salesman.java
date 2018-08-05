package br.jordanpena.filewatcher.model;

import java.util.regex.Matcher;

public class Salesman {
	private String id;
	private String cpf;
	private String name;
	private String salary;

	public Salesman(Matcher m) {
		this.setId(m.group(1));
		this.setCpf(m.group(2));
		this.setName(m.group(3));
		this.setSalary(m.group(4));
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSalary() {
		return salary;
	}

	public void setSalary(String salary) {
		this.salary = salary;
	}

}
