package br.jordanpena.filewatcher.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import br.jordanpena.filewatcher.model.Customer;
import br.jordanpena.filewatcher.model.Sales;
import br.jordanpena.filewatcher.model.Salesman;

public class FileAnalysisService {

	private Path path;
	private Pattern pSalesman = Pattern.compile("(001)ç(\\d+)ç([A-Z]\\w+)ç([1-9]\\d*(\\.\\d+)?)");
	private Pattern pCustomer = Pattern.compile("(002)ç(\\d+)ç([A-Z\\s\\w]+)ç([A-Z]\\w+)");
	private Pattern pSales = Pattern.compile("(003)ç(\\d+)ç(\\[[\\S]+\\])ç([A-Z]\\w+)");

	private List<Salesman> listSalemans = new LinkedList<Salesman>();
	private List<Customer> listCustomer = new LinkedList<Customer>();
	private List<Sales> listSales = new LinkedList<Sales>();

	public FileAnalysisService(Path path) {
		if (path != null) {
			this.path = path;
			readData();
			generateReport();
		}
	}

	void readData() {

		try {

			findData(Files.lines(path), pSalesman);

			findData(Files.lines(path), pCustomer);

			findData(Files.lines(path), pSales);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void findData(Stream<String> lines, Pattern pattern) {

		lines.parallel().filter(pattern.asPredicate()).forEach(elem -> {

			Matcher m = pattern.matcher(elem);

			while (m.find()) {
				switch (m.group(1)) {
				case "001":
					Salesman sm = new Salesman(m);
					getListSalemans().add(sm);
					break;
				case "002":
					Customer c = new Customer(m);
					getListCustomer().add(c);
					break;
				case "003":
					Sales s = new Sales(m);
					getListSales().add(s);
					break;
				default:
					break;
				}
			}
		});
	}

	private void generateReport() {

		if (!getListSales().isEmpty()) {
			Sales sale = Collections.max(getListSales(), Comparator.comparing(s -> s.getTotal()));

			Map<String, DoubleSummaryStatistics> reportSalesman = getListSales().stream().collect(
					Collectors.groupingBy(Sales::getSalesmanName, Collectors.summarizingDouble(Sales::getTotal)));

			new ExportReport(getListSalemans().stream().count(), getListCustomer().stream().count(), sale.getSaleId(),
					reportSalesman);
		}
	}

	public List<Salesman> getListSalemans() {
		return listSalemans;
	}

	public void setListSalemans(List<Salesman> listSalemans) {
		this.listSalemans = listSalemans;
	}

	public List<Customer> getListCustomer() {
		return listCustomer;
	}

	public void setListCustomer(List<Customer> listCustomer) {
		this.listCustomer = listCustomer;
	}

	public List<Sales> getListSales() {
		return listSales;
	}

	public void setListSales(List<Sales> listSales) {
		this.listSales = listSales;
	}

}
