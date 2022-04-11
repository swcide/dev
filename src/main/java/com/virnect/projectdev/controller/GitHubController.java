package com.virnect.projectdev.controller;

import java.io.IOException;
import java.net.URL;

import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GitHub;
import org.kohsuke.github.GitHubBuilder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import com.virnect.projectdev.domain.request.CreateRepoRequestDto;
import com.virnect.projectdev.service.GitHubService;

@Slf4j
@RestController
@RequiredArgsConstructor
public class GitHubController {

	private final GitHubService gitHubService;
	// String token = "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";
	@PostMapping("/repo")
	public String CreateRepo (@RequestBody CreateRepoRequestDto createRepoRequestDto) throws IOException {


		String url = gitHubService.createRepo(createRepoRequestDto);

		return url;
	}
}
