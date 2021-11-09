package com.jcpdev.board.controller;

import java.io.IOException;
import java.util.List;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jcpdev.board.model.Gallery;
import com.jcpdev.board.service.GalleryService;

@Controller
@RequestMapping("/gallery")
public class GalleryController {
	private static final Logger logger = LoggerFactory.getLogger(GalleryController.class);

	private final GalleryService service;

	public GalleryController(GalleryService service) {
		this.service=service;
	}

	@RequestMapping(value="gallery", method=RequestMethod.GET)
	public void insert(Model model) {
		logger.info(String.valueOf(RequestMethod.GET));
		List<Gallery> list = service.getAll();
		model.addAttribute("list",list);
	}

	@RequestMapping(value="gallery",method=RequestMethod.POST)
	//		,headers = ("content-type=multipart/*"))
	public String save(Gallery gallery) { 
		try {
			service.save(gallery);
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "redirect:gallery";
	}


}