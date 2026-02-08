package com.example;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class border extends Application  {
    public void start(Stage  s){
        BorderPane bb = new BorderPane();
        bb.setTop(new CustomPane(" top" ));
        bb.setRight(new CustomPane(" right "));
        bb.setLeft(new CustomPane(" left "));
        bb.setCenter(new  CustomPane(" center"));
        bb.setBottom(new CustomPane(" bottom"));
        Scene c = new Scene(bb,400,400);
        s.setScene(c);
        s.show();
    }
    class CustomPane extends StackPane{
        public CustomPane(String title){
            getChildren().add(new Label(title));
            setStyle(" -fx-border-color:black");
            setPadding(new Insets(10,10,10,10));
        }
       
    }
 public static void main(String[]args){
            launch(args);
        }
}
