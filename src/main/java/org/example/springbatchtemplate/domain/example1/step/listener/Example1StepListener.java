package org.example.springbatchtemplate.domain.example1.step.listener;

import org.example.springbatchtemplate.common.service.RetryService;
import org.example.springbatchtemplate.domain.example1.step.reader.Example1Reader;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class Example1StepListener implements StepExecutionListener {

	private final RetryService retryService;
	private final Example1Reader reader;

	@Override
	public void beforeStep(StepExecution stepExecution) {
		// Step 실행 전 초기화 또는 설정 로직 추가 가능
		System.out.println("Step 시작: " + stepExecution.getStepName());
		reader.reset();
	}

	@Override
	public ExitStatus afterStep(StepExecution stepExecution) {
		// Step 실행 후 로직 추가 가능
		System.out.println("Step 완료: " + stepExecution.getStepName());
		return stepExecution.getExitStatus();
	}

	public void onStepError(StepExecution stepExecution, Throwable t) {
		// RetryService를 통해 실패 로깅
		retryService.logFailure(
			stepExecution.getJobExecution().getJobInstance().getJobName(),
			stepExecution.getStepName(),
			t.getMessage(),
			null // 필요한 경우 요청 데이터를 추가
		);
	}
}
