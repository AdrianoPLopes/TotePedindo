package com.totepedindo.application.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.totepedindo.application.repositorys.UsersRepository;

@Controller
public class UserController {

	private  UsersRepository usersRepo;
	
	
	public UserController(UsersRepository usersRepo) {
		this.usersRepo = usersRepo;
	}
	
	@GetMapping("/users")
	public String users(Model model) {
	model.addAttribute("listaUsers", usersRepo.findAll());
		return "/users/index";
	}
}
