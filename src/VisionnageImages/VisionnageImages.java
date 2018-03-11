/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VisionnageImages;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author uapv1700535
 */
public class VisionnageImages extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        
        File f = new File(System.getProperty("user.dir")+"/MotCles.json");
        if(!f.exists() && !f.isDirectory()) { 
            try {
                FileWriter file = new FileWriter("MotCles.json");
            }catch (IOException euror){}
        }
        
           
        
        Parent root = FXMLLoader.load(getClass().getResource("views/FXMLmainFenetre.fxml"));
        
        Scene scene = new Scene(root);
        stage.setTitle("Visionneuse Image");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
