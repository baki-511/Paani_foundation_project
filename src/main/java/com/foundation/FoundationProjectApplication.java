package com.foundation;

import com.foundation.entity.AdminUser;
import com.foundation.repository.AdminUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class FoundationProjectApplication implements CommandLineRunner {
	@Autowired
	private AdminUserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(FoundationProjectApplication.class, args);
		System.out.println("Application is Running...");
	}
	
	@Override
	public void run(String... args) throws Exception {
		AdminUser user = new AdminUser();
		user.setFullName("Navin Kumar");
		user.setMobile("7563222");
		user.setRole("ADMIN");
		user.setUsername("navin@gmail.com");
		user.setPassword(passwordEncoder.encode("admin"));
//		userRepository.save(user);
	}
}
