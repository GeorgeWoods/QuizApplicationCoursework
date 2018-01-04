package Coursework;

import Coursework.Model.Answers;
import Coursework.Model.Questions;
import Coursework.Model.QuestionsService;
import javafx.event.ActionEvent;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Separator;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Quiz {

    //String[] questions = { "This is a very good question yes men?", "Yes?" };
    //String[][] answers = { {"It's good", "Yeah I guess so", "Okay mate", "Suicide is inevitable"}, {"1", "2", "3", "4"} };
    ArrayList<Questions> questions = new ArrayList<>();
    ArrayList<Answers[]> answers = new ArrayList<>();

    Label lblQuestion;
    Label lblQuestionIndex;
    int questionIndex = 0;

    ArrayList<Integer> theirAnswers = new ArrayList<>();

    Scene scene;
    Button answer1;
    Button answer2;
    Button answer3;
    Button answer4;
    ProgressBar progressBar;

    long timeStart = 0;
    long timeEnd = 0;

    Timer timer;

    public Quiz(int QuizID) {
        Pane root = new Pane();

        QuestionsService.selectQuizById(QuizID, answers, questions);

        this.scene = new Scene(root, 1024, 768);
        this.scene.getStylesheets().add(getClass().getResource("Resources/app.css").toExternalForm());

        Button home = new Button("Home");
        home.setPrefSize(200, 60);
        home.setOnAction((ActionEvent ae) -> goToHome(ae));
        root.getChildren().add(home);
        home.setLayoutX(815);
        home.setLayoutY(20);
        home.setId("button");

        Label label = new Label();
        label.setText("Username:");
        label.setPrefSize(200, 30);
        label.setLayoutX(815);
        label.setLayoutY(90);
        root.getChildren().add(label);

        Label lblUsername = new Label();
        lblUsername.setText(Main.username);
        lblUsername.setLayoutX(815);
        lblUsername.setLayoutY(120);
        lblUsername.setFont(new Font(25.0));
        root.getChildren().add(lblUsername);

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

        progressBar = new ProgressBar();
        progressBar.setPrefSize(600, 50);
        progressBar.setProgress(0.5);
        progressBar.setLayoutX(30);
        progressBar.setLayoutY(360);
        root.getChildren().add(progressBar);

        setQuestion(0);
    }



    public static void goToHome(ActionEvent ae) {
        Main.goToHome(ae);
    }

    void setAnswer(int answerIndex) {
        theirAnswers.add(answerIndex);
        questionIndex += 1;

        System.out.println(questionIndex);
        if (questionIndex >= questions.size()) {
            System.out.println("The quiz is over");
            // logic to write when the quiz is over
        } else {
            setQuestion(questionIndex);
        }
    }

    void setQuestion(int questionIndex) {
        lblQuestionIndex.setText("Question #" + String.valueOf(questionIndex + 1));

        Questions question = questions.get(questionIndex);
        lblQuestion.setText(question.getQuestion());

        timeStart = System.currentTimeMillis();
        timeEnd = System.currentTimeMillis() + (1000 * 10);

        populateAnswers(questionIndex);

        if (timer != null) {
            timer.cancel();
        }

        timer = new Timer();

        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                double timeLeft = timeEnd - System.currentTimeMillis();

                double percentage = 1 - (timeLeft / 10000);
                progressBar.setProgress(percentage);

//                if (timeLeft < 0) {
//                    setAnswer(0);
//                }
            }
        }, 0, 1);
    }

    void populateAnswers(int questionIndex) {
        Answers[] answerArray = answers.get(questionIndex);

        answer1.setText(answerArray[0].getAnswer());
        answer2.setText(answerArray[1].getAnswer());
        answer3.setText(answerArray[2].getAnswer());
        answer4.setText(answerArray[3].getAnswer());
    }

    Scene getScene() {
        return this.scene;
    }

}
