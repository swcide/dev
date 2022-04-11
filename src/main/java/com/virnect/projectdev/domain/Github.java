package com.virnect.projectdev.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor

@Entity
public class Github {

	@Id
	@Column(name = "id", nullable = false)
	private Long id;

	@Column
	private String url;
	@Column
	private String projectName;
	@Column
	private String projectCode;


}
