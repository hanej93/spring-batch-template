package org.example.springbatchtemplate.domain.example1.config;

import org.example.springbatchtemplate.api.dto.MockApiResponse;
import org.example.springbatchtemplate.domain.example1.entity.Example1Entity;
import org.example.springbatchtemplate.domain.example1.step.processor.Example1Processor;
import org.example.springbatchtemplate.domain.example1.step.reader.Example1ApiReader;
import org.example.springbatchtemplate.domain.example1.step.writer.Example1DbWriter;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class Example1StepConfig {

	private final JobRepository jobRepository;
	private final PlatformTransactionManager transactionManager;
	private final Example1ApiReader reader;
	private final Example1Processor processor;
	private final Example1DbWriter writer;


	@Bean
	public Step example1Step() {
		return new StepBuilder("example1Step", jobRepository)
			.<MockApiResponse, Example1Entity>chunk(10, transactionManager)
			.reader(reader)
			.processor(processor)
			.writer(writer)
			.build();
	}
}
