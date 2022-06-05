package Survey;

import java.io.Serializable;

public class survey  implements Serializable {
	
	public int QuestionNumber;
	public String QuestionForm;
	public int getQuestionNumber() {
		return QuestionNumber;
	}
	public void setQuestionNumber(int questionNumber) {
		QuestionNumber = questionNumber;
	}
	public String getQuestionForm() {
		return QuestionForm;
	}
	public void setQuestionForm(String questionForm) {
		QuestionForm = questionForm;
	}
	public survey(int questionNumber, String questionForm) {
		super();
		QuestionNumber = questionNumber;
		QuestionForm = questionForm;
	}
	public survey(survey survey) {
		this.QuestionNumber=survey.getQuestionNumber();
		this.QuestionForm=survey.getQuestionForm();
	}
	@Override
	public String toString() {
		return "survey [QuestionNumber=" + QuestionNumber + ", QuestionForm=" + QuestionForm + "]";
	}
	

}
