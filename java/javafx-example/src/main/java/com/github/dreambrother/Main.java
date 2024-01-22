package com.github.dreambrother;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application {

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

        var scene = new Scene(stackPane, 350, 230, Color.LIGHTYELLOW);

        stage.setTitle("MyShapes with JavaFX");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
