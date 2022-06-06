package Survey;

import java.io.Serializable;



/**
 * 
 * class description:
 * this class is the survey answer class contains all the survey answers
 *  
 * @author Seren ,Hanany.
 * @author Shoroq ,Heib.
 * @author maisalon ,safory. 
 *
 */

public class SurveyAnswers implements Serializable {
	public String SurveyID;
	public String CustomerID;
	public String QuestionNumber;
	public String QuestionAnswer;

	/**
	 * return the details in string
	 */
	@Override
	public String toString() {
		return "SurveyAnswers [SurveyID=" + SurveyID + ", CustomerID=" + CustomerID + ", QuestionNumber="
				+ QuestionNumber + ", QuestionAnswer=" + QuestionAnswer + "]";
	}

	/**
	 * @param surveyID
	 * @param customerID
	 * @param questionNumber
	 * @param questionAnswer
	 */
	public SurveyAnswers(String surveyID, String customerID, String questionNumber, String questionAnswer) {
		super();
		SurveyID = surveyID;
		CustomerID = customerID;
		QuestionNumber = questionNumber;
		QuestionAnswer = questionAnswer;
	}

	/**
	 * @param surveyAnswers
	 */
	public SurveyAnswers(SurveyAnswers surveyAnswers) {
		this.SurveyID = surveyAnswers.getSurveyID();
		this.CustomerID = surveyAnswers.getCustomerID();
		this.QuestionNumber = surveyAnswers.getQuestionNumber();
		this.QuestionAnswer = surveyAnswers.getQuestionAnswer();
	}
	
	

	/**
	 * setters and getters of the survey answers
	 */
	
	
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
