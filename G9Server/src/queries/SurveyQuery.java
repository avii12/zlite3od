package queries;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

import org.w3c.dom.Document;

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
		SurveyAnswers answerANDid = (SurveyAnswers) messageFromClient.getObject();
		System.out.println(answerANDid.toString());
		System.out.println("server");
		try {
			mainQuery.InsertOneRowIntosurveyAnswersTable(answerANDid);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		messageFromClient.setResponse(Response.SET_ANSWER_DONE);
		return messageFromClient;
	
	}

	public static FullMessage UploadFile(FullMessage messageFromClient)throws SQLException {
		FileOutputStream output=null;
		
		
		return null;
	}
	
	public static void getByteArrayFromFile(final Document handledDocument) throws IOException {
	 
		
		
	}

}
