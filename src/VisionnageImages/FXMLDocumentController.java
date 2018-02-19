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
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;

/**
 *
 * @author uapv1700535
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Label labelM;
    
    @FXML
    private TextField textRep;
    @FXML
    private Button buttonMl;
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
 
        final DirectoryChooser dialog = new DirectoryChooser(); 
        final File directory = dialog.showDialog(buttonMl.getScene().getWindow());
      
        textRep.setText(directory.getAbsolutePath());
    
    }
        @FXML
    private void validerButtonAction(ActionEvent event) {
            
    
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
