package org.example.springbatchtemplate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class SpringBatchTemplateApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBatchTemplateApplication.class, args);
	}

}
