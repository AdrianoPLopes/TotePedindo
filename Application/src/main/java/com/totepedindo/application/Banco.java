package com.totepedindo.application;

import java.time.LocalDate;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.totepedindo.application.models.Users;
import com.totepedindo.application.repositorys.UsersRepository;



@Component
@Transactional
public class Banco implements CommandLineRunner {

	@Autowired
	private UsersRepository usersRepo;
	
	@Override
	public void run(String... args) throws Exception {
		
	Users p1 = new Users("Tote");
	p1.setDataNascimento(LocalDate.of(1955, 7, 3));
	p1.setEmail("abrl@bol.com.br");
	
	Users p2 = new Users("Adriano");
	p2.setDataNascimento(LocalDate.of(1987, 1, 29));
	p2.setEmail("adrianoplopes@gmail.com");
	
	usersRepo.save(p1);
	usersRepo.save(p2);
	
	}

}
