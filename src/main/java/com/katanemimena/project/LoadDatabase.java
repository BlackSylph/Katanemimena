package com.katanemimena.project;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.katanemimena.project.entity.User;
import com.katanemimena.project.repository.UserRepository;

@Configuration
public class LoadDatabase {
	
	private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

	 @Bean
	 CommandLineRunner initDatabase(UserRepository repository) {

	    return args -> {
	      //log.info("Preloading " + repository.save(new User("user1@gmail.com", "User1", "U1", 1)));
	      //log.info("Preloading " + repository.save(new User("user2@gmail.com", "User2", "U2", 2)));
	      //log.info("Preloading " + repository.save(new User("user3@gmail.com", "User3", "U3", 3)));
	    };
	  }
}
