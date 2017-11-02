package Coursework.Model;

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
}
