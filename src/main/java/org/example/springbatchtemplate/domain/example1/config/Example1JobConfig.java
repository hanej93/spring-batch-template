package org.example.springbatchtemplate.domain.example1.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Example1JobConfig {

	private final JobRepository jobRepository;
	private final Step example1Step;

	public Example1JobConfig(JobRepository jobRepository, Step example1Step) {
		this.jobRepository = jobRepository;
		this.example1Step = example1Step;
	}

	@Bean
	public Job example1Job() {
		return new JobBuilder("example1Job", jobRepository)
			.start(example1Step)
			.build();
	}
}
