package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/")
public class PlantController {

	@GetMapping("/main")
	public String main() {
		return "main";
	}
	
	@GetMapping("/login")
	public String login() {
		return "/member/member_login";
	}
	
	@GetMapping("/join")
	public String join() {
		return "/member/member_join";
	}
	@GetMapping("/cart")
	public String cart() {
		return "cart/cart_list";
	}
}
