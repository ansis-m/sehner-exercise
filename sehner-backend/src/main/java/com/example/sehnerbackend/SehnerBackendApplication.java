package com.example.sehnerbackend;

import com.example.sehnerbackend.services.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SehnerBackendApplication {

	@Autowired
	public SehnerBackendApplication(FileService fileService){
		try{
			fileService.processFiles();
		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage() + e);
		}
	}

	public static void main(String[] args) {
		SpringApplication.run(SehnerBackendApplication.class, args);
	}
}
