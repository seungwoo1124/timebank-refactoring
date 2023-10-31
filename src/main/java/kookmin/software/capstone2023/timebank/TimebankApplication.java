package kookmin.software.capstone2023.timebank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
public class TimebankApplication {

	public static void main(String[] args) {
		SpringApplication.run(TimebankApplication.class, args);
	}

}
