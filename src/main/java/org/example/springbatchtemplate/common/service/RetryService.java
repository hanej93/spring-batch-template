package org.example.springbatchtemplate.common.service;

import java.util.List;

import org.example.springbatchtemplate.common.entity.FailedRequest;
import org.example.springbatchtemplate.common.enums.RequestStatus;
import org.example.springbatchtemplate.common.repository.FailedRequestRepository;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RetryService {

	private final FailedRequestRepository failedRequestRepository;
	private final ObjectMapper objectMapper;

	// 실패 요청 기록
	@Transactional
	public void logFailure(String jobName, String stepName, String errorMessage, Object requestData) {
		String requestDataJson;
		try {
			requestDataJson = objectMapper.writeValueAsString(requestData);
		} catch (Exception e) {
			requestDataJson = "Invalid JSON format";
		}
		FailedRequest failedRequest = FailedRequest.builder()
			.jobName(jobName)
			.stepName(stepName)
			.errorMessage(errorMessage)
			.requestData(requestDataJson)
			.build();
		failedRequestRepository.save(failedRequest);
	}

	// 재시도 가능한 실패 요청 조회
	public List<FailedRequest> getPendingRequests(String jobName, int maxRetryCount) {
		return failedRequestRepository.findByJobNameAndRetryCountLessThan(jobName, maxRetryCount);
	}

	// 실패 요청 재시도 처리
	@Transactional
	public void processRetry(FailedRequest failedRequest, int maxRetryCount) {
		if (failedRequest.getRetryCount() >= maxRetryCount) {
			// 재시도 초과된 경우 상태와 에러 메시지를 업데이트
			failedRequest.updateErrorStatus("Max retry count exceeded", RequestStatus.FAILED);
			failedRequestRepository.save(failedRequest);
			throw new IllegalStateException("Max retry count exceeded for request: " + failedRequest.getId());
		}

		// 재시도 카운트 증가 및 저장
		failedRequest.incrementRetryCount();
		failedRequestRepository.save(failedRequest);
	}
}
