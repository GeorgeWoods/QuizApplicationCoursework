package Coursework.Model;

public class QuizName {

     private int quizID;
     private String quizName;

    public QuizName(int quizID, String quizName) {
        this.quizID = quizID;
        this.quizName = quizName;
    }

    public int getQuizID() {
        return quizID;
    }

    public void setQuizID(int quizID) {
        this.quizID = quizID;
    }

    public String getQuizName() {
        return quizName;
    }

    public void setQuizName(String quizName) {
        this.quizName = quizName;
    }
}