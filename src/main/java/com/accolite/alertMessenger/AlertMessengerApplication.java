package com.accolite.alertMessenger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;


@SpringBootApplication
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class AlertMessengerApplication {

	public static void main(String[] args) {
		SpringApplication.run(AlertMessengerApplication.class, args);
	}

}
