package Coursework.Model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class Questions {

    private int questionID;
    private int quizID;
    private String question;

    public Questions(int questionID, int quizID, String question) {
        this.questionID = questionID;
        this.quizID = quizID;
        this.question = question;
    }

    public int getQuestionID() {
        return questionID;
    }

    public void setQuestionID(int questionID) {
        this.questionID = questionID;
    }

    public int getQuizID() {
        return quizID;
    }

    public void setQuizID(int quizID) {
        this.quizID = quizID;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public static void selectAll(List<Answers> targetList, DatabaseConnection database) {
        PreparedStatement statement = database.newStatement("SELECT * FROM Questions ORDER BY QuestionID");

        try {
            if (statement == null) {
                System.out.println("Statement is null");
                return;
            }

            ResultSet results = database.executeQuery(statement);

            if (results == null) {
                System.out.println("Results is null");
                return;
            }

            while (results.next()) {
                targetList.add(new Answers(results.getInt("QuestionID"), results.getInt("QuizID"), results.getString("Question")));
            }
        } catch (SQLException resultsException) {
            System.out.println("Database select all error: " + resultsException.getMessage());
        }
    }

}
