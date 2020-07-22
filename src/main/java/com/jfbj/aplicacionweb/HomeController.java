package com.jfbj.aplicacionweb;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

	@GetMapping("/login")
	public String login(@RequestParam(name = "name", required = false, defaultValue = "World") String name,
			Model model) {
		model.addAttribute("name", name);
		return "login";
	}

	@GetMapping("/registro")
	public String registro(Model model) {
		
		return "registro";
	}

	@GetMapping("/menu")
	public String menu(Model model) {
		
		return "menu";
	}
}