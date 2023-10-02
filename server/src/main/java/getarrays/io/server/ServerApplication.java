package getarrays.io.server;

import getarrays.io.server.enumeration.Status;
import getarrays.io.server.model.Server;
import getarrays.io.server.repository.ServerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServerApplication.class, args);
	}

	@Bean
	CommandLineRunner run(ServerRepository serverRepository) {
		return args -> {
			serverRepository.save(new Server(null, "192.168.1.160", "Ubuntu Linux", "16 GB", "Personal PC", "http://localhost:8080/server/image/s1.png", Status.SERVER_UP));
			serverRepository.save(new Server(null, "192.168.1.161", "Ubuntu Linux", "16 GB", "Personal PC", "http://localhost:8080/server/image/s2.png", Status.SERVER_UP));
			serverRepository.save(new Server(null, "192.168.1.162", "Ubuntu Linux", "16 GB", "Personal PC", "http://localhost:8080/server/image/s3.png", Status.SERVER_UP));
			serverRepository.save(new Server(null, "192.168.1.163", "Ubuntu Linux", "16 GB", "Personal PC", "http://localhost:8080/server/image/s4.png", Status.SERVER_UP));
		};
	}

}
