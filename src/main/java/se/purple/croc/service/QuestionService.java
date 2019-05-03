package se.purple.croc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import se.purple.croc.domain.Form;
import se.purple.croc.domain.FormQuestion;
import se.purple.croc.dto.QuestionDto;
import se.purple.croc.repository.FromRepository;
import se.purple.croc.repository.QuestionRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {
	@Autowired
	FromRepository formRepo;

	@Autowired
	QuestionRepository questionRepo;

	public List<QuestionDto> getQuestionsByForm(int formId) {

		// TODO: Check if this can be more optimized.
		List<FormQuestion> questionList = questionRepo.getQuestionsByFormId(formId);
		List<QuestionDto> questions = new ArrayList<>();
		for (FormQuestion formQuestion: questionList) {
			QuestionDto questionDto = new QuestionDto();
			questionDto.copy(formQuestion.getQuestion()); // maybe move to service
			questionDto.setNumber(formQuestion.getNumber());
			questions.add(questionDto);
		}
		return questions;
	}
}
