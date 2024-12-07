package org.example.springbatchtemplate.domain.example1.step.listener;

import org.example.springbatchtemplate.common.service.RetryService;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class Example1StepListener implements StepExecutionListener {

	private final RetryService retryService;

	@Override
	public void beforeStep(StepExecution stepExecution) {
		System.out.println("Step 시작: " + stepExecution.getStepName());
	}

	@Override
	public ExitStatus afterStep(StepExecution stepExecution) {
		System.out.println("Step 완료: " + stepExecution.getStepName());
		return stepExecution.getExitStatus();
	}

	public void onStepError(StepExecution stepExecution, Throwable t) {
		retryService.logFailure(
			stepExecution.getJobExecution().getJobInstance().getJobName(),
			stepExecution.getStepName(),
			t.getMessage(),
			null // 필요한 경우 요청 데이터를 추가
		);
	}
}
