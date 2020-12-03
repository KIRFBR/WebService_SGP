import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
@EntityScan(basePackages = { "entity" })
@EnableJpaRepositories(basePackages = { "repository" })
@ComponentScan(basePackages = {"controller"})
public class Application {
	
	public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurerAdapter() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/product/*").allowedOrigins("http://localhost:8000");
				registry.addMapping("/user/*").allowedOrigins("http://localhost:8000");
				registry.addMapping("/client/*").allowedOrigins("http://localhost:8001");
				registry.addMapping("/service/*").allowedOrigins("http://localhost:8001");
				registry.addMapping("/myService/*").allowedOrigins("http://localhost:8001");
			}
		};
	}
}