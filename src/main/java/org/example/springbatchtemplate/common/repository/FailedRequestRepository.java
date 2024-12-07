package org.example.springbatchtemplate.common.repository;

import java.util.List;

import org.example.springbatchtemplate.common.entity.FailedRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FailedRequestRepository extends JpaRepository<FailedRequest, Long> {

	// 특정 Job의 실패 요청 중 재시도 가능한 요청 조회
	List<FailedRequest> findByJobNameAndRetryCountLessThan(String jobName, int maxRetryCount);
}
