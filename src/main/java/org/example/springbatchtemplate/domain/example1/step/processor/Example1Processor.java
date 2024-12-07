package org.example.springbatchtemplate.domain.example1.step.processor;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class Example1Processor implements ItemProcessor<String, String> {

	@Override
	public String process(String item) {
		// 데이터를 대문자로 변환하는 간단한 처리 로직
		return item.toUpperCase();
	}
}
