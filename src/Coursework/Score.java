package Coursework;

import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

public class Score {

    Scene scene;
    public Score(int score) {
        Pane root = new Pane();

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
        label.setText("Your score is:\n" + score);
        label.setFont(Font.font ("Verdana", 100));
        label.setTextAlignment(TextAlignment.CENTER);
        label.setLayoutX(200);
        label.setLayoutY(200);
        root.getChildren().add(label);

    }



    public static void goToHome(ActionEvent ae) {
        Main.goToHome(ae);
    }

    public void displayScoreScene() {
        Main.stage.setScene(this.scene);
    }

}
