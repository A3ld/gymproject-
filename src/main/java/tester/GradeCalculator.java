package tester;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class GradeCalculator extends Application {

    private Rectangle rectangle;
    private Circle circle;
    private Label descriptionLabel;

    private int currentDrawingIndex = 0;
    private String[] drawingDescriptions = {"Rectangle Drawing", "Circle Drawing"};

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Design");

        // Create shapes
        rectangle = new Rectangle(150, 100, Color.GREEN);
        circle = new Circle(70, Color.RED);

        // Create buttons
        Button exitButton = new Button("Exit");
        Button changeButton = new Button("Change");

        // Create description label
        descriptionLabel = new Label(drawingDescriptions[currentDrawingIndex]);

        // Set button actions
        exitButton.setOnAction(e -> primaryStage.close());
        changeButton.setOnAction(e -> changeDrawing());

        // Create layout
        StackPane layout = new StackPane();
        layout.getChildren().addAll(rectangle, circle, descriptionLabel, exitButton, changeButton);

        // Set position of shapes and buttons
        StackPane.setAlignment(rectangle, javafx.geometry.Pos.TOP_LEFT);
        StackPane.setAlignment(circle, javafx.geometry.Pos.TOP_LEFT);
        StackPane.setAlignment(descriptionLabel, javafx.geometry.Pos.BOTTOM_CENTER);
        StackPane.setAlignment(exitButton, javafx.geometry.Pos.BOTTOM_LEFT);
        StackPane.setAlignment(changeButton, javafx.geometry.Pos.BOTTOM_RIGHT);

        // Set scene
        Scene scene = new Scene(layout, 300, 250);
        primaryStage.setScene(scene);

        // Show the stage
        primaryStage.show();
    }

    private void changeDrawing() {
        // Toggle between rectangle and circle
        currentDrawingIndex = (currentDrawingIndex + 1) % 2;

        // Update shapes and description
        if (currentDrawingIndex == 0) {
            rectangle.setVisible(true);
            circle.setVisible(false);
        } else {
            rectangle.setVisible(false);
            circle.setVisible(true);
        }

        descriptionLabel.setText(drawingDescriptions[currentDrawingIndex]);
    }
}