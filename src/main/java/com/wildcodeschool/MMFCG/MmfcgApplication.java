package com.wildcodeschool.MMFCG;

import com.wildcodeschool.MMFCG.storage.StorageProperties;
import com.wildcodeschool.MMFCG.storage.StorageService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class MmfcgApplication {

	public static void main(String[] args) {
		SpringApplication.run(MmfcgApplication.class, args);
	}

	@Bean
	CommandLineRunner init(StorageService storageService) {
		return (args) -> {
			//storageService.deleteAll();
			storageService.init();
		};
	}
}
