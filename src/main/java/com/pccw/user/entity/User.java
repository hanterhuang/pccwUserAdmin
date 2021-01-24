package com.pccw.user.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {
	private String id;
	private String first;
	private String last;
	private String email;
	//@JsonIgnore
	private String password;
	@JsonIgnore
	private String token;
}
