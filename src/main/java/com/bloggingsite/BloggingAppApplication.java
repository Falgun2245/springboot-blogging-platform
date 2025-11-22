package com.bloggingsite;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static java.lang.System.out;

@SpringBootApplication
public class BloggingAppApplication {

	public static void main(String[] args) {

		SpringApplication.run(BloggingAppApplication.class, args);

		out.println("my Blogging Application is Connected within PostgresSQL Database...");
	}
}