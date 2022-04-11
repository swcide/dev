package com.virnect.projectdev.utill;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.reactive.function.client.WebClient;

import com.google.common.net.HttpHeaders;

import reactor.core.publisher.Mono;

import com.virnect.projectdev.domain.GitBranch;
import com.virnect.projectdev.domain.Github;
import com.virnect.projectdev.global.common.WebClientFilter;

public class GithubUtils {

	private final String GITHUB_TOKEN = "token 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";

	public List<GitBranch> getBranch(String name) {

		WebClient webClient = WebClient.builder()
			.baseUrl("https://api.github.com/repos/virnect-corp/")
			.defaultHeader(HttpHeaders.AUTHORIZATION, GITHUB_TOKEN)
			.filter(WebClientFilter.logRequest())
			.filter(WebClientFilter.logResponse())
			.build();

		Github github = new Github();

		Mono<List<GitBranch>> response = webClient.get()
			.uri(name)
			.retrieve()
			.bodyToMono(new ParameterizedTypeReference<List<GitBranch>>() {
			});

		List<GitBranch> gitBranches = response.block();


		return gitBranches;
	}
}
