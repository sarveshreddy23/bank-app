package org.example.servicediscovary;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
@RefreshScope
public class ServiceDiscovaryApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceDiscovaryApplication.class, args);
	}

}
