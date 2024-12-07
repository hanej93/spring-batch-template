package org.example.springbatchtemplate.api.controller;

import java.util.ArrayList;
import java.util.List;

import org.example.springbatchtemplate.api.dto.MockApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MockApiController {

	@GetMapping("/api/data")
	public List<MockApiResponse> fetchData(@RequestParam long startId, @RequestParam long endId) {
		List<MockApiResponse> response = new ArrayList<>();
		long totalItems = 100; // 총 데이터 개수 제한

		for (long i = startId; i <= endId && i <= totalItems; i++) {
			response.add(new MockApiResponse(i, "Item " + i, (int) i * 10));
		}

		return response;
	}
}
