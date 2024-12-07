package org.example.springbatchtemplate.domain.example1.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Version;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Example1Entity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	private int value;

	@Version
	private int version; // 낙관적 락 버전 관리 필드 추가

	private LocalDateTime processedAt; // 처리 시간 기록

	@Builder
	public Example1Entity(String name, int value, int version) {
		this.name = name;
		this.value = value;
		this.version = version;
	}
}
