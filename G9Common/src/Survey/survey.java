package Survey;

import java.io.Serializable;

/**
 * 
 * class description:
 * this class is the survey  class contains all the survey information
 *  
 * @author Seren ,Hanany.
 * @author Shoroq ,Heib.
 * @author maisalon ,safory. 
 *
 */

public class survey  implements Serializable {
	
	/**
	 * this is the 	Question number
	 */
	public int QuestionNumber;
	
	/**
	 * this is the question form
	 */
	public String QuestionForm;
	/**
	 * survey id
	 */
	public String SurveyID;
	
	/**
	 * @param survey
	 */
	public survey(survey survey) {
		this.QuestionNumber=survey.getQuestionNumber();
		this.QuestionForm=survey.getQuestionForm();
		this.SurveyID=survey.getSurveyID();
	}
	/**
	 * setters and getters
	 * 
	 */
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
	
	public void setSurveyID(String surveyID) {
		SurveyID = surveyID;
	}
	@Override
	public String toString() {
		return "survey [QuestionNumber=" + QuestionNumber + ", QuestionForm=" + QuestionForm + ", SurveyID=" + SurveyID
				+ "]";
	}
	/**
	 * @param questionNumber
	 * @param questionForm
	 * @param surveyID
	 */
	public survey(int questionNumber, String questionForm, String surveyID) {
		super();
		QuestionNumber = questionNumber;
		QuestionForm = questionForm;
		SurveyID = surveyID;
	}
	public String getSurveyID() {
		return SurveyID;
	}
	
	
	

}
