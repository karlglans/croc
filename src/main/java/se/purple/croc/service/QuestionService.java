package se.purple.croc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.purple.croc.domain.FormQuestion;
import se.purple.croc.domain.Question;
import se.purple.croc.domain.QuestionType;
import se.purple.croc.dto.InputQuestionDto;
import se.purple.croc.dto.QuestionDto;
import se.purple.croc.repository.QuestionRepository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class QuestionService {
	@Autowired
	QuestionRepository questionRepo;

	/**
	 * @param formId
	 * @return List<QuestionDto> questions will be sorted in number order
	 */
	public List<QuestionDto> getQuestionsByForm(int formId) {

		class QuestionNumberComparator implements Comparator<FormQuestion> {
			public int compare(FormQuestion a, FormQuestion b) {
				return a.getNumber() - b.getNumber();
			}
		}

		// TODO: Check if this can be more optimized.
		List<FormQuestion> questionList = questionRepo.getQuestionsByFormId(formId);
		questionList.sort(new QuestionNumberComparator());
		List<QuestionDto> questions = new ArrayList<>();
		for (FormQuestion formQuestion: questionList) {
			QuestionDto questionDto = new QuestionDto();
			questionDto.copy(formQuestion.getQuestion()); // maybe move to service
			questions.add(questionDto);
		}

		return questions;
	}

	Question findQuestion(int id) {
		return questionRepo.getFirstById(id);
	}

	public QuestionDto updateQuestion(InputQuestionDto inputQuestion) {
		Question question = findQuestion(inputQuestion.getId());
		question.setText(inputQuestion.getText());
		// since questionType is optional
		if (inputQuestion.getQuestionType() != null) {
			question.setQuestionType(inputQuestion.getQuestionType());
		}
		return makeDto(question);
	}

	private QuestionDto makeDto(Question question) {
		QuestionDto questionDto = new QuestionDto();
		questionDto.setId(question.getId());
		questionDto.setText(question.getText());
		questionDto.setQuestionType(question.getQuestionType());
		return questionDto;
	}

	public QuestionDto storeQuestion(InputQuestionDto inputQuestionDto) {
		Question question = new Question();
		question.setText(inputQuestionDto.getText());
		QuestionType qt = inputQuestionDto.getQuestionType();
		question.setQuestionType(qt);
		questionRepo.save(question);
		return makeDto(question);
	}
}
