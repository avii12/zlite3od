package Survey;

import java.io.Serializable;

public class SurveyAnswers implements Serializable {
	public String SurveyID;
	public String CustomerID;
	public String QuestionNumber;
	public String QuestionAnswer;

	@Override
	public String toString() {
		return "SurveyAnswers [SurveyID=" + SurveyID + ", CustomerID=" + CustomerID + ", QuestionNumber="
				+ QuestionNumber + ", QuestionAnswer=" + QuestionAnswer + "]";
	}

	public SurveyAnswers(String surveyID, String customerID, String questionNumber, String questionAnswer) {
		super();
		SurveyID = surveyID;
		CustomerID = customerID;
		QuestionNumber = questionNumber;
		QuestionAnswer = questionAnswer;
	}

	public SurveyAnswers(SurveyAnswers surveyAnswers) {
		this.SurveyID = surveyAnswers.getSurveyID();
		this.CustomerID = surveyAnswers.getCustomerID();
		this.QuestionNumber = surveyAnswers.getQuestionNumber();
		this.QuestionAnswer = surveyAnswers.getQuestionAnswer();
	}

	public String getSurveyID() {
		return SurveyID;
	}

	public void setSurveyID(String surveyID) {
		SurveyID = surveyID;
	}

	public String getCustomerID() {
		return CustomerID;
	}

	public void setCustomerID(String customerID) {
		CustomerID = customerID;
	}

	public String getQuestionNumber() {
		return QuestionNumber;
	}

	public void setQuestionNumber(String questionNumber) {
		QuestionNumber = questionNumber;
	}

	public String getQuestionAnswer() {
		return QuestionAnswer;
	}

	public void setQuestionAnswer(String questionAnswer) {
		QuestionAnswer = questionAnswer;
	}

}
