package com.skychen.minyi;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import java.util.Arrays;

@SpringBootApplication
//@ComponentScan("com.skychen.minyi.repository")
public class MinyixApplication {

	public static void main(String[] args) {
		SpringApplication.run(MinyixApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args -> {

			System.out.println("-------------> just checking!");

			String[] beanNames = ctx.getBeanDefinitionNames();
			Arrays.sort(beanNames);
//			for (String beanName : beanNames) {
//				System.out.println(beanName);
//			}
			
			System.out.println("-------------< END!");
		};
	}

}
