package org.example.springbatchtemplate.domain.example1.scheduler;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class Example1Scheduler {

	private final JobLauncher jobLauncher;
	private final Job example1Job;

	@Scheduled(fixedDelay = 5_000) // 1분마다 실행
	public void runExample1Job() {
		try {
			jobLauncher.run(example1Job, new JobParametersBuilder()
				.addLong("timestamp", System.currentTimeMillis())
				.toJobParameters());
		} catch (Exception e) {
			System.err.println("Example1 Job 실행 실패: " + e.getMessage());
		}
	}
}
