package com.jefff.gr.homework;

import com.jefff.gr.homework.console.ConsoleApplication;
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
			ConsoleApplication consoleApplication = new ConsoleApplication();
			consoleApplication.run(args);
		} else {
			SpringApplication.run(GrHomeworkApplication.class, args);
		}
	}

}
