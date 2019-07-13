package se.purple.croc.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExampleCtrl {

//	@Value("${security.token.testparam}")
//	String testparam;

	@RequestMapping(value = "/ex")
	public String hello() {
		return "Example2 ";
	}
}
