package com.aimlite;

import javafx.animation.KeyFrame;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

public class MainApp extends Application {

    private Player player;
    private Leaderboard leaderboard = new Leaderboard();
    private TargetSprite target;
    private Timeline timeline;

    @Override
    public void start(Stage stage) {

        Label title = new Label("AIMLITE");
        title.setStyle("-fx-font-size: 28px; -fx-text-fill: pink;");

        TextField nameField = new TextField();
        nameField.setPromptText("Masukkan Nama Pemain");

        ChoiceBox<String> difficultyBox = new ChoiceBox<>();
        difficultyBox.getItems().addAll("EASY", "MEDIUM", "GOD");
        difficultyBox.setValue("EASY");

        Button startBtn = new Button("START GAME");
        startBtn.setStyle("""
                -fx-background-color: hotpink;
                -fx-text-fill: white;
                -fx-font-size: 16px;
                -fx-padding: 10 20;
                -fx-background-radius: 20;
                """);

        VBox card = new VBox(15, title, nameField, difficultyBox, startBtn);
        card.setAlignment(Pos.CENTER);
        card.setStyle("""
                -fx-background-color: rgba(0,0,0,0.65);
                -fx-padding: 30;
                -fx-background-radius: 18;
                """);

        StackPane menuRoot = new StackPane(card);
        menuRoot.setStyle("-fx-background-color: linear-gradient(#2c003e, #000000);");

        startBtn.setOnAction(e -> {
            String name = nameField.getText().isEmpty() ? "Player" : nameField.getText();
            player = new Player(name);
            GameSystem system = new GameSystem(difficultyBox.getValue());
            startGame(stage, system);
        });

        stage.setTitle("AimLite");
        stage.setScene(new Scene(menuRoot, 600, 400));
        stage.show();
    }

    private void startGame(Stage stage, GameSystem system) {

        Pane gamePane = new Pane();
        gamePane.setStyle("-fx-background-color: #1b1b1b;");

        Label scoreLabel = new Label("Score: 0");
        scoreLabel.setTextFill(Color.HOTPINK);
        scoreLabel.setStyle("-fx-font-size: 18px;");
        scoreLabel.setLayoutX(10);
        scoreLabel.setLayoutY(10);

        target = new TargetSprite();
        addClickEffect(target);

        gamePane.getChildren().addAll(target.getShape(), scoreLabel);

        target.getShape().setOnMouseClicked(e -> {
            player.addScore(10);
            scoreLabel.setText("Score: " + player.getScore());
            target.update();
        });

        timeline = new Timeline(
                new KeyFrame(Duration.millis(system.getSpeed()), e -> target.update())
        );
        timeline.setCycleCount(25);
        timeline.setOnFinished(e -> endGame(stage));
        timeline.play();

        stage.setScene(new Scene(gamePane, 600, 400));
    }

    private void endGame(Stage stage) {

        leaderboard.add(player.getName(), player.getScore());

        VBox root = new VBox(12);
        root.setAlignment(Pos.CENTER);
        root.setStyle("-fx-background-color: #0f0f0f; -fx-padding: 20;");

        Label title = new Label("🏆 RANKING PEMAIN");
        title.setStyle("-fx-font-size: 24px; -fx-text-fill: hotpink;");

        root.getChildren().add(title);

        int rank = 1;
        for (LeaderboardEntry entry : leaderboard.getEntries()) {
            Label label = new Label(rank + ". " + entry.toString());
            label.setStyle("-fx-font-size: 16px; -fx-text-fill: white;");
            root.getChildren().add(label);
            rank++;
        }

        Button exitBtn = new Button("KELUAR");
        exitBtn.setStyle("""
                -fx-background-color: hotpink;
                -fx-text-fill: white;
                -fx-padding: 8 20;
                -fx-background-radius: 15;
                """);

        exitBtn.setOnAction(e -> stage.close());
        root.getChildren().add(exitBtn);

        stage.setScene(new Scene(root, 450, 350));
    }

    private void addClickEffect(TargetSprite target) {
        ScaleTransition st = new ScaleTransition(Duration.millis(120), target.getShape());
        st.setFromX(1);
        st.setFromY(1);
        st.setToX(0.7);
        st.setToY(0.7);
        st.setAutoReverse(true);
        st.setCycleCount(2);

        target.getShape().setOnMouseClicked(e -> st.playFromStart());
    }

    public static void main(String[] args) {
        launch(args);
    }
}
