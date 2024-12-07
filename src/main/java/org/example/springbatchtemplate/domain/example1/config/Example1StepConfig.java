package org.example.springbatchtemplate.domain.example1.config;

import org.example.springbatchtemplate.domain.example1.step.listener.Example1StepListener;
import org.example.springbatchtemplate.domain.example1.step.processor.Example1Processor;
import org.example.springbatchtemplate.domain.example1.step.reader.Example1Reader;
import org.example.springbatchtemplate.domain.example1.step.writer.Example1Writer;
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
	private final Example1Reader reader;
	private final Example1Processor processor;
	private final Example1Writer writer;
	private final Example1StepListener stepListener;

	@Bean
	public Step example1Step() {
		return new StepBuilder("example1Step", jobRepository)
			.<String, String>chunk(10, transactionManager)
			.reader(reader) // Reader 설정
			.processor(processor) // Processor 설정
			.writer(writer) // Writer 설정
			.listener(stepListener) // Listener 설정
			.faultTolerant() // 에러 처리 활성화
			.skip(Exception.class) // Exception 발생 시 스킵
			.build();
	}
}
