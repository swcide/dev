package com.virnect.projectdev.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.kohsuke.github.GitHub;
import org.kohsuke.github.GitHubBuilder;

import com.virnect.projectdev.domain.request.CreateRepoRequestDto;

public class GitHubServiceTest {

	String VIRNECT = "virnect-corp";
	String REPO = "/ProjectDev-base";

	String VIRNECT_TOKEN = "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";
	String SWCIDE_TOKEN = "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";

	@DisplayName("virnect-corp 레포지토리 생성")
	@Test
	public void createRepo() throws IOException {

		//given

		CreateRepoRequestDto createRepoRequestDto = CreateRepoRequestDto.builder()
			.token(VIRNECT_TOKEN)
			// .token(SWCIDE_TOKEN)
			.projectName("ProjectDev-test2")
			.description("테스트 레파지토리 입니다.")
			.build();
		GitHub github = new GitHubBuilder()
			.withOAuthToken(createRepoRequestDto.getToken())
			.build();

		// github.getOrganization(VIRNECT)
		github
			.createRepository(createRepoRequestDto.getProjectName())
			.description(createRepoRequestDto.getDescription())
			.private_(true)
			.create();


		//then

		// Assertions.assertEquals(VIRNECT, github.getRepository("virnect-corp/testPrjoect").getOwner().toString());

	}

	@DisplayName("virnect-corp 레포지토리 삭제")
	@Test
	public void deleteRepo() throws IOException {

		//given

		CreateRepoRequestDto createRepoRequestDto = CreateRepoRequestDto.builder()
			.token(VIRNECT_TOKEN)
			// .token("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")
			.projectName("ProjectDev-test")
			.description("테스트 레파지토리 입니다.")
			.build();
		GitHub github = new GitHubBuilder()
			.withOAuthToken(createRepoRequestDto.getToken())
			.build();

		// github.getOrganization(VIRNECT)
		github
			.getRepository("Jintae-VIRNECT/"+createRepoRequestDto.getProjectName())
				.delete();

		//then

	}

	@DisplayName("클론 이름 가져오기")
	@Test
	public void branches_name() throws IOException {
		CreateRepoRequestDto createRepoRequestDto = CreateRepoRequestDto.builder()
			.token("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")
			.projectName("ProjectDev-test")
			.description("테스트 레파지토리 입니다.")
			.build();
		GitHub github = new GitHubBuilder()
			.withOAuthToken(createRepoRequestDto.getToken())
			.build();

		List<String> collect = new ArrayList<>(github.getRepository(VIRNECT + REPO)
			.getBranches()
			.keySet());

		for (String s : collect) {

			System.out.println(s);
		}
	}

	@DisplayName("스크립트 실행")
	@Test
	public void execScript() throws IOException {

		String rootPath = System.getProperty("user.dir");
		System.out.println("rootPath = " + rootPath);

		String command = rootPath + "/src/main/clt/sh/clone.sh";

		CommandLine commandLine = CommandLine.parse(command);
		DefaultExecutor defaultExecutor = new DefaultExecutor();
		defaultExecutor.setExitValue(0);
		int iExitValue = defaultExecutor.execute(commandLine);

		System.out.println("iExitValue = " + iExitValue);

	}

	@DisplayName("스크립트 실행-window")
	@Test
	public void execScript_for_window() throws IOException {

		File directory = new File (".");
		System.out.println ("Current directory's canonical path: "
			+ directory.getCanonicalPath());
		System.out.println ("Current directory's absolute  path: "
			+ directory.getAbsolutePath());

		ProcessBuilder processBuilder = new ProcessBuilder();
		processBuilder.command("c:/home/base/clone.txt");



		try {
			// Run script
			Process process = processBuilder.start();

			// Read output
			StringBuilder output = new StringBuilder();
			BufferedReader reader = new BufferedReader(
				new InputStreamReader(process.getInputStream()));

			String line;
			while ((line = reader.readLine()) != null) {
				output.append(line);
			}

			System.out.println(output.toString());

		} catch (Exception e) {
			e.printStackTrace();
		}


	}

}