package guzzi.project;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@MapperScan(value={"guzzi.project"})
@PropertySource("classpath:/app.properties")
@SpringBootApplication
public class GuzziApplication {

	public static void main(String[] args) {
		SpringApplication.run(GuzziApplication.class, args);
	}

}
