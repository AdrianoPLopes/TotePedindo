package com.totepedindo.application.controllers;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.totepedindo.application.models.Users;
import com.totepedindo.application.repositorys.UsersRepository;

@Controller
public class UserController {

	private UsersRepository usersRepo;

	public UserController(UsersRepository usersRepo) {
		this.usersRepo = usersRepo;
	}

	@GetMapping("/users")
	public String users(Model model) {
		model.addAttribute("listaUsers", usersRepo.findAll());
		return "/users/index";
	}

	@GetMapping("/users/nova")
	public String novoUser(@ModelAttribute("user") Users user) {
		return "users/form";
	}

	
	
	@GetMapping("/users/{id}")
	public String AlterarUsers(@PathVariable("id") long id, Model model) {
		Optional<Users> userOpt  = usersRepo.findById(id);
		if (userOpt.isEmpty()) {
			throw new IllegalArgumentException("Pessoa Inválida");
		}
		model.addAttribute("user", userOpt.get());
		return "users/form";
	}
	
	@PostMapping("/users/salvar")
	public String salvarUsers(@Valid @ModelAttribute("user") Users user, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "/users/form";
		}
		usersRepo.save(user);
		return "redirect:/users";
	}
	@GetMapping("/users/excluir/{id}")
	public String excluirUsers(@PathVariable("id") long id) {
		Optional<Users> userOpt  = usersRepo.findById(id);
		if (userOpt.isEmpty()) {
			throw new IllegalArgumentException("Pessoa Inválida");
		}
		usersRepo.delete(userOpt.get());
		return "redirect:/users";
	}
}
