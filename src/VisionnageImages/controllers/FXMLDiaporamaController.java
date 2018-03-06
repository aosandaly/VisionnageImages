/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VisionnageImages.controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author mariam and Sandaly
 */

    

public class FXMLDiaporamaController implements Initializable {

    /**
     * Initializes the controller class.
     */
    private File directory;
    private boolean exit = false;
    
    
    @FXML 
        private ImageView imageViewDiapo;
        private Button btnQuitterDiapo;
        private ListView listeImages;
        
    public FXMLDiaporamaController(File directory, ListView listeImages){
        this.directory = directory;
        this.listeImages = listeImages;
    }
    
    public ListView getListeImages() {
        return listeImages;
    }

    public File getSelectedDirectory() {
        return directory;
    }

    public void setListeImages(ListView listeImages) {
        this.listeImages = listeImages;
    }

    public void setSelectedDirectory(File selectedDirectory) {
        this.directory = selectedDirectory;
    }

    public void setExit(boolean exit) {
        this.exit = exit;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.startDiaporama();
              
    }
    
    private void startDiaporama(){
        long delay = 2000;// mis a jour de la photo chaque 2 secondes;
            
        new Timer().schedule(new TimerTask() {
            int count=0;
            @Override
            public void run() {
                try {
                    String s=listeImages.getItems().get(count++).toString();
                    //System.out.println(directory.getAbsolutePath()+"/"+s);
                    imageViewDiapo.setImage(new Image(new FileInputStream(directory.getAbsolutePath()+"/"+s),552,316,false,false));
                    if (count >= listeImages.getItems().size()) {
                        //Thread.currentThread().interrupt();
                        this.cancel();
                    }
                    if (exit ) {
                        this.cancel();
                    }
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(FXMLmainFenetreController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }, 0, delay);
       
    }
    
    @FXML private void btnQuitterDiapoAction(ActionEvent event){
        
    }
    
    public void showMessage(String message){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(message);
            alert.show();
    }
}
