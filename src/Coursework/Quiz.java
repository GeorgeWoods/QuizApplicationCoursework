package Coursework;

import Coursework.Model.Answers;
import Coursework.Model.Questions;
import Coursework.Model.QuestionsService;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

import java.util.ArrayList;

public class Quiz {

    String[] questions = { "This is a very good question yes men?", "Yes?" };
    String[][] answers = { {"It's good", "Yeah I guess so", "Okay mate", "Suicide is inevitable"}, {"1", "2", "3", "4"} };

    Label lblQuestion;
    Label lblQuestionIndex;
    int questionIndex = 0;

    ArrayList<Integer> theirAnswers = new ArrayList<>();

    Scene scene;
    Button answer1;
    Button answer2;
    Button answer3;
    Button answer4;

    public Quiz() {
        Pane root = new Pane();

        this.scene = new Scene(root, 1024, 768);
        this.scene.getStylesheets().add(getClass().getResource("Resources/app.css").toExternalForm());

        lblQuestionIndex = new Label();
        lblQuestionIndex.setLayoutX(30);
        lblQuestionIndex.setLayoutY(15);
        root.getChildren().add(lblQuestionIndex);

        lblQuestion = new Label();
        //lblQuestion.setPrefSize(200, 30);
        lblQuestion.setLayoutX(30);
        lblQuestion.setLayoutY(30);
        lblQuestion.setFont(Font.font ("Verdana", 32));
        root.getChildren().add(lblQuestion);

        Separator sep = new Separator();
        sep.setOrientation(Orientation.HORIZONTAL);
        sep.setLayoutX(30);
        sep.setLayoutY(80);
        sep.setPrefWidth(650);
        root.getChildren().add(sep);

        answer1 = new Button();
        answer1.setLayoutX(30);
        answer1.setLayoutY(120);
        answer1.setPrefHeight(100);
        answer1.setPrefWidth(300);
        answer1.setOnAction(event -> {
            setAnswer(0);
        });
        root.getChildren().add(answer1);

        answer2 = new Button();
        answer2.setLayoutX(330);
        answer2.setLayoutY(120);
        answer2.setPrefHeight(100);
        answer2.setPrefWidth(300);
        answer2.setOnAction(event -> {
            setAnswer(1);
        });
        root.getChildren().add(answer2);

        answer3 = new Button();
        answer3.setLayoutX(30);
        answer3.setLayoutY(220);
        answer3.setPrefHeight(100);
        answer3.setPrefWidth(300);
        answer3.setOnAction(event -> {
            setAnswer(2);
        });
        root.getChildren().add(answer3);

        answer4 = new Button();
        answer4.setLayoutX(330);
        answer4.setLayoutY(220);
        answer4.setPrefHeight(100);
        answer4.setPrefWidth(300);
        answer4.setOnAction(event -> {
            setAnswer(3);
        });
        root.getChildren().add(answer4);

        setQuestion(0);

        ArrayList<Questions> x = new ArrayList<>();
        ArrayList<Answers> y = new ArrayList<>();
        QuestionsService.selectQuizById(1, x, y);
    }

    void setAnswer(int answerIndex) {
        theirAnswers.add(answerIndex);
        questionIndex += 1;

        if (questionIndex == questions.length) {
            System.out.println("The quiz is over");
            // logic to write when the quiz is over
        }

        setQuestion(questionIndex);
    }

    void setQuestion(int questionIndex) {
        lblQuestionIndex.setText("Question #" + String.valueOf(questionIndex + 1));
        lblQuestion.setText(questions[questionIndex]);
        populateAnswers(questionIndex);
    }

    void populateAnswers(int questionIndex) {
        answer1.setText(answers[questionIndex][0]);
        answer2.setText(answers[questionIndex][1]);
        answer3.setText(answers[questionIndex][2]);
        answer4.setText(answers[questionIndex][3]);
    }

    Scene getScene() {
        return this.scene;
    }

}
