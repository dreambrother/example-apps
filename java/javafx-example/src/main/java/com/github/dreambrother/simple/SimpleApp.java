package com.github.dreambrother.simple;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class SimpleApp extends Application {

    @Override
    public void start(Stage stage) {
        // Create an Ellipse and set fill color
        var ellipse = new Ellipse(110, 70);
        ellipse.setFill(Color.LIGHTBLUE);

        // Create a Text shape with font and size
        var text = new Text("My Shapes");
        text.setFont(new Font("Arial Bold", 24));

        var stackPane = new StackPane();
        stackPane.getChildren().addAll(ellipse, text);

//        var pane = new AnchorPane();
//        pane.getChildren().add(stackPane);
//        AnchorPane.setLeftAnchor(stackPane, 0.0);
//        AnchorPane.setRightAnchor(stackPane, 0.0);
//        AnchorPane.setBottomAnchor(stackPane, 20.0);

        var pane = new BorderPane();
        var colorLabel = new Label("Color: Lightblue");
        colorLabel.setFont(new Font("Verdana", 18));
        pane.setTop(colorLabel);
        pane.setCenter(stackPane);
        BorderPane.setAlignment(colorLabel, Pos.CENTER);
        BorderPane.setMargin(colorLabel, new Insets(20,10,5,10));

        var scene = new Scene(pane, 350, 230, Color.LIGHTYELLOW);

        stage.setTitle("MyShapes with JavaFX");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
