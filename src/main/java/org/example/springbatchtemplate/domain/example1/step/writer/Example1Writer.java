package org.example.springbatchtemplate.domain.example1.step.writer;

import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

@Component
public class Example1Writer implements ItemWriter<String> {

	@Override
	public void write(Chunk<? extends String> items) throws Exception {
		// 데이터 출력
		items.forEach(System.out::println);
	}
}
