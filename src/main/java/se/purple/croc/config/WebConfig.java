package se.purple.croc.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
class WebConfig implements WebMvcConfigurer {

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		// will forward unmatched uris to frontend in static folder
		registry.addViewController("/**/{path:[^\\.]*}")
				.setViewName("forward:/");
	}
}
