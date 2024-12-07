package org.example.springbatchtemplate.domain.example1.step.writer;

import java.time.LocalDateTime;

import org.example.springbatchtemplate.domain.example1.entity.Example1Entity;
import org.example.springbatchtemplate.domain.example1.repository.Example1Repository;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

@Component
public class Example1DbWriter implements ItemWriter<Example1Entity> {

	private final Example1Repository repository;

	public Example1DbWriter(Example1Repository repository) {
		this.repository = repository;
	}

	@Override
	public void write(Chunk<? extends Example1Entity> items) {
		for (Example1Entity item : items) {
			repository.findByNameAndValue(item.getName(), item.getValue())
				.ifPresentOrElse(
					existing -> {
						System.out.println("Duplicate data: " + existing.getName());
					},
					() -> {
						item.setProcessedAt(LocalDateTime.now()); // 처리 시간 기록
						repository.save(item);
					}
				);
		}
	}
}
