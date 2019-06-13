package se.purple.croc.resolver;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import se.purple.croc.domain.Form;
import se.purple.croc.dto.*;
import se.purple.croc.repository.FromRepository;
import se.purple.croc.service.*;
import se.purple.croc.service.exceptions.ServiceException;

@Component
public class Mutation implements GraphQLMutationResolver {

	@Autowired
	private FormService formService;

	@Autowired
	private FromRepository fromRepo;

	@Autowired
	private UserGroupService userGroupService;

	@Autowired
	private SurveyService surveyService;

	@Autowired
	private AnswerService answerService;

	@Autowired
	private AuthService authService;

	@Autowired
	private QuestionService questionService;

	public QuestionDto addQuestion(final InputQuestionDto inQuestion) {
		return questionService.storeQuestion(inQuestion);
	}

	public FormDto updateQuestion(final InputQuestionDto inQuestion) {
		// TODO: validate inQuestion. There should be an id in it
		// NOTE: for convenience this action is now returning a form, a question would be more logic
		questionService.updateQuestion(inQuestion);
		return formService.getSingleFormByQuestion(inQuestion.getId());
	}

	public FormDto addQuestionToForm(final Integer formId, final Integer questionId) {
		int formChangedId = formService.addQuestionToForm(questionId, formId);
		if (formChangedId == 0) {
			return null;
		}

		// should not return a questionDto
		return formService.getFromDto(formId);
	}

	public FormDto removeQuestionFromForm(final Integer questionId, final Integer formId) {
		return formService.removeQuestionFromForm(questionId, formId);
		// return new FormDto();
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
		QuestionDto questionDto = questionService.storeQuestion(inQuestion);
		formService.addQuestionToForm(questionDto.getId(), formId);
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
		return answerService.updateAnswer(surveyId, authService.getPrincipal(), questionId, value);
	}

	// swapQuestionOnForm(formId: Id!, questionId: Id!, destSpotNumber: Int!): Form
	public FormDto swapQuestionOnForm(int formId, int questionId, int destSpotNumber) {
		return formService.swapQuestionOnForm(formId, questionId, destSpotNumber);
	}

}
