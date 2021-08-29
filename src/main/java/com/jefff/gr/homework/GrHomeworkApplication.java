package com.jefff.gr.homework;

import com.jefff.gr.homework.console.ConsoleApplication;
import com.jefff.gr.homework.console.output.RealPrintWriter;
import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Properties;

@SpringBootApplication
public class GrHomeworkApplication {

	private static final Logger log = Logger.getLogger(RealPrintWriter.class);

	public static void main(String[] args) {
		Properties properties = System.getProperties();
		String mode = properties.getProperty("mode");
		if (mode != null && mode.equals("console")) {
			ConfigurableApplicationContext context = new AnnotationConfigApplicationContext("com.jefff.gr.homework");
			ConsoleApplication consoleApplication = context.getBean(ConsoleApplication.class);
			consoleApplication.run(args);
		} else {
			ConfigurableApplicationContext context = SpringApplication.run(GrHomeworkApplication.class, args);
			log.info("back from SpringApplication.run()");


		}
	}

}
