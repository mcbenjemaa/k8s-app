package com.github.memedchiheb.k8sapp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;



@EnableDiscoveryClient
@SpringBootApplication
@RestController
public class K8sAppApplication {
	
	@Autowired
	private DiscoveryClient discoveryClient;
	
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

	
	@GetMapping("/client")
	public List<String> client() {
	  return discoveryClient.getServices();
	}
	
	@GetMapping("/client/description")
	public String clientDescription() {
	  return discoveryClient.description();
	}
	
	@GetMapping("/client/{serviceId}")
	public List<ServiceInstance> client(@PathVariable String serviceId) {
	  return discoveryClient.getInstances(serviceId);
	}
}
