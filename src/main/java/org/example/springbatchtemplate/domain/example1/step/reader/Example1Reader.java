package org.example.springbatchtemplate.domain.example1.step.reader;

import org.springframework.batch.item.ItemReader;
import org.springframework.stereotype.Component;

@Component
public class Example1Reader implements ItemReader<String> {

	private int count = 0;
	private boolean initialized = false;


	@Override
	public String read() {
		if (!initialized) {
			// Reader 초기화
			count = 0;
			initialized = true;
		}

		// 간단한 데이터 제공 로직 (데이터 없으면 null 반환)
		if (count < 10) {
			return "Sample Data " + (++count);
		}
		return null;
	}

	public void reset() {
		initialized = false; // 스케줄마다 초기화하도록 상태 리셋
	}
}
