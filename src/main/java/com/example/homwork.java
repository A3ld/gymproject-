package com.example;
import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.shape.Rectangle;

public class homwork extends Application  {
   
    
    int cu=1;
    @Override
    public void start (Stage pstage){
         BorderPane root = new BorderPane();
         Button exitbtn = new Button(" Exit");
         Button changebtn = new Button("change");
         StackPane stack = new StackPane();
         StackPane.setMargin(changebtn, new Insets(0,50,0,0));
         StackPane.setMargin(exitbtn, new Insets(0,0,0,50));
         stack.getChildren().addAll(changebtn,exitbtn);
         root.setTop(stack);
        root.setBottom(new Label(" drawing 1: first shape"));
        StackPane p = new StackPane();
        Circle createDrawing1 = new Circle(100,100,20);
        p.getChildren().add(createDrawing1);
        Rectangle createDrawing2 = new Rectangle(50, 50, 100, 100);
        root.setCenter(p);
        changebtn.setOnAction(new EventHandler<ActionEvent>() {
             @Override
             public void handle(ActionEvent e) {
                 FadeTransition fade = new FadeTransition( Duration.millis(300),p);
                 fade.setFromValue(1.0);
                 fade.setToValue(0.0);
                 fade.setOnFinished(ev ->{
                    p.getChildren().clear();
                    if(cu ==1){
                     p.getChildren().add(createDrawing2);
                     cu=2;  } else {
                     p.getChildren().add(createDrawing1);
                     cu =1;
                 }
                 FadeTransition fadeIn = new FadeTransition( Duration.millis(300),p);
                 fadeIn.setFromValue(0.0);
                 fadeIn.setToValue(1.0);
                 fadeIn.play();
                 });
                 fade.play();
             }
         });
        exitbtn.setOnAction((ActionEvent event)->{
            System.exit(0);
        });
 Scene scene = new Scene(root,300,300);
            pstage.setScene(scene);
            pstage.show();
            


    }

    public static void main (String []args){
        launch(args);
    }
}
