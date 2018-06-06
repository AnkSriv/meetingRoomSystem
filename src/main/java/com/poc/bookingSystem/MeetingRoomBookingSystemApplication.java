package com.poc.bookingSystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages="com.poc.bookingSystem.repo.**")
@EnableAutoConfiguration
@EntityScan(basePackages="com.poc.bookingSystem.entity.**")
public class MeetingRoomBookingSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(MeetingRoomBookingSystemApplication.class, args);
	}
}
