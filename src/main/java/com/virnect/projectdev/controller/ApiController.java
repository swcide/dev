package com.virnect.projectdev.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import com.virnect.projectdev.domain.GitBranch;
import com.virnect.projectdev.domain.Github;
import com.virnect.projectdev.utill.GithubUtils;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ApiController {

	@GetMapping("/health")
	public String healthCheck(){
		return "HI";
	}

	@PostMapping("/github")
	public String createJob(@RequestBody Github github){

		System.out.println(" = " );


		GithubUtils githubUtils = new GithubUtils();

		log.info(github.getUrl());
		System.out.println("github = " + github);

		List<GitBranch> branch = githubUtils.getBranch("ProjectDev-Base/branches");

		System.out.println("branch = " + branch.get(0).getName());

		return github.getUrl();

	}

}
