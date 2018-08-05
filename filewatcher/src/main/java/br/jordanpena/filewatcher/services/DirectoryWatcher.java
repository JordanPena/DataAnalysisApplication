package br.jordanpena.filewatcher.services;

import java.io.IOException;
import java.nio.file.*;
import java.util.regex.Pattern;

import static java.nio.file.StandardWatchEventKinds.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;

public class DirectoryWatcher {

	private Path pathDir;
	private WatchService watcher;
	private WatchKey key;

	public DirectoryWatcher(String path) {
		this.pathDir = Paths.get(path + "/data/in");
		findFiles();
		createWatcher();
	}

	private void findFiles() {
		try {
			System.out.println("Application is running!");
			Files.walk(this.pathDir).filter(Files::isRegularFile).forEach(f -> {

				if (Pattern.compile(".dat$").matcher(f.toFile().getName()).find()) {
					new FileAnalysisService(pathDir.resolve(f.toFile().getName()));
				}

			});
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	void createWatcher() {
		boolean error = true;

		try {
			watcher = FileSystems.getDefault().newWatchService();
			key = pathDir.register(watcher, ENTRY_CREATE, ENTRY_MODIFY);

			System.out.println("Watcher is running!");

		} catch (IOException exception) {
			error = false;
		}

		while (error) {
			final WatchKey key;

			try {
				key = watcher.take();
			} catch (InterruptedException exception) {
				error = false;
				break;
			}

			for (final WatchEvent<?> event : key.pollEvents()) {

				if (event.kind() == OVERFLOW) {
					continue;
				}

				String fileName = event.context().toString();

				if (fileName != null) {

					if (Pattern.compile(".dat$").matcher(fileName).find()) {
						
						new FileAnalysisService(pathDir.resolve(fileName));

					} else {
						continue;
					}

				}

			}
			boolean valid = key.reset();
			if (!valid) {
				error = false;
				break;
			}
		}
	}
}
