package Coursework.Model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class Answers {

    private int answerID;
    private int questionID;
    private String answer;

    public Answers(int answerID, int questionID, String answer) {
        this.answerID = answerID;
        this.questionID = questionID;
        this.answer = answer;
    }

    public int getAnswerID() {
        return answerID;
    }

    public void setAnswerID(int answerID) {
        this.answerID = answerID;
    }

    public int getQuestionID() {
        return questionID;
    }

    public void setQuestionID(int questionID) {
        this.questionID = questionID;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    @Override
    public String toString() {
        return "AnswerID: " + this.getAnswerID() + ", QuestionID: " + this.getQuestionID() + ", Answer: " + this.getAnswer();
    }

    public static void selectAll(List<Answers> targetList, DatabaseConnection database) {
        PreparedStatement statement = database.newStatement("SELECT * FROM Answers ORDER BY AnswerID");

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
                targetList.add(new Answers(results.getInt("AnswerID"), results.getInt("QuestionID"), results.getString("Answer")));
            }
        } catch (SQLException resultsException) {
            System.out.println("Database select all error: " + resultsException.getMessage());
        }
    }

    public static Answers selectById(int id, DatabaseConnection database) {
        PreparedStatement statement = database.newStatement("SELECT * FROM Answers WHERE AnswerID = ? ORDER BY AnswerID");

        Answers answer = null;
        try {
            statement.setInt(1, id);

            if (statement == null) {
                return answer;
            }

            ResultSet results = database.executeQuery(statement);

            if (results == null) {
                return answer;
            }

            while (results.next()) {
                return new Answers(results.getInt("AnswerID"), results.getInt("QuestionID"), results.getString("Answer"));
            }
        } catch (SQLException resultsException) {
            System.out.println("Database select all error: " + resultsException.getMessage());
        }

        return answer;
    }

    public static void save(Answers itemToSave, DatabaseConnection database) {

        Answers existingItem = selectById(itemToSave.getAnswerID(), database);

        try {
            if (existingItem == null) {
                PreparedStatement statement = database.newStatement("INSERT INTO Answers (AnswerID, QuestionID, Answer) VALUES (?, ?, ?))");
                statement.setInt(1, itemToSave.getAnswerID());
                statement.setInt(2, itemToSave.getQuestionID());
                statement.setString(3, itemToSave.getAnswer());
                database.executeUpdate(statement);
            }
            else {
                PreparedStatement statement = database.newStatement("UPDATE Answers SET AnswerID = ?, QuestionID = ?, Answer = ? WHERE id = ?");
                statement.setInt(1, itemToSave.getAnswerID());
                statement.setInt(2, itemToSave.getQuestionID());
                statement.setString(3, itemToSave.getAnswer());
                database.executeUpdate(statement);
            }
        } catch (SQLException resultsException) {
            System.out.println("Database saving error: " + resultsException.getMessage());
        }
    }
}
