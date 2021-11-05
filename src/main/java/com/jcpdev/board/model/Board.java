package com.jcpdev.board.model;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Getter
@Setter
public class Board {
	private int idx;
	private String name;
	private String password;
	private String subject;
	private String content;
	private String ip;
	private int commentCount;
	private int readCount;
	private Date wdate;
}
