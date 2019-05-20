mutation {
  addQuestion(input: {
    text: "aaaa"
  }) {
    id
  }
}

{
	forms{
		title
        questions{
            id
            text
        }
	}
}

{
    survey (id: 1)  {
        id
        participants {
            userId
            answers {
                value
                participantId
                questionId
            }
        }
        form {
            id
            questions {
                id
            }
        }
	}
}

mutation {
  addQuestion( input: {
    text: "aaaa"
  }) {
    id
  }
}