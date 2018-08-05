package br.jordanpena.filewatcher.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.DoubleSummaryStatistics;
import java.util.Map;

public class ExportReport {
	private Path pathDir;
	private StringBuilder sb = new StringBuilder();

	public ExportReport(long count, long count2, String saleId, Map<String, DoubleSummaryStatistics> reportSalesman) {
		pathDir = Paths.get(System.getProperty("user.home") + "/data/out");
		sb.append("Amount of clients = " + count2 + "\n");
		sb.append("Amount of salesman = " + count + "\n");
		sb.append("ID of the most expensive sale = " + saleId + "\n");
		sb.append("# ---- Salesman report ---- #"+"\n");
		reportSalesman.entrySet().stream()
				.forEach(m -> sb.append("Name: " + m.getKey() + " | Total Sales: " + m.getValue().getSum() + "\n"));
		export();
	}

	private void export() {
		try {
			Files.write(Paths.get(pathDir + "/reportSales.done.dat"), sb.toString().getBytes());
			System.out.println("Report successfully exported!");

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public Path getPathDir() {
		return pathDir;
	}

	public void setPathDir(Path pathDir) {
		this.pathDir = pathDir;
	}

}
