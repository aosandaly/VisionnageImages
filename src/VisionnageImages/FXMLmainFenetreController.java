/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VisionnageImages;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
    @FXML private Button btnModif;
    @FXML private Label labelReper;
    @FXML private ImageView imageView;
  
    
    @FXML private ListView listeImages;
    
    private File directory;
    
    public void setPath(File path){
        this.directory = path;
    }
    public File getPath(){
        return directory;
    }
    
    private File[] fListes;
    
    public void setLabelReper(String path){
        labelReper.setText(path);
    }
    
    public void setItemsInListView(File path){
        
        if(path != null){
            fListes = path.listFiles();
            for(File file: fListes){
                String name = file.getName();
                if(file.isFile() && 
                        (name.endsWith(".JPEG") || name.endsWith(".png") || name.endsWith(".jpg") || name.endsWith(".jpeg") 
                        || name.endsWith(".PNG" ) || name.endsWith(".JPG" ) )){

                    listeImages.getItems().add(file.getName());
                }
            }
        }
        
    }

    public FXMLmainFenetreController(File path) {
        this.directory = path;
    }
    
    
    @FXML
    private void modifRepButtonAction(ActionEvent event) {
        final DirectoryChooser dialog = new DirectoryChooser(); 
        directory = dialog.showDialog(btnModif.getScene().getWindow());
        setItemsInListView(getPath());
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        setItemsInListView(getPath());
        
        listeImages.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                //System.out.println("ListView selection changed from oldValue = "+ oldValue + " to newValue = " + newValue);
                FileInputStream input = null;
                try {
                    input = new FileInputStream(directory.getAbsolutePath()+"/"+newValue);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(FXMLmainFenetreController.class.getName()).log(Level.SEVERE, null, ex);
                }
                Image image = new Image(input,455,445,false,false);
                imageView.setImage(image);
                
                
            }
        });
    }    
    
}
