package se.purple.croc.resolver;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import graphql.ExceptionWhileDataFetching;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import se.purple.croc.domain.Form;
import se.purple.croc.domain.Question;
import se.purple.croc.dto.*;
import se.purple.croc.repository.FromRepository;
import se.purple.croc.repository.QuestionRepository;
//import se.purple.croc.resolver.exceptions.MissingData;
import se.purple.croc.service.AnswerService;
import se.purple.croc.service.FormService;
import se.purple.croc.service.SurveyService;
import se.purple.croc.service.UserGroupService;
import se.purple.croc.service.exceptions.ServiceException;

import java.util.List;

@Component
public class Mutation implements GraphQLMutationResolver {

	@Autowired
	private QuestionRepository questionRepo;

	@Autowired
	private FormService formService;

	@Autowired
	FromRepository fromRepo;

	@Autowired
	UserGroupService userGroupService;

	@Autowired
	SurveyService surveyService;

	@Autowired
	AnswerService answerService;

	public QuestionDto addQuestion(final InputQuestionDto inQuestion) {
		Question question = new Question();
		question.setText(inQuestion.getText());
		questionRepo.save(question);
		QuestionDto questionDto = new QuestionDto();
		questionDto.copy(question);
		return questionDto;
	}

	public FormDto addQuestionToForm(final Integer formId, final Integer questionId) {
		int formChangedId = formService.addQuestionToForm(questionId, formId);
		if (formChangedId == 0) {
			return null;
		}

		// should not return a questionDto
		return formService.getFromDto(formId);
	}

	public FormDto createForm(String title) {
		Form form = new Form();
		form.setTitle(title);
		fromRepo.save(form);
		return formService.makeFormDtoShallow(form);
	}

	public SurveyDto createSurvey(int formId, String name) {
		return surveyService.createSurvey(formId, name);
	}

	/**
	 *  mutation($inquest: InputQuestion!, $formId: Int!) {
	 * 		createFormQuestion(input: $inquest, formId: $formId){ ... }
	 * 	}
	 */
	public QuestionDto createFormQuestion(InputQuestionDto inQuestion, final Integer formId) {
		Question question = new Question();
		question.setText(inQuestion.getText());
		questionRepo.save(question);
		formService.addQuestionToForm(question.getId(), formId);
		QuestionDto questionDto = new QuestionDto();
		questionDto.copy(question);
		return questionDto;
	}

	public UserGroupDto createUserGroup(String name) {
		return userGroupService.createUserGroup(name);
	}

	public UserGroupDto addUserToGroup(final Integer userId, final Integer userGroupId) {
		return userGroupService.addUserToGroup(userId, userGroupId);
	}

	public boolean addUserGroupToSurvey(int userGroupId, int surveyId){
		return surveyService.addUsersToSurvey(userGroupId, surveyId);
	}

	public boolean startSurvey(int surveyId) {
		return surveyService.startSurvey(surveyId);
	}

	public AnswerDto updateAnswer(int surveyId, int userId, int questionId, int value) throws ServiceException {
//		try {
//			return answerService.updateAnswer(surveyId, userId, questionId, value);
//		} catch (ServiceException e) {
//			throw new MissingData(400, "missing answer");
//		} catch (Exception ex) {
//			throw new MissingData(400, "unknown exception when setting answer");
//		}
		return answerService.updateAnswer(surveyId, userId, questionId, value);
	}

}
