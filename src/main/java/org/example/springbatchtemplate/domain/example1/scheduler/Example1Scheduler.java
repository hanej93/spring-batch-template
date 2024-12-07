package org.example.springbatchtemplate.domain.example1.scheduler;

import org.example.springbatchtemplate.domain.example1.step.reader.Example1ApiReader;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class Example1Scheduler {

	private final JobLauncher jobLauncher;
	private final Job example1Job;
	private final Example1ApiReader reader;

	private long currentStartId = 1; // 시작 ID
	private final long rangeSize = 50; // 처리 범위 크기

	@Scheduled(fixedDelay = 10_000, initialDelay = 5_000) // 5초마다 실행
	public void runExample1Job() {
		long currentEndId = currentStartId + rangeSize - 1; // 현재 스케줄의 끝 ID
		log.info("Example1 Job 실행 시작: 범위 {} ~ {}", currentStartId, currentEndId);

		try {
			reader.setRange(currentStartId, currentEndId);

			JobExecution execution = jobLauncher.run(
				example1Job,
				new JobParametersBuilder()
					.addLong("timestamp", System.currentTimeMillis()) // 고유 Job 파라미터
					.toJobParameters()
			);

			log.info("Example1 Job 실행 완료. 상태: {}", execution.getStatus());
			currentStartId = currentEndId + 1; // 다음 범위로 이동
		} catch (Exception e) {
			log.error("Example1 Job 실행 실패: {}", e.getMessage());
		}
	}

}
