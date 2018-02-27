/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VisionnageImages;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

/**
 *
 * @author uapv1700535
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Label labelM;
   
    @FXML
    private Button buttonMl;
    @FXML
    private Label labelRep;
    
    private File directory = null;
    
    private String chemin;
    @FXML
    private VBox buttonM;
    
    public String getlabelRep(){
        return labelRep.getText();
    }
    
    public File getPath(){
        return directory;
    }
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
 
        final DirectoryChooser dialog = new DirectoryChooser(); 
        directory = dialog.showDialog(buttonMl.getScene().getWindow());
        System.out.println(directory);
       if(directory == null){
            labelRep.setText("No Directory selected");
        }else{
            labelRep.setText(directory.getAbsolutePath());
        }
     
    
    }
        @FXML
    private void validerButtonAction(ActionEvent event) throws IOException {
   
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLmainFenetre.fxml"));
        FXMLmainFenetreController personController = new FXMLmainFenetreController(directory);
        loader.setController(personController);

        Parent parent =  loader.load();
        Scene scene= new Scene(parent);
        //personController.setLabelReper(getlabelRep());
        Stage stage= (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
