package org.example.springbatchtemplate.common.enums;

public enum RequestStatus {
	PENDING,   // 재시도 대기 중
	RETRYING,  // 재시도 중
	FAILED     // 재시도 초과
}
