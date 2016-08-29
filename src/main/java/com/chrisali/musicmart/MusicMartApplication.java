package com.chrisali.musicmart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
public class MusicMartApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(MusicMartApplication.class, args);
	}
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(MusicMartApplication.class);
	}
}
