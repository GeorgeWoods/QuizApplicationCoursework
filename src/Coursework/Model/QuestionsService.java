package Coursework.Model;

import javax.xml.crypto.Data;
import java.sql.Array;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class QuestionsService {

    public static DatabaseConnection database;

    public static void setDatabaseConnection(DatabaseConnection d) {
        database = d;
    }

    public static HashMap<Integer, Integer> getCorrectAnswer(int QuizID) {
        HashMap<Integer, Integer> questionsToAnswers = new HashMap<>();

        PreparedStatement statement = database.newStatement("SELECT * FROM Answers INNER JOIN Questions ON Questions.QuestionID = Answers.QuestionID WHERE Correct = 1 AND Questions.QuizID = ?");
        try {
            if (statement == null) {
                System.out.println("Statement is null");
                return questionsToAnswers;
            }

            statement.setInt(1, QuizID);

            ResultSet results = database.executeQuery(statement);

            if (results == null) {
                System.out.println("Results is null");
                return questionsToAnswers;
            }


            while (results.next()) {
                int questionId = results.getInt("QuestionID");
                int answerId = results.getInt("AnswerID");
                questionsToAnswers.put(questionId, answerId);
            }
        } catch (SQLException resultsException) {
            System.out.println("Database select all error: " + resultsException.getMessage());
        }
        return questionsToAnswers;
    }

    public static void selectQuizById(int QuizID, ArrayList<Answers[]> answers, ArrayList<Questions> questions) {
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
                int questionId = results.getInt("QuestionID");
                int quizId = results.getInt("QuizID");
                String question = results.getString("Question");
                int answerId = results.getInt("AnswerID");
                String answer = results.getString("Answer");

                // if the question hasn't been added to the questions list yet then add it
                if (questions.size() != questionId) {
                    Questions questionObject = new Questions(questionId, quizId, question);
                    questions.add(questionObject);
                }

                if(answers.size() != questionId) {
                    answers.add(new Answers[4]);
                }

                Answers[] answersArray = answers.get(questionId - 1);
                answersArray[answerId % 4] = new Answers(answerId, questionId, answer);
                answers.set(questionId - 1, answersArray);


                //                if(answers.size() != questionId) {
//                    Answers[] answersArray = new Answers[4];
//                    answersArray[answerId - 1] = new Answers(answerId, questionId, answer);
//                    answers.add(answersArray);
//                } else {
//                    Answers[] answersArray = answers.get(questionId - 1);
//                    answersArray[answerId - 1] = new Answers(answerId, questionId, answer);
//                    answers.set(questionId - 1, answersArray);
//                }

            }
        } catch (SQLException resultsException) {
            System.out.println("Database select all error: " + resultsException.getMessage());
        }
    }

}
