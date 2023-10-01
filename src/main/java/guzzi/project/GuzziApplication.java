package guzzi.project;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@MapperScan(value={"guzzi.project"})
@PropertySource("classpath:/app.properties")
@SpringBootApplication
public class GuzziApplication {

	public static void main(String[] args) {
		SpringApplication.run(GuzziApplication.class, args);
	}

}
