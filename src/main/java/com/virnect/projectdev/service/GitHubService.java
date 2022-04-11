package com.virnect.projectdev.service;

import java.net.URL;

import org.kohsuke.github.GitHub;
import org.kohsuke.github.GitHubBuilder;
import org.springframework.stereotype.Service;

import com.virnect.projectdev.domain.request.CreateRepoRequestDto;

@Service
public class GitHubService {

	public String createRepo(CreateRepoRequestDto createRepoRequestDto) {

		try {
			GitHub github = new GitHubBuilder()
				.withOAuthToken(createRepoRequestDto.getToken())
				.build();





			github.getOrganization("virnect-corp")
				.createRepository(createRepoRequestDto.getProjectName())
				.fromTemplateRepository("virnect-corp", "ProjectDev-Base")
				.create();

			URL url = github.getRepository(("virnect-corp/"+createRepoRequestDto.getProjectName()))
				.getHtmlUrl();

			return url.toString();


		}catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}

}
