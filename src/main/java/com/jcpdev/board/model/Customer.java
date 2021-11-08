package com.jcpdev.board.model;
//lombok

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Customer {
	private int idx;
	private String name;
	private String password;
	private String email;
	private String addr;
	private String addr_etc;
	private String gender;
	private int age;
	private String hobby;
	
}
