/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VisionnageImages;

import java.io.File;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
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
    
    
    private ResourceBundle bundle;
    private Locale local;
  
  
  
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
    }
}
