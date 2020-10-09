package com.wmsweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class WmswebApplication {

	public static void main(String[] args) throws IOException {

		SpringApplication.run(WmswebApplication.class, args);

		Runtime.getRuntime().exec(new String[]{"cmd", "/c","start chrome http://localhost:9080/login"});
	}

}
