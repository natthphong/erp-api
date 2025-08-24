package th.co.erp.sme;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


//@EnableCaching
@SpringBootApplication
//@ComponentScan("th.co.my.sm")
@EnableJpaAuditing
@EnableJpaRepositories
public class ErpApplication {


	public static void main(String[] args) {

		SpringApplication.run(ErpApplication.class, args);
	}
}
