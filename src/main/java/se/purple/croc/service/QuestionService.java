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

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {
	@Autowired
	FromRepository formRepo;

	@Autowired
	DebugInspect debugInspectHelper;

//	@Transactional
	public List<QuestionDto> getQuestionsByForm(Form form) {
		debugInspectHelper.printSession("1");
		// NOTE: Why do we need to get the entity form again?
//		form = formRepo.findById(form.getId()).get(); // looking up item again?

		List<FormQuestion> questionList = form.getFormQuestions();
		List<QuestionDto> questions = new ArrayList<>();
		for (FormQuestion formQuestion: questionList) {
			QuestionDto questionDto = new QuestionDto();
			questionDto.copy(formQuestion.getQuestion());
			questionDto.setNumber(formQuestion.getNumber());
			questions.add(questionDto);
		}
		debugInspectHelper.printSession("2");
		return questions;
	}
}
