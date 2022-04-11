package com.virnect.projectdev.domain.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CreateRepoRequestDto {

	private String token;
	private String projectName;
	private String description;

	@Builder
	public CreateRepoRequestDto(String token, String projectName, String description) {
		this.token = token;
		this.projectName = projectName;
		this.description = description;
	}

}
