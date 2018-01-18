package Coursework;

import Coursework.Model.Answers;
import Coursework.Model.DatabaseConnection;
import Coursework.Model.QuestionsService;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.Optional;


public class Main extends Application {

    static Stage stage;
    static Scene scene;
    static String username = "";

    enum PAGES {
        HOME,
    }

    PAGES currentPage = PAGES.HOME;
    DatabaseConnection databaseConnection = new DatabaseConnection("QuizApplication.db");

    @Override
    public void start(Stage stage) throws Exception {
        QuestionsService.setDatabaseConnection(databaseConnection);
        Image smiley = new Image("Coursework/Resources/unnamed.png");

        Pane root = new Pane();
        stage.getIcons().add(smiley);

        scene = new Scene(root, 1024, 768);
        scene.getStylesheets().add(getClass().getResource("Resources/app.css").toExternalForm());

        stage.setTitle("Quizzy McQuiz Face");
        stage.setScene(scene);
        stage.setResizable(false);

        Button home = new Button("Home");
        home.setPrefSize(200, 60);
        home.setOnAction((ActionEvent ae) -> goToHome(ae));
        root.getChildren().add(home);
        home.setLayoutX(815);
        home.setLayoutY(20);
        home.setId("button");

        Label label = new Label();
        label.setText("Enter username:");
        label.setPrefSize(200, 30);
        label.setLayoutX(815);
        label.setLayoutY(90);
        root.getChildren().add(label);

        ImageView iv = new ImageView(smiley);
        iv.setFitWidth(150);
        iv.setFitHeight(150);
        iv.setX(30);
        iv.setY(20);
        root.getChildren().add(iv);

        TextField txtUsername = new TextField();
        txtUsername.setPrefSize(200, 30);
        txtUsername.setLayoutX(815);
        txtUsername.setLayoutY(120);
        root.getChildren().add(txtUsername);
        txtUsername.setOnAction((ActionEvent) -> {
            if (!txtUsername.equals("")) {
                username = txtUsername.getText();
                root.getChildren().remove(txtUsername);

                Label lblUsername = new Label();
                lblUsername.setText(username);
                lblUsername.setLayoutX(815);
                lblUsername.setLayoutY(120);
                lblUsername.setFont(new Font(25.0));
                root.getChildren().add(lblUsername);
            }
        });

        VBox center = new VBox(20);
        Button btnQuiz1 = new Button("Quiz 1");
        btnQuiz1.setPrefSize(600, 40);
        btnQuiz1.setOnAction((ActionEvent ae) -> clickQuiz1(ae));
        root.getChildren().add(btnQuiz1);
        btnQuiz1.setLayoutX(30);
        btnQuiz1.setLayoutY(200);

        Button btnQuiz2 = new Button("Quiz 2");
        btnQuiz2.setPrefSize(600, 40);
        btnQuiz2.setOnAction((ActionEvent ae) -> clickQuiz2(ae));
        root.getChildren().add(btnQuiz2);
        btnQuiz2.setLayoutX(30);
        btnQuiz2.setLayoutY(250);

        Button btnQuiz3 = new Button("Quiz 3");
        btnQuiz3.setPrefSize(600, 40);
        btnQuiz3.setOnAction((ActionEvent ae) -> clickQuiz3(ae));
        root.getChildren().add(btnQuiz3);
        btnQuiz3.setLayoutX(30);
        btnQuiz3.setLayoutY(300);
        //center.getChildren(btnQuiz1, btnQuiz2, btnQuiz3);


        stage.setOnCloseRequest((WindowEvent we) -> exitPrompt(we));

        stage.show();

        ArrayList<Answers> list = new ArrayList<>();
        Answers.selectAll(list, databaseConnection);


        Answers test = Answers.selectById(1, databaseConnection);
        System.out.println(test);

        this.stage = stage;
    }

    public static void exitPrompt(WindowEvent we) {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Are you sure you want to exit?");

        Optional result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            System.exit(0);
        } else {
            we.consume();
        }

    }

    public static void goToHome(ActionEvent ae) {
        stage.setScene(scene);
    }

    public void clickQuiz1(ActionEvent ae) {
        System.out.println("Click Quiz 1");
        Quiz quiz = new Quiz(1);
        stage.setScene(quiz.getScene());
    }

    public void clickQuiz2(ActionEvent ae) {
        System.out.println("Click Quiz 2");
    }

    public void clickQuiz3(ActionEvent ae) {
        System.out.println("Click Quiz 3");
    }

    public static void main(String[] args) {
        launch(args);
    }

}