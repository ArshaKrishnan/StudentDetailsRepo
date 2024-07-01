package com.demo.studentInfo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 *@EnableDiscoveryClient is used to register the application to eureka server
 * @EnableFeignClients to enable openfiegn for calling other microservice
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class StudentInfoApplication {

	public static void main(String[] args) {

		SpringApplication.run(StudentInfoApplication.class, args);

	}

}
