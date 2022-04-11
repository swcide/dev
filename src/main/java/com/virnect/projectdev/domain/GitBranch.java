package com.virnect.projectdev.domain;

import java.util.List;

import lombok.Getter;

@Getter
public class GitBranch {

	private String name;
	private Commit commit;
	private Protection protection;
	private String protection_url;


	static class Commit {
		String sha;
		String url;
	}

	static class Protection {
		Boolean enabled;
		Required_status_checks required_status_checks;

	}
	static class Required_status_checks{
		String enforcement_level;
		String contexts;
		String checks;
	}

}
