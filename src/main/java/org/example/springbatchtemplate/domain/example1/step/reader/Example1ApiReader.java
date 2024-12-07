package org.example.springbatchtemplate.domain.example1.step.reader;

import java.util.Iterator;
import java.util.List;

import org.example.springbatchtemplate.api.dto.MockApiResponse;
import org.springframework.batch.item.ItemReader;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class Example1ApiReader implements ItemReader<MockApiResponse> {

	private final RestTemplate restTemplate;
	private Iterator<MockApiResponse> iterator;
	private long currentId; // 현재 읽고 있는 ID
	private long endId; // 범위의 끝 ID

	public Example1ApiReader() {
		this.restTemplate = new RestTemplate();
	}

	// Reader의 데이터 범위를 설정
	public void setRange(long startId, long endId) {
		this.currentId = startId;
		this.endId = endId;
		this.iterator = null; // 범위 변경 시 iterator 초기화
	}

	@Override
	public MockApiResponse read() {
		if (iterator == null || !iterator.hasNext()) {
			if (currentId > endId) {
				return null;
			}

			List<MockApiResponse> data = fetchDataFromApi(currentId, Math.min(currentId + 9, endId)); // 10개씩 가져오기
			if (data.isEmpty()) {
				return null;
			}

			iterator = data.iterator();
			currentId += data.size(); // 다음 ID로 이동
		}

		return iterator.next();
	}

	private List<MockApiResponse> fetchDataFromApi(long startId, long endId) {
		String url = String.format("http://localhost:8080/api/data?startId=%d&endId=%d", startId, endId);
		return List.of(restTemplate.getForObject(url, MockApiResponse[].class));
	}
}
