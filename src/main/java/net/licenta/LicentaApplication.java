package net.licenta;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/test")
@SpringBootApplication
public class LicentaApplication {

	public static void main(String[] args) {
		SpringApplication.run(LicentaApplication.class, args);
	}
	
	@GetMapping
	public String test() {
	  return "Is On";
	}
	
	 /**
   * Bean used to take messages from messages.properties
   * 
   * @param messageSource
   * @return
   */
  @Bean
  public LocalValidatorFactoryBean validator(MessageSource messageSource) {
      LocalValidatorFactoryBean validatorFactoryBean = new LocalValidatorFactoryBean();
      validatorFactoryBean.setValidationMessageSource(messageSource);
      return validatorFactoryBean;
  }
}
