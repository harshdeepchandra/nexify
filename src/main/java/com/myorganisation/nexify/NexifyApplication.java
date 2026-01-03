package com.myorganisation.nexify;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class NexifyApplication {

	public static void main(String[] args) {
		SpringApplication.run(NexifyApplication.class, args);
	}

}
