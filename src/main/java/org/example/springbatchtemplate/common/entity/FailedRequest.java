package org.example.springbatchtemplate.common.entity;

import org.example.springbatchtemplate.common.enums.RequestStatus;
import org.hibernate.annotations.DynamicUpdate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@DynamicUpdate
@NoArgsConstructor(access = AccessLevel.PROTECTED) // JPA 기본 생성자
public class FailedRequest extends Auditable {

	@Column(nullable = false)
	private String jobName; // 실패한 Job 이름

	@Column(nullable = false)
	private String stepName; // 실패한 Step 이름

	@Column(nullable = false)
	private String errorMessage; // 에러 메시지

	@Column(columnDefinition = "TEXT")
	private String requestData; // 실패한 요청 데이터 (JSON 형태 등)

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private RequestStatus status = RequestStatus.PENDING;

	private int retryCount = 0; // 재시도 횟수 (기본값 0)

	@Builder
	public FailedRequest(String jobName, String stepName, String errorMessage, String requestData) {
		this.jobName = jobName;
		this.stepName = stepName;
		this.errorMessage = errorMessage;
		this.requestData = requestData;
		this.status = RequestStatus.PENDING; // 초기 상태
	}

	// 에러 메시지와 상태 업데이트
	public void updateErrorStatus(String errorMessage, RequestStatus status) {
		this.errorMessage = errorMessage;
		this.status = status;
	}

	// 재시도 횟수 증가
	public FailedRequest incrementRetryCount() {
		this.retryCount += 1;
		return this;
	}
}
