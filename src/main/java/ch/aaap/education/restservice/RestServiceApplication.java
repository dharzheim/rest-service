package ch.aaap.education.restservice;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import ch.aaap.education.restservice.domain.Organization;
import ch.aaap.education.restservice.domain.User;
import ch.aaap.education.restservice.repository.OrganizationRepository;
import ch.aaap.education.restservice.repository.UserRepository;

@SpringBootApplication
public class RestServiceApplication {
	private static final Logger log = LoggerFactory.getLogger(RestServiceApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(RestServiceApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner demo(OrganizationRepository repository, UserRepository userRepository) {
		return (args) -> {
			Organization org1 = new Organization("3ap");
			Organization org2 = new Organization("IBM");
			Organization org3 = new Organization("UBS");
			
			repository.save(org1);
			repository.save(org2);
			repository.save(org3);
			
			userRepository.save(new User("Daniel Harzheim","harzheim@3ap.ch","test",org1));
			userRepository.save(new User("Cyricl Gabathuler","gabathuler@3ap.ch","test2",org1));
			userRepository.save(new User("IBM Mitarbeiter","ma@ibm.ch","test3",org2));

			// fetch all customers
			log.info("Orgs found with findAll():");
			log.info("-------------------------------");
			for (Organization org : repository.findAll()) {
				log.info(org.toString());
			}
            log.info("");

			// fetch an individual customer by ID
            Organization retrievedOrg = repository.findOne(1L);
			log.info("Organization found with findOne(1L):");
			log.info("--------------------------------");
			log.info(retrievedOrg.toString());
            log.info("");

			// fetch customers by last name
			log.info("Organization found with findByLastName('Bauer'):");
			log.info("--------------------------------------------");
			for (Organization org : repository.findByName("3ap")) {
				log.info(org.toString());
			}
            log.info("");
		};
	}
}
