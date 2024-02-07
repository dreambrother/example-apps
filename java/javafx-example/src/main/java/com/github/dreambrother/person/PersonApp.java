package com.github.dreambrother.person;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

public class PersonApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        // Left panel
        var personList = FXCollections.observableArrayList(Person.extractor);
        var leftPanelList = new ListView<Person>();
        leftPanelList.setItems(personList);
        leftPanelList.setLayoutX(-17);
        leftPanelList.setLayoutY(14);
        leftPanelList.setPrefWidth(200);
        leftPanelList.setPrefHeight(285);
        var leftPanel = new AnchorPane(leftPanelList);
        leftPanel.setMinWidth(0);
        leftPanel.setMinHeight(0);
        leftPanel.setPrefWidth(100);
        leftPanel.setPrefHeight(160);
        AnchorPane.setTopAnchor(leftPanelList, 15.);
        AnchorPane.setBottomAnchor(leftPanelList, 0.);
        AnchorPane.setLeftAnchor(leftPanelList, 0.);
        AnchorPane.setRightAnchor(leftPanelList, 0.);

        // Main panel grid
        var nameLabel = new Label("Name");
        var nameField = new TextField();
        nameField.setPrefWidth(248);

        var notesLabel = new Label("Notes");
        var notesArea = new TextArea();
        notesArea.setWrapText(true);
        notesArea.setPrefWidth(265);
        notesArea.setPrefHeight(173);

        leftPanelList.getSelectionModel()
                .selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> {
                    if (newValue != null) {
                        nameField.setText(newValue.getName());
                        notesArea.setText(newValue.getNotes());
                    } else {
                        nameField.setText("");
                        notesArea.setText("");
                    }
                });

        // Action buttons
        var newButton = new Button("New");
        newButton.setOnAction(e -> leftPanelList.getSelectionModel().clearSelection());
        var saveButton = new Button("Save");
        saveButton.setOnAction(e -> {
            var selected = leftPanelList.getSelectionModel().getSelectedItem();
            if (selected != null) {
                selected.setName(nameField.getText());
                selected.setNotes(notesArea.getText());
            } else {
                var person = new Person(nameField.getText(), notesArea.getText());
                personList.add(person);
                leftPanelList.getSelectionModel().select(person);
            }
        });
        var deleteButton = new Button("Delete");
        deleteButton.setOnAction(e -> {
            var selected = leftPanelList.getSelectionModel().getSelectedItem();
            personList.remove(selected);
        });
        var actionButtons = new ButtonBar();
        actionButtons.getButtons().addAll(newButton, saveButton, deleteButton);

        var gridPane = new GridPane(20, 20);
        gridPane.setPrefWidth(340);
        gridPane.setPrefHeight(298);
        gridPane.add(nameLabel, 0, 0);
        gridPane.add(nameField, 1, 0);
        GridPane.setHgrow(nameField, Priority.ALWAYS);
        gridPane.add(notesLabel, 0, 1);
        gridPane.add(notesArea, 1, 1);
        GridPane.setHgrow(notesArea, Priority.ALWAYS);
        GridPane.setVgrow(notesArea, Priority.ALWAYS);
        gridPane.add(actionButtons, 0, 2);
        GridPane.setColumnSpan(actionButtons, 2);
        gridPane.getRowConstraints()
                .addAll(
                        new RowConstraints(10, 45, 71),
                        new RowConstraints(10, 173, 173),
                        new RowConstraints(10, 30, 45)
                );
        gridPane.getColumnConstraints()
                .addAll(
                        new ColumnConstraints(10, 65, 65, Priority.SOMETIMES, HPos.RIGHT, false),
                        new ColumnConstraints(10, 265, 265)
                );
        gridPane.getColumnConstraints().get(1).setHgrow(Priority.ALWAYS);
        gridPane.getRowConstraints().get(0).setVgrow(Priority.NEVER);
        gridPane.getRowConstraints().get(1).setVgrow(Priority.ALWAYS);
        gridPane.getRowConstraints().get(2).setVgrow(Priority.NEVER);
        var mainPanel = new AnchorPane(gridPane);
        mainPanel.setMinWidth(0);
        mainPanel.setMinHeight(0);
        mainPanel.setPrefWidth(400);
        mainPanel.setPrefHeight(160);
        AnchorPane.setBottomAnchor(gridPane, 20.);
        AnchorPane.setTopAnchor(gridPane, 20.);
        AnchorPane.setRightAnchor(gridPane, 20.);
        AnchorPane.setLeftAnchor(gridPane, 20.);

        var splitPane = new SplitPane(leftPanel, mainPanel);
        splitPane.setPrefWidth(600);
        splitPane.setPrefHeight(326);
        splitPane.setDividerPositions(0.33);
        splitPane.setStyle("-fx-background-color: linear-gradient(aliceblue, lightblue)");
        var panels = new AnchorPane(splitPane);
        panels.setPrefWidth(600);
        panels.setPrefHeight(326);
        AnchorPane.setTopAnchor(splitPane, 0.);
        AnchorPane.setBottomAnchor(splitPane, 0.);
        AnchorPane.setLeftAnchor(splitPane, 0.);
        AnchorPane.setRightAnchor(splitPane, 0.);

        var scene = new Scene(panels);
        stage.setTitle("Example JavaFX app");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
