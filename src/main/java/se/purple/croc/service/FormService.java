package se.purple.croc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.purple.croc.domain.Form;
import se.purple.croc.domain.FormQuestion;
import se.purple.croc.domain.Question;
import se.purple.croc.dto.FormDto;
import se.purple.croc.dto.FormQuestionDto;
import se.purple.croc.repository.FromRepository;
import se.purple.croc.repository.QuestionRepository;
import se.purple.croc.service.exceptions.ServiceException;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
public class FormService {

	@Autowired
	QuestionRepository questionRepo;

	@Autowired
	FromRepository formRepo;

	@Transactional
	public Form getForm(int id) {
		return formRepo.findById(id).get();
	}

	private int findLastPos(List<FormQuestion> formQuestionList, AtomicBoolean fromQuestionFound,
							int formQuestionNumber) {
		int maxFqNumber = 0;
		fromQuestionFound.set(false);
		for(FormQuestion fq : formQuestionList) {
			if(fq.getNumber() > maxFqNumber) {
				maxFqNumber = fq.getNumber();
			}
			if(fq.getNumber() == formQuestionNumber) {
				fromQuestionFound.set(true);
			}
		}
		return maxFqNumber;
	}

	@Transactional
	public int addQuestionToForm(Integer questionId, Integer formId) {
		Question question = questionRepo.getFirstById(questionId);
		Form form = formRepo.getFirstById(formId);

		if (question == null || form == null) {
			return 0;
		}

		List<FormQuestion> formQuestions = form.getFormQuestions();
		AtomicBoolean fromQuestionFound = new AtomicBoolean();

		int nextFormQuestionNumber = findLastPos(formQuestions, fromQuestionFound, questionId) + 1;
		if (fromQuestionFound.get()) {
			return 0;
		}

		FormQuestion formQuestion = new FormQuestion();
		formQuestion.setForm(form);
		formQuestion.setQuestion(question);
		formQuestion.setNumber(nextFormQuestionNumber);

		formQuestions.add(formQuestion);
		formRepo.save(form);
		return nextFormQuestionNumber;
	}

	public FormQuestionDto makeFormQuestionDto(FormQuestion formQuestion) {
		FormQuestionDto formQuestionDto = new FormQuestionDto();
		formQuestionDto.setText(formQuestion.getQuestion().getText());
		formQuestionDto.setNumber(formQuestion.getNumber());
		formQuestionDto.setId(formQuestion.getId());
		return formQuestionDto;
	}

	public FormDto makeFormDto(Form form) {
		FormDto formDto = new FormDto();
		formDto.setFormId(form.getId());
		formDto.setTitle(form.getTitle());
		List<FormQuestionDto> formQuestions = new ArrayList<>();
		for(FormQuestion formQuestion: form.getFormQuestions()) {
			formQuestions.add(makeFormQuestionDto(formQuestion));
		}
		formDto.setQuestions(formQuestions);
		return formDto;
	}

	public FormDto getFormById(int id) {
		Form form = formRepo.getFirstById(id);
		if (form == null) {
			return null;
		}
		return makeFormDto(form);
	}
}
