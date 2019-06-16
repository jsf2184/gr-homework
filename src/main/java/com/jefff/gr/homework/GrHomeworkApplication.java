package com.jefff.gr.homework;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Properties;

@SpringBootApplication
public class GrHomeworkApplication {

	public static void main(String[] args) {
		Properties properties = System.getProperties();
		String mode = properties.getProperty("mode");
		if (mode != null && mode.equals("console")) {
			System.out.printf("Hello-Goodbye - argcount = %d\n", args.length);
		} else {
			SpringApplication.run(GrHomeworkApplication.class, args);
		}
	}

}
