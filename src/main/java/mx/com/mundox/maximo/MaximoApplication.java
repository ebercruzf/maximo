package mx.com.mundox.maximo;

import java.util.Collections;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.client.RestTemplate;


@Configuration
@ComponentScan
@EnableAutoConfiguration
@EnableOAuth2Client
@EnableAsync

@SpringBootApplication
public class MaximoApplication extends SpringBootServletInitializer implements WebApplicationInitializer {

	
	private static final Logger LOGGER = LoggerFactory.getLogger(MaximoApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(MaximoApplication.class, args);
		
	}
    
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(MaximoApplication.class);
    }
    @Autowired
    BCryptPasswordEncoder passwordEncoder;
    
    @Bean(name = "appRestClient")
    public RestTemplate getRestClient() {
        RestTemplate restClient = new RestTemplate(
                new BufferingClientHttpRequestFactory(new SimpleClientHttpRequestFactory()));

        // Add one interceptor like in your example, except using anonymous class.
        restClient.setInterceptors(Collections.singletonList((request, body, execution) -> {

            LOGGER.debug("Intercepting...");
            return execution.execute(request, body);
        }));

        return restClient;
    }
    
    
//  public static void main(String[] args) {
  public void run(String[] args) throws Exception {
//		CifradoRSAUtileria cifradoRSAUtileria = new CifradoRSAUtileria();
		String password = "admin123_"; //12345 Max12345_
		
		for (int i = 0; i < 88; i++) {
			String passwordBcrypt = passwordEncoder.encode(password);
			System.out.println("CLAVE_PRUEBA*+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++: "+ password+ " :_ENCRRIPTADA_:" + passwordBcrypt);
		}
		

		}

//  **************************************************************************
}