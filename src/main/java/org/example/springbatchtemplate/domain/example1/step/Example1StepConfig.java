package org.example.springbatchtemplate.domain.example1.step;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class Example1StepConfig {

	private final JobRepository jobRepository;
	private final PlatformTransactionManager transactionManager;

	public Example1StepConfig(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
		this.jobRepository = jobRepository;
		this.transactionManager = transactionManager;
	}

	@Bean
	public Step example1Step() {
		return new StepBuilder("example1Step", jobRepository)
			.<String, String>chunk(10, transactionManager)
			.reader(() -> "Hello, Batch!") // Reader
			.processor(item -> item.toUpperCase()) // Processor
			.writer(items -> items.forEach(System.out::println)) // Writer
			.build();
	}
}
