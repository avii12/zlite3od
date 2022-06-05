package Survey;

import java.io.Serializable;

public class survey  implements Serializable {
	
	public int QuestionNumber;
	public String QuestionForm;
	public String SurveyID;
	public survey(survey survey) {
		this.QuestionNumber=survey.getQuestionNumber();
		this.QuestionForm=survey.getQuestionForm();
		this.SurveyID=survey.getSurveyID();
	}
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
	public String getSurveyID() {
		return SurveyID;
	}
	public void setSurveyID(String surveyID) {
		SurveyID = surveyID;
	}
	@Override
	public String toString() {
		return "survey [QuestionNumber=" + QuestionNumber + ", QuestionForm=" + QuestionForm + ", SurveyID=" + SurveyID
				+ "]";
	}
	public survey(int questionNumber, String questionForm, String surveyID) {
		super();
		QuestionNumber = questionNumber;
		QuestionForm = questionForm;
		SurveyID = surveyID;
	}
	
	
	

}
