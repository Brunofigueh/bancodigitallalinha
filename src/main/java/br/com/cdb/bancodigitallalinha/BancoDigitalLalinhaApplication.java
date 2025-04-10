package br.com.cdb.bancodigitallalinha;

import org.springframework.boot.SpringApplication; 
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "br.com.cdb.bancodigitallalinha")
@EnableJpaRepositories("br.com.cdb.bancodigitallalinha.repository")
@EntityScan("br.com.cdb.bancodigitallalinha.entity")
public class BancoDigitalLalinhaApplication {

	public static void main(String[] args) {
		SpringApplication.run(BancoDigitalLalinhaApplication.class, args);
	}

}
