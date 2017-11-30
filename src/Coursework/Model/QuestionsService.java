package Coursework.Model;

import javax.xml.crypto.Data;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class QuestionsService {

    public static DatabaseConnection database;

    public static void setDatabaseConnection(DatabaseConnection d) {
        database = d;
    }

    public static void selectAnswersFromQuizID(int QuizID, List<Answers> answersList) {
        PreparedStatement statement = database.newStatement("SELECT * FROM Questions, Answers WHERE Questions.QuizID = ? AND Questions.QuestionID = Answers.QuestionID");

        try {
            if (statement == null) {
                System.out.println("Statement is null");
                return;
            }

            statement.setInt(1, QuizID);

            ResultSet results = database.executeQuery(statement);

            if (results == null) {
                System.out.println("Results is null");
                return;
            }

            while (results.next()) {
                //Questions question = new Questions(results.getInt("QuestionID"), QuizID, results.getString("Quesiton"));
                Answers answer = new Answers(results.getInt("AnswerID"), results.getInt("QuestionID"), results.getString("Answer"));
                answersList.add(answer);
            }
        } catch (SQLException resultsException) {
            System.out.println("Database select all error: " + resultsException.getMessage());
        }
    }

}
