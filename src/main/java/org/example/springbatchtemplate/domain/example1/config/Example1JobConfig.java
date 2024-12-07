package org.example.springbatchtemplate.domain.example1.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class Example1JobConfig {

	private final JobRepository jobRepository;
	private final Step example1Step;

	@Bean
	public Job example1Job() {
		return new JobBuilder("example1Job", jobRepository)
			.start(example1Step)
			.build();
	}
}
