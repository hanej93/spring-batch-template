package org.example.springbatchtemplate.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MockApiResponse {
	private Long id;
	private String name;
	private int value;
}
