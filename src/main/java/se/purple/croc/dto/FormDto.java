package se.purple.croc.dto;

import lombok.Data;
import se.purple.croc.domain.Form;
import se.purple.croc.domain.FormQuestion;

import java.util.ArrayList;
import java.util.List;

@Data
public class FormDto {
	int id;
	String title;
	List<FormQuestionDto> questions = new ArrayList<>();
	boolean isEditable;
}
