package com.example;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


public class flow extends Application {
@Override
public void start(Stage sx){
    FlowPane flow = new  FlowPane();
    flow.setPadding (new Insets (5,5,5,5));
    flow.setHgap(5);
    flow.setVgap(5);
   flow.getChildren().addAll( new Label(" first name"),new TextField(),new Label("mi"));
   TextField t = new TextField();
   t.setPrefColumnCount(1);
   flow.getChildren().addAll(t,new Label(" last name"), new TextField());
   Scene f= new Scene(flow,700,400);
   sx.setScene(f);
   sx.show();
   
   

   
   

}

public static void main(String[] args) {
    launch(args);
}
}
