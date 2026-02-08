package com.example;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class grid extends Application  {
    @Override
    public void start(Stage s){
        GridPane g = new GridPane();
        g.setHgap(10);
        g.setVgap(10);
        g.setPadding(new javafx.geometry.Insets(20, 20, 20, 20));
        
        g.add(new Label("First Name:"), 0, 0);
        g.add(new TextField(), 1, 0);
        
        g.add(new Label("MI:"), 0, 1);
        g.add(new TextField(), 1, 1);
        
        g.add(new Label("Last Name:"), 0, 2);
        g.add(new TextField(), 1, 2);
        
        Button bb = new Button("Add Name");
        g.add(bb, 1, 3);
        GridPane.setHalignment(bb, HPos.RIGHT);
        
        Scene x = new Scene(g, 400, 250);
        s.setTitle("Name Registration Form");
        s.setScene(x);
        s.show();




    }
     public static void main(String[]args){
        launch(args);
     }

}
