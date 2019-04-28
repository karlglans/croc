package se.purple.croc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.purple.croc.domain.Form;
import se.purple.croc.domain.FormQuestion;
import se.purple.croc.dto.QuestionDto;
import se.purple.croc.repository.FromRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {
	@Autowired
	FromRepository fromRepo;

	public List<QuestionDto> getQuestionsByForm(Form form) {
		List<FormQuestion> questionList = form.getFormQuestions();
		List<QuestionDto> questions = new ArrayList<>();
		for (FormQuestion formQuestion: questionList) {
			QuestionDto questionDto = new QuestionDto();
			questionDto.copy(formQuestion.getQuestion());
			questionDto.setNumber(formQuestion.getNumber());
			questions.add(questionDto);
		}
		return questions;
	}
}
