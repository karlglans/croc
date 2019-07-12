package se.purple.croc.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExampleCtrl {

	@RequestMapping(value = "/ex")
	public String hello() {
		return "Example2";
	}
}
