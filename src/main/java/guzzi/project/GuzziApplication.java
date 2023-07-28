package guzzi.project;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan(value={"com."})
@SpringBootApplication
public class GuzziApplication {

	public static void main(String[] args) {
		SpringApplication.run(GuzziApplication.class, args);
	}

}
