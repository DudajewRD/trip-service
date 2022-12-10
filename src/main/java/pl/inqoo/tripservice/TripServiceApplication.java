package pl.inqoo.tripservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.envers.repository.support.EnversRevisionRepositoryFactoryBean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import pl.inqoo.tripservice.model.helper.AuditorAwareProvider;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditorAwareProvider")
@EnableJpaRepositories(repositoryFactoryBeanClass =
		EnversRevisionRepositoryFactoryBean.class)
public class TripServiceApplication {
	@Bean
	public AuditorAware<String> auditorAwareProvider() {
		return new AuditorAwareProvider();
	}

	public static void main(String[] args) {
		SpringApplication.run(TripServiceApplication.class, args);
	}

}
