package com.example.demo;

import com.example.demo.config.Props;
import com.example.demo.util.S3helper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.nio.file.*;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner  {

	@Autowired
	private Props props;

	@Autowired
	private S3helper s3helper;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		s3helper.listBuckets();


		WatchService watchService = FileSystems.getDefault().newWatchService();
		Path path = Paths.get(props.getLocalDirectory());
		log.info("Checking payment file at --> " + path.toString());
		path.register(watchService, StandardWatchEventKinds.ENTRY_CREATE, StandardWatchEventKinds.ENTRY_DELETE, StandardWatchEventKinds.ENTRY_MODIFY);
		WatchKey key;
		while ((key = watchService.take()) != null) {
			for (WatchEvent<?> event : key.pollEvents()) {
				log.info("Event kind: " + event.kind() + ". File affected: " + event.context() + ".");
				if(".DS_Store".compareTo(event.context().toString())==0) {
					log.info("That Mac file again!");
				} else {
					String paymentFile =  event.context().toString();
					s3helper.uploadFile(paymentFile);
				}
			}
			key.reset();
		}

	}

}
