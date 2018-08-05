package br.jordanpena.filewatcher;

import br.jordanpena.filewatcher.services.DirectoryWatcher;

public class App {
	private static String homePath;

	public static void main(String[] args) {
		homePath = System.getProperty("user.home");
		new DirectoryWatcher(homePath);
	}
}
