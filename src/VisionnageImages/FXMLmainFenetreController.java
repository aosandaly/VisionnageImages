/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VisionnageImages;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
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
  @FXML
    private Button btnModif;
    @FXML
    private Label labelReper; 
    @FXML
    private Label NomImage;
    @FXML
    private Label motCle;
    @FXML
    private Label type;
    @FXML
    private Label taille;
    @FXML
    private Label rechercherPar;
    @FXML
    private RadioButton  nomRecherche;
    @FXML
    private RadioButton motCleRecherche;
    @FXML
    private Label listesImage;
    @FXML
    private Button quitterApp;
    @FXML
    private Button mofifieInfo;
    @FXML
    private Button ok;
      @FXML
    private TextField promptRecherch;
    @FXML
    private TextField promptMotCle;
    @FXML
    private TextField promptNom;
    @FXML
    private Label labelDetails;
    @FXML
    private ImageView imageView;
    
    @FXML private ListView listeImages;
@FXML
    private ImageView test;
    private ResourceBundle bundle;
    private Locale local;
  
    
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
    
    @FXML private void btnAssocierMotClesButtonAction(ActionEvent event){
        if (!promptMotCle.getText().isEmpty()) {
            
        } else {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText("Le champs mot-clés n'est pas remplis!");
//            alert.setContentText("Le champs mot-clés n'est pas remplis!");
            
        }
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
     @FXML
    public void btnRecadrer(ActionEvent event) {

    } 
  
    public void btnDiaporama(ActionEvent event) throws IOException{

    }     
     @FXML
    public void btnEn(ActionEvent event) {
         loadLang("en");
    } 
       @FXML
    public void btnFr(ActionEvent event) {
        loadLang("fr");
    } 
       @FXML
    public void btnAr(ActionEvent event) {

        loadLang("ar");
    } 
    private void loadLang(String lang){
    local=new Locale(lang);
    bundle=ResourceBundle.getBundle("VisionnageImages.lang",local);
    NomImage.setText(bundle.getString("NomImage"));
    motCle.setText(bundle.getString("motCle"));
    type.setText(bundle.getString("type"));
    taille.setText(bundle.getString("taille"));
    rechercherPar.setText(bundle.getString("rechercherPar"));
    nomRecherche.setText(bundle.getString("nomRecherche"));
    motCleRecherche.setText(bundle.getString("motCleRecherche"));
    listesImage.setText(bundle.getString("listesImage"));
    btnModif.setText(bundle.getString("btnModif"));
    mofifieInfo.setText(bundle.getString("mofifieInfo"));
    quitterApp.setText(bundle.getString("quitterApp"));
    ok.setText(bundle.getString("ok"));
    promptRecherch.setPromptText(bundle.getString("promptRecherch"));
    promptMotCle.setPromptText(bundle.getString("promptMotCle"));
    promptNom.setPromptText(bundle.getString("promptNom"));
    labelDetails.setText(bundle.getString("labelDetails"));
    }
}
