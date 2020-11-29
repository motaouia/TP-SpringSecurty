package org.motaouia.securitybrain;

import org.motaouia.securitybrain.repositories.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = UserRepository.class)
public class Tp5SecurityBrainApplication {

	public static void main(String[] args) {
		SpringApplication.run(Tp5SecurityBrainApplication.class, args);
	}

}
