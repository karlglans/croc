package se.purple.croc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class ExampleCtrl {

	@RequestMapping(value = "/ex")
	public String hello() {
		return "Example2";
	}

	@RequestMapping(value = "/admin/surveys")
	public String admin() {
		return "index";
	}

//	@RequestMapping(value="/**/{[path:[^\\.]*}")
//	public String index() {
//		System.out.println("rewrite");
//		return "index";
//	}
}
