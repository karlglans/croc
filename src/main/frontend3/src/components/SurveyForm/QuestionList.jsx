import React from 'react';

import AnswerableQuestion from './AnswerableQuestion';

const attachAnswerToQuestion = (questions, answers) => {
  let questionMap = {};
  questions.forEach(q => questionMap[q.id] = q)
  answers.forEach( a => {
    if (a.questionId && questionMap[a.questionId]) {
      questionMap[a.questionId].answer = a;
    }
  });
}

class QuestionList extends React.Component {
  constructor(props) {
    super(props);
    this.state = {}
  }

  // check if all questions was answered
  hasOtherMissingAnswers(idOfJustAnsweredQuestion) {
    let unansweredQuestionFound = false;
    this.props.survey.form.questions.forEach( question => {
      if (!question.answer && question.id !== idOfJustAnsweredQuestion) {
        unansweredQuestionFound = true;
      } 
    });
    return unansweredQuestionFound;
  }

  // TODO survey contains survey id
  render() {
    const { survey, answers } = this.props;
    const questions = survey.form.questions;
    attachAnswerToQuestion(questions, answers);
    return (
      <div>
        {questions.map(question => (
          <AnswerableQuestion
            hasOtherMissingAnswers={this.hasOtherMissingAnswers.bind(this)}
            question={question}
            surveyId={survey.id}
            survey={survey}
            key={question.id} />
        ))}
      </div>
    );
  }
}

export default QuestionList;
