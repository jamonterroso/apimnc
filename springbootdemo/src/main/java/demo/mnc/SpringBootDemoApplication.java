package demo.mnc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
@SpringBootApplication 
public class SpringBootDemoApplication {
        private static final Logger LOG = LoggerFactory.getLogger(SpringBootDemoApplication.class);
    
	public static void main(String[] args) {
		SpringApplication.run(SpringBootDemoApplication.class, args);
	}
        
}
        
