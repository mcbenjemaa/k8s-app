package com.github.memedchiheb.k8sapp;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class K8sAppApplication {
	
	@Value("${whoami.profile}")
	private String profile;

	@Value("${whoami.name}")
	private String name;
	
	public static void main(String[] args) {
		SpringApplication.run(K8sAppApplication.class, args);
	}
	
	@GetMapping("/")
	public String hello() {
	  return "Hello " + name + " from " + profile+"!";
	}

}
