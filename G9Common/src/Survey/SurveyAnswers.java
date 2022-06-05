package Survey;

import java.io.Serializable;

public class SurveyAnswers implements Serializable {
	public int QuestionNumber;
	public String QuestionAnswer;
	public String CustomerID;
	
	@Override
	public String toString() {
		return "SurveyAnswers [QuestionNumber=" + QuestionNumber + ", QuestionAnswer=" + QuestionAnswer
				+ ", CustomerID=" + CustomerID + "]";
	}
	public SurveyAnswers(int questionNumber, String questionAnswer, String customerID) {
		super();
		QuestionNumber = questionNumber;
		QuestionAnswer = questionAnswer;
		CustomerID = customerID;
	}
	public int getQuestionNumber() {
		return QuestionNumber;
	}
	public void setQuestionNumber(int questionNumber) {
		QuestionNumber = questionNumber;
	}
	public String getQuestionAnswer() {
		return QuestionAnswer;
	}
	public void setQuestionAnswer(String questionAnswer) {
		QuestionAnswer = questionAnswer;
	}
	public String getCustomerID() {
		return CustomerID;
	}
	public void setCustomerID(String customerID) {
		CustomerID = customerID;
	}
	

}
