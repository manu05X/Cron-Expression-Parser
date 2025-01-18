package com.example.CronPaserCLI;

import com.example.CronPaserCLI.service.CronParserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;

import java.util.Scanner;

@SpringBootApplication
public class CronPaserCliApplication {

	public static void main(String[] args) {
		new SpringApplicationBuilder(CronPaserCliApplication.class)
				.web(WebApplicationType.NONE)
				.logStartupInfo(false)
				.bannerMode(org.springframework.boot.Banner.Mode.OFF)
				.run(args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(CronParserService cronParserService) {
		return args -> {
			Scanner scanner = new Scanner(System.in);
			System.out.println("Enter a cron expression (e.g., */15 0 1,15 * 1-5 /usr/bin/find), or type 'exit' to quit:");

			while (true) {
				System.out.print("> ");
				String input = scanner.nextLine();

				if ("exit".equalsIgnoreCase(input)) {
					System.out.println("Exiting application. Goodbye!");
					break;
				}

				String[] parts = input.split(" ");

				if (parts.length != 6) {
					System.out.println("Invalid cron expression format. Expected 5 time fields and 1 command.");
					System.out.println("Format: minute hour day-of-month month day-of-week command");
					continue;
				}

				try {
					cronParserService.parseCronExpression(input);
				} catch (Exception ex) {
					System.out.println("Error: " + ex.getMessage());
				}
			}
			scanner.close();
		};
	}
}
