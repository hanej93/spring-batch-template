package org.example.springbatchtemplate.domain.example1.step.processor;

import org.example.springbatchtemplate.api.dto.MockApiResponse;
import org.example.springbatchtemplate.domain.example1.entity.Example1Entity;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class Example1Processor implements ItemProcessor<MockApiResponse, Example1Entity> {

	@Override
	public Example1Entity process(MockApiResponse item) {
		return Example1Entity.builder()
			.name(item.getName())
			.value(item.getValue())
			.build();
	}
}
