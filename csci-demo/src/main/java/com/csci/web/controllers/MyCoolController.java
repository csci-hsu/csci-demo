package com.csci.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller // Controllers 'control' how the web server reacts to user requests.
public class MyCoolController {

	@RequestMapping("/") // URL we're mapping to
	public String hello() {
		return "helloworld"; // Template name
	}
	
	@RequestMapping("/admin")
	public String admin() {
		return "admin";
	}
}
