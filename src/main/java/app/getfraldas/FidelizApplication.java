package app.getfraldas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class FidelizApplication {

	public static void main(String[] args) {
		SpringApplication.run(FidelizApplication.class, args);
	}
}
