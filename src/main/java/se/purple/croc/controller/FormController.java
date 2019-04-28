package se.purple.croc.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import se.purple.croc.dto.FormDto;
import se.purple.croc.service.FormService;

@RestController
@RequestMapping("/api/v1/form")
public class FormController {
	private FormService formService;

	FormController(FormService formService) {
		this.formService = formService;
	}

	@GetMapping(value = "/{id}", produces = "application/json")
	public FormDto getFormById(@PathVariable final int id) {
		return formService.getFormById(id);
	}
}
