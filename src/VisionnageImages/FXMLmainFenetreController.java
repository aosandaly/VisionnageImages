/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VisionnageImages;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.DirectoryChooser;

/**
 * FXML Controller class
 *
 * @author uapv1700535
 */
public class FXMLmainFenetreController implements Initializable {

    /**
     * Initializes the controller class.
     */
        @FXML
    private Button btnModif;
         @FXML
    private Label labelReper;
  @FXML
    private void modifRepButtonAction(ActionEvent event) {
 
        final DirectoryChooser dialog = new DirectoryChooser(); 
        final File directory = dialog.showDialog(btnModif.getScene().getWindow());
        if(directory == null){
                    labelReper.setText("No Directory selected");
                }else{
                    labelReper.setText(directory.getAbsolutePath());
                }
    
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
