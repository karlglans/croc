package se.purple.croc.service;

import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.purple.croc.domain.Form;
import se.purple.croc.domain.FormQuestion;
import se.purple.croc.domain.Question;
import se.purple.croc.dto.FormDto;
import se.purple.croc.repository.FormQuestionRepository;
import se.purple.croc.repository.FromRepository;
import se.purple.croc.repository.QuestionRepository;
import se.purple.croc.service.exceptions.ServiceException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
public class FormService {

	@Autowired
	QuestionRepository questionRepo;

	@Autowired
	FromRepository formRepo;

	@Autowired
	FromRepository serviceRepo;

	@Autowired
	FormQuestionRepository formQuestionRepo;

	FormQuestion getFormQuestion(Form form, int questionId) throws ServiceException {
		Optional<FormQuestion> formQuestion = form.getFormQuestions()
				.stream().filter(q -> q.getQuestion().getId() == questionId).findFirst();
		if (!formQuestion.isPresent()) {
			throw new ServiceException("formQuestion is not found");
		}
		return formQuestion.get();
	}

	public Form getForm(int id) {
		return formRepo.findById(id).get();
	}

	// nyare. Går inte lika djupt ner i formDto
	public FormDto makeFormDtoShallow(Form from){
		FormDto formDto = new FormDto();
		from.setId(from.getId());
		formDto.setId(from.getId());
		formDto.setTitle(from.getTitle());
		formDto.setEditable(from.isEditable());
		return formDto;
	}

	public FormDto getFromDto(int id) {
		return(makeFormDtoShallow(getForm(id)));
	}

	public List<FormDto> getAllFormDtos() {
		var forms = formRepo.findAll();
		List<FormDto> formDto = new ArrayList<>();
		for (Form form : forms) {
			formDto.add(makeFormDtoShallow(form));
		}
		return formDto;
	}

	Form getFormBySurveyId(int serviceId) {
		return serviceRepo.getFromBySurveyId(serviceId);
	}

	public FormDto getFormDtoBySurveyId(int serviceId) {
		return makeFormDtoShallow(getFormBySurveyId(serviceId));
	}

	/*
		There should only be one form for this question
	 */
	public FormDto getSingleFormByQuestion(int questionId) {
		List<FormQuestion> formQuestions = formQuestionRepo.getFormQuestionsByQuestionId(questionId);
		if (formQuestions.size() != 1) {
			return null;
		}
		return makeFormDtoShallow(formQuestions.get(0).getForm());
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
		formQuestionRepo.save(formQuestion);
		return nextFormQuestionNumber;
	}

	public FormDto swapQuestionOnForm(final int formId, final int questionId, final int destSpotNumber) {
		Form form = getForm(formId);
		var questions = form.getFormQuestions();
		int srcSpotNumber = -1;
		FormQuestion destQuestion = null, srcQuestion = null;
		for (FormQuestion question : questions) {
			if (question.getNumber() == destSpotNumber){
				destQuestion = question;
			}
			if (question.getQuestion().getId() == questionId) {
				srcSpotNumber = question.getNumber();
				srcQuestion = question;
			}
			if (destQuestion != null && srcQuestion != null) {
				destQuestion.setNumber(srcSpotNumber);
				srcQuestion.setNumber(destSpotNumber);
				break;
			}
		}
		return makeFormDtoShallow(form);
	}

}
