package com.company;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        Pane root = new Pane();

        Scene scene = new Scene(root, 1024, 768);

        scene.getStylesheets().add(getClass().getResource("app.css").toExternalForm());

        stage.setTitle("Hello World");
        stage.setScene(scene);
        stage.setResizable(false);

        Button myButton = new Button("Home");
        myButton.setPrefSize(110, 60);
        myButton.setOnAction((ActionEvent ae) -> goToHome(ae));
        root.getChildren().add(myButton);
        myButton.setLayoutX(900);
        myButton.setLayoutY(20);
        myButton.setId("button");

        Label label = new Label();
        label.setText("Enter username:");
        label.setPrefSize(200, 30);
        label.setLayoutX(815);
        label.setLayoutY(90);
        root.getChildren().add(label);


        TextField text = new TextField();
        text.setPrefSize(200, 30);
        text.setLayoutX(815);
        text.setLayoutY(120);
        root.getChildren().add(text);

        stage.show();
    }

    public void goToHome(ActionEvent ae) {

    }

    public static void main(String[] args) {
        launch(args);
    }
}