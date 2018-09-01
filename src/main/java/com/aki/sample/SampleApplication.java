package com.aki.sample;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.aki.sample.mapper")
public class SampleApplication {

	public static void main(String[] args) {
		System.setProperty("file.encoding", "UTF-8");
		System.setProperty("spring.devtools.restart.enabled", "false");
		SpringApplication.run(SampleApplication.class, args);
	}
}
