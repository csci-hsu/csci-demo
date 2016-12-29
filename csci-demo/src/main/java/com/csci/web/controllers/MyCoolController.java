package com.csci.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Controllers 'control' how the web server reacts to user requests.
 * 
 * This class defines a bunch of URLs that can be hit by users, along with specifying
 * which web pages will be shown as a result (if any are shown at all!)
 * 
 * @author seth.ellison
 *
 */
@Controller
public class MyCoolController {

	/**
	 * Basic example of how to create a home page for a website.
	 * @return Name of the HTML template to send back to the user.
	 */
	@RequestMapping("/") // URL we're mapping to
	public String hello() {
		return "helloworld"; // Template name
	}
	
	/**
	 * This is our Spring Security secured URL. Only logged in users with the admin role will be able to reach the admin.html page.
	 * @return Name of the HTML template to send back to the user.
	 */
	@RequestMapping("/admin")
	public String admin() {
		return "admin";
	}
	
	/**
	 * When we receive a POST request to the /header URL, this function runs. 
	 * 
	 * @param myCoolHeader String which contains the value of the header.
	 * @return A string as the response body for the client.
	 */
	@RequestMapping(value = "/header", method = RequestMethod.POST) 
	@ResponseBody
	public String header(@RequestHeader("My-Cool-Header") String myCoolHeader) {
		System.out.println(myCoolHeader);
		return myCoolHeader;
	}
	
	/**
	 * Processes a request to /pathparam, where the second URI segment contains
	 * the value of name.
	 * 
	 * @param name The name of the person to greet, sent via the URL!
	 * @param model Thymeleaf uses a Model object to send information to the template. It's basically just a map. 
	 * @return The name of the Thymeleaf template to render.
	 */
	@RequestMapping("/pathparam/{name}")
	public String pathParam(@PathVariable String name, Model model) {
		model.addAttribute("name", name);
		return "greeter";
	}
}
