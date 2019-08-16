package se.purple.croc.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExampleCtrl {

	@Value("${app.oauth2.authorizedRedirectUris}")
	String testparam;

	@RequestMapping(value = "/ex")
	public String hello() {
		return testparam;
	}
}
