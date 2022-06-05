package queries;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

import Orders.Branch;
import Report.ReportType;
import Report.Reports;
import RequestsAndResponses.FullMessage;
import RequestsAndResponses.Response;
import Survey.SurveyAnswers;
import Survey.survey;

public class SurveyQuery {

	public static FullMessage GetSurveyFromDB(FullMessage messageFromClient)throws SQLException {
		ArrayList<survey> SurveyQ = new ArrayList<survey>();
		ResultSet rs = mainQuery.SelectAllProductsFromDB("surveyquestions");
		try {
			if (!rs.isBeforeFirst()) {
				messageFromClient.setResponse(Response.NO_SURVEY);
				messageFromClient.setObject(null);
				return messageFromClient;
			}

			while (rs.next()) {
				survey Question =convertToSurvey(rs) ;
				SurveyQ.add(Question);
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		messageFromClient.setResponse(Response.SURVEY_FOUND);
		messageFromClient.setObject(SurveyQ);
		return messageFromClient;
	}
	
	private static survey convertToSurvey(ResultSet rs) {
		try {
			int QuestionNumber = rs.getInt(1);
			String QuestionForm = rs.getString(2);
			return new survey(QuestionNumber, QuestionForm);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static FullMessage SetAnswersToDB(FullMessage messageFromClient)throws SQLException {
		String[] answerANDid = (String[]) messageFromClient.getObject();
		
		String CustomerID=answerANDid[0];
		
		for(int i=1;i<7;i++) {
			int QN=i;
			String QA=answerANDid[i];
		try {
			mainQuery.InsertOneRowIntosurveyAnswersTable(QN,QA,CustomerID);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//mainQuery.DeleteRowFromDB1("surveyanswers","CustomerID='1'");
		}
		messageFromClient.setResponse(Response.SET_ANSWER_DONE);
		return messageFromClient;
	}


}
