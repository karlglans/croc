package se.purple.croc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.jpa.support.OpenEntityManagerInViewFilter;
import javax.servlet.Filter;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.view.RedirectView;

@EnableTransactionManagement
@SpringBootApplication
public class CrocApplication {

	public static void main(String[] args) {
		System.out.println("MAIN 005");
		SpringApplication.run(CrocApplication.class, args);
	}

	/**
	 * Register the {@link OpenEntityManagerInViewFilter} so that the
	 * GraphQL-Servlet can handle lazy loads during execution.
	 *
	 * @return
	 */
//	@Bean
//	public Filter OpenFilter() {
//		return new OpenEntityManagerInViewFilter();
//	}

//	@Bean
//	public SessionFactory sessionFactory(final HibernateEntityManagerFactory hemf) {
//		return hemf.getSessionFactory();
//	}

//	@Bean
//	public HibernateJpaSessionFactoryBean sessionFactory() {
//		return new HibernateJpaSessionFactoryBean();
//	}
}
