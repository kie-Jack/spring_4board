package com.jcpdev.board.dao;

import java.util.List;

import com.jcpdev.board.model.Gallery;

public interface GalleryMapper {
	List<Gallery> getAll();
	int insert(Gallery gallery);
}