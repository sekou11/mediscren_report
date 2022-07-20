package mediscreen_report;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MediscrenReportApplication {

	public static void main(String[] args) {
		SpringApplication.run(MediscrenReportApplication.class, args);
	}

}
