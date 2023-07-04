package driver;

import graph.Graph;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import managers.FileManager;
import models.Edge;
import models.Vertex;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Driver extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        ArrayList<Vertex> countries = Graph.countries;

        Image image = new Image(new FileInputStream("src/assets/maps/map.png"));
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(700);
        imageView.setFitWidth(950);
        imageView.setLayoutX(7);
        imageView.setLayoutY(0);


        Label Source = new Label("Source");
        Source.setLayoutX(1000);
        Source.setLayoutY(37);
        Source.setTextFill(Color.web("White"));
        Source.setStyle("-fx-font-weight: bold");
        Source.setFont(Font.font("Baskerville Old Face", 20));
        Label Target = new Label("Target");
        Target.setLayoutX(1000);
        Target.setLayoutY(109);
        Target.setFont(Font.font("Baskerville Old Face", 20));

        Target.setTextFill(Color.web("White"));
        Target.setStyle("-fx-font-weight: bold");
        Label Path = new Label("Path");
        Path.setLayoutX(1000);
        Path.setLayoutY(351);
        Path.setFont(Font.font("Baskerville Old Face", 20));

        Path.setTextFill(Color.web("White"));
        Path.setStyle("-fx-font-weight: bold");
        Label Distance = new Label("Distance");
        Distance.setLayoutX(1000);
        Distance.setLayoutY(526);

        Distance.setFont(Font.font("Baskerville Old Face", 20));
        Distance.setTextFill(Color.web("White"));
        Distance.setStyle("-fx-font-weight: bold");

        Button runButton = new Button("Run");
        runButton.setLayoutX(1150);
        runButton.setLayoutY(176);
        runButton.setPrefHeight(40);
        runButton.setPrefWidth(101);
        runButton.setMnemonicParsing(false);


        TextField distanceTextfield = new TextField();

        distanceTextfield.setPrefHeight(47);
        distanceTextfield.setPrefWidth(200);
        distanceTextfield.setLayoutX(1100);
        distanceTextfield.setLayoutY(520);
        distanceTextfield.setAlignment(Pos.CENTER);
        distanceTextfield.setDisable(true);

        TextArea pathTextarea = new TextArea();
        pathTextarea.setPrefHeight(200);
        pathTextarea.setPrefWidth(200);
        pathTextarea.setLayoutX(1100);
        pathTextarea.setLayoutY(258);
        pathTextarea.setStyle("-fx-border-color : WHite ; " + " -fx-border-width : 4;" + "-fx-text-fill: black");
        pathTextarea.setDisable(true);


        ComboBox<String> c1 = new ComboBox<String>();

        c1.setPrefHeight(25);
        c1.setPrefWidth(200);
        c1.setEditable(true);
        c1.setLayoutX(1100.0);
        c1.setLayoutY(40);
        c1.setPromptText("choose Country .....");
        c1.setStyle("-fx-background-color: Gray;");
        ComboBox<String> c2 = new ComboBox<String>();

        c2.setPromptText("choose Country .....");
        c2.setPrefHeight(25);
        c2.setPrefWidth(200);
        c2.setEditable(true);
        c2.setLayoutX(1100.0);
        c2.setLayoutY(112);
        c2.setStyle("-fx-background-color: Gray;");
        Button ResetButton = new Button("Reset");
        ResetButton.setLayoutX(1150);
        ResetButton.setLayoutY(620);
        ResetButton.setOnAction(e -> {
            c1.setValue("");
            c2.setValue("");
            pathTextarea.clear();
            distanceTextfield.clear();
        });

        AnchorPane pane = new AnchorPane();
        pane.setPrefHeight(700);
        pane.setPrefWidth(1500);
        pane.setStyle("-fx-background-color:  #282828 ;" + "-fx-border-color : #9e881c ;" + "-fx-border-width: 2 ;");

        pane.getChildren().addAll(distanceTextfield, pathTextarea, runButton, c2, c1, Source, Target, Path, Distance, imageView, ResetButton);
        Group g = new Group();
        g.getChildren().addAll(pane, imageView);
        for (int i = 0; i < countries.size(); i++) {
            c1.getItems().add(countries.get(i).getcountry().getName());
            c2.getItems().add(countries.get(i).getcountry().getName());
        }
        // AryyaList<> labels = new ArrayList<>();
        ArrayList<RadioButton> array = new ArrayList<>();
        for (int i = 0; i < countries.size(); i++) {
            RadioButton rb = new RadioButton(countries.get(i).getcountry().getName()); //  RadioButton rb = new RadioButton();
            rb.setTextFill(Color.BLACK);
            rb.setStyle("");
            rb.setOnMouseClicked(e -> {

                for (int j = 0; j < array.size(); j++) {
                    array.get(j).setSelected(false);
                }
                if (c1.getValue() == null || c1.getValue().equals("")) {
                    rb.setSelected(true);
                    c1.setValue(rb.getId());
                } else if (c2.getValue() == null || c2.getValue().equals("")) {
                    rb.setSelected(true);
                    c2.setValue(rb.getId());
                }
            });
            rb.setId(countries.get(i).getcountry().getName());
            rb.setLayoutX(countries.get(i).getcountry().getX());
            rb.setLayoutY(countries.get(i).getcountry().getY());
            rb.setTextFill(Color.BLACK);
            rb.setStyle("-fx-font-size:10px;" + "-fx-font-weight: bold");
            array.add(rb);
            g.getChildren().addAll(rb);
        }

        Scene scene = new Scene(g, 2000, 700);
        scene.getStylesheets().add("assets/style.css");
        primaryStage.setScene(scene);
        primaryStage.setTitle("Project Three");
        primaryStage.setResizable(true);
        primaryStage.show();

        runButton.setOnAction(e -> {
            Graph graph = new Graph();
            deleteingLines(g);
            try {
                Vertex sourceCountry = FileManager.Search(c1.getValue().trim());
                Vertex targetCountry = FileManager.Search(c2.getValue().trim());

                graph.dijikstra(sourceCountry, targetCountry);

                ArrayList<String> ar = graph.PrintPath(sourceCountry, targetCountry);
                String s = "";
                for (int i = 0; i < ar.size(); i++) {
                    s += "->" + ar.get(i) + " \n";
                }
                if (targetCountry.getDistance() == Double.MAX_VALUE)
                    distanceTextfield.setText(" No Distance");
                else
                    distanceTextfield.setText(String.valueOf(String.format("%.2f", targetCountry.getDistance()))+" km");
                pathTextarea.setText(s);
                System.out.println(targetCountry.getDistance());

                System.out.println(graph.PrintPath(sourceCountry, targetCountry));

                recScenes(targetCountry, g);

            } catch (FileNotFoundException e1) {

                e1.printStackTrace();
            }

        });
    }

    public static void recScenes(Vertex target, Group myPane) {
        while (target != null) {

            Vertex prev = target.getPrev();
            if (prev == null)
                return;

            Line line = new Line(target.getcountry().getX() + 5, target.getcountry().getY() + 5, prev.getcountry().getX() + 5,
                    prev.getcountry().getY() + 5);
            line.setStyle("-fx-stroke: black;" + "-fx-stroke-width: 2px;");

            myPane.getChildren().add(line);
            line.getStyleClass().add("line");

            target = prev;

        }
    }

    public void deleteingLines(Group myPane) {
        int counter = myPane.getChildren().size() - 53;
        for (int i = 0; i < counter; i++) {
            myPane.getChildren().remove(myPane.getChildren().size() - 1);
        }
    }

}