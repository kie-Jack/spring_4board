package com.jcpdev.board.model;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Comment {
	private int idx;
	private int mref;
	private String name;
	private String password;
	private String content;
	private Date wdate;
	private String ip;
}
