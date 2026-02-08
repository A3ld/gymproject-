package com.example;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.*;
public class fu  extends  Application{
    @Override
public void start(Stage s){
    Pane p = new HBox(10);
    p.setPadding(new Insets(5,5,5,5));
    
    // Temporarily removed image code - add a valid image path to use images
    // Example: Image image = new Image("file:path/to/your/image.png");
    
    Scene ss = new Scene(p, 400, 400);
    s.setTitle(" gggggggggggg");
    s.setScene(ss);
    s.show();

    

}
public static void main (String[] args){
    launch(args);
}
}
