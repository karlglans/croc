package se.purple.croc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.purple.croc.domain.Question;
import se.purple.croc.repository.QuestionRepository;
import se.purple.croc.service.exceptions.ServiceException;

import java.util.List;

//@Service
//public class GroupQuestionService {
//
//	@Autowired
//	FormQuestionGroupRepository formQuestionGroupRepository;
//
//	@Autowired
//	QuestionRepository questionRepository;
//
//	int getTopQuestionNumber(List<GroupQuestion> groupQuestionList) {
//		int max = 0;
//		for (GroupQuestion gq : groupQuestionList){
//			if(gq.getNumber() > max) {
//				max = gq.getNumber();
//			}
//		}
//		return max;
//	}
//
//	@Transactional
//	public void addQuestionToQuestionGroup(Integer questionId, Integer groupQuestionId) throws ServiceException {
//
//		FormQuestionGroup fqg =  formQuestionGroupRepository.getFirstById(groupQuestionId);
//
//		if (fqg == null) {
//			throw new ServiceException("formQuestionGroup not found");
//		}
//
//		Question question = questionRepository.getFirstById(questionId);
//
//		if (question == null) {
//			throw new ServiceException("question not found");
//		}
//
//		List<GroupQuestion> groupQuestionList = fqg.getGroupQuestions();
//		int max = getTopQuestionNumber(groupQuestionList);
//
//		int newTopValue = max + 1;
//
//	}
//}
