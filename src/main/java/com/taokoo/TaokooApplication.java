
package com.taokoo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("com.taokoo")
@SpringBootApplication
public class TaokooApplication {
	public static void main(String[] args) {
		SpringApplication.run(TaokooApplication.class, args);
	}
}