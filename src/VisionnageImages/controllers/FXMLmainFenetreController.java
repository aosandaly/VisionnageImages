/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VisionnageImages.controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.DirectoryChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.json.simple.JSONObject;

/**
 * FXML Controller class
 *
 * @author uapv1700535, uapv1700541
 */
public class FXMLmainFenetreController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML private Label labelReper; 
    @FXML private Label NomImage;
    @FXML private Label motCle;
    @FXML private Label type;
    @FXML private Label typeImage;
    @FXML private Label taille;
    @FXML private Label tailleimage;
    @FXML private Label rechercherPar;
    @FXML private Label listesImage;
    @FXML private Label labelDetails;
    
    @FXML private Button btnModif;
    @FXML private Button quitterApp;
    @FXML private Button reunitialiserListe;
    @FXML private Button mofifieInfo;
    @FXML private Button ok;
    
    @FXML private TextField promptRecherch;
    @FXML private TextField promptMotCle;
    @FXML private TextField promptNom;
    
    @FXML private ImageView imageView;
    @FXML private ListView listeImages;
    @FXML private ResourceBundle bundle;
    @FXML private Locale local; 
  
    int indice;
    private File directory = new File("images");
    
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
            try {
                if(listeImages.getItems().size() > 0){
                    setImageInImageView(directory.getAbsolutePath(), listeImages.getItems().get(0).toString());
                    listeImages.getSelectionModel().select(0);
                }else{
                    showMessage("Le répertoire choisi est vide !");
                }
                
            } catch (IndexOutOfBoundsException e) {
                
            }
        }
    }
    
    public void setImageInImageView(String directory, String newValue){
        FileInputStream input = null;
        try {
            input = new FileInputStream(directory+"/"+newValue);
            Image image = new Image(input,460,460,false,false);
            imageView.setImage(image);
        } catch (FileNotFoundException ex) {
            //Logger.getLogger(FXMLmainFenetreController.class.getName()).log(Level.SEVERE, null, ex);
            showMessage("Impossible de charger l'image dans la liste !");
        }
    }

  @FXML
    private void reunitialiserListeButtonAction(ActionEvent event) {
        listeImages.getItems().clear();
        setItemsInListView(directory);
    }
          
          
  @FXML
    private void modifRepButtonAction(ActionEvent event) {
        
        final DirectoryChooser newRep = new DirectoryChooser();
        File newDirectory = newRep.showDialog(btnModif.getScene().getWindow());
        if(newDirectory != null){
            directory = newDirectory;
            listeImages.getItems().clear();
            setItemsInListView(directory);
        }else{
            listeImages.refresh();
        }
    }
    
     @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        tailleimage.setText("460x460");
        setItemsInListView(getPath());
        listeImages.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if(listeImages.getItems().size()>0){
                    setImageInImageView(directory.getAbsolutePath(), newValue);
                    typeImage.setText(newValue.substring(newValue.indexOf(".")+1));
                    promptNom.setText(newValue.substring(0,newValue.indexOf(".")));
                }
                
           }
        });
    }
    
    
    @FXML
    private void btnRecadrer(ActionEvent event) throws IOException {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/VisionnageImages/views/FXMLRecadrer.fxml"));
            FXMLRecadrerController Controller = new FXMLRecadrerController();
            loader.setController(Controller);
            Parent root = (Parent) loader.load();
            Scene scene = new Scene(root);
            Controller.setImage(imageView.getImage());
            Window existingWindow = ((Node) event.getSource()).getScene().getWindow();

            // create a new stage:
            Stage stage = new Stage();
            // make it modal:
            stage.initModality(Modality.APPLICATION_MODAL.APPLICATION_MODAL.APPLICATION_MODAL);
            // make its owner the existing window:
            stage.initOwner(existingWindow);
            stage.setTitle("Recadrement Image");
            stage.setScene(scene);
            stage.show();
        }catch(Exception e){
            showMessage(e+"Impossible de charger la Récadrement !");
        }
        

    } 
    @FXML public void btnPrecedentAction(ActionEvent event){
        indice =0;
        indice = listeImages.getSelectionModel().getSelectedIndex() - 1;
        if(indice < 0)
            indice = listeImages.getItems().size() - 1;
        listeImages.getSelectionModel().select(indice);
    }
    
    @FXML public void btnSuivantAction(ActionEvent event){
        indice =0;
        indice = listeImages.getSelectionModel().getSelectedIndex() +1;
        if(indice == listeImages.getItems().size())
            indice = 0;
        listeImages.getSelectionModel().select(indice);
    }
    
    @FXML
    private void closeButtonAction(ActionEvent event){
        // get a handle to the stage
        Stage stage = (Stage) quitterApp.getScene().getWindow();
        
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setContentText("Voulez vous quitter l'application ?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            stage.close();
        }
        
    }
    @FXML
    public void btnDiaporamaAction(ActionEvent event) throws IOException{
        try {
            FXMLLoader loaderDiapo = new FXMLLoader(getClass().getResource("/VisionnageImages/views/FXMLDiaporama.fxml"));
            FXMLDiaporamaController diapoController = new FXMLDiaporamaController(directory,listeImages);
            loaderDiapo.setController(diapoController);
            

            Parent root =  (Parent)loaderDiapo.load();
            Stage stageDiapo = new Stage();
            stageDiapo.initStyle(StageStyle.DECORATED);
            stageDiapo.setTitle("Diaporama");
            stageDiapo.setScene(new Scene(root));
            
            Window existingWindow = ((Node) event.getSource()).getScene().getWindow();
            
            stageDiapo.initModality(Modality.APPLICATION_MODAL.APPLICATION_MODAL.APPLICATION_MODAL);
            // make its owner the existing window:
            stageDiapo.initOwner(existingWindow);
        
            stageDiapo.show();
            
        } catch (Exception e) {
            showMessage(e.toString());
        }
        
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
        bundle=ResourceBundle.getBundle("VisionnageImages.langues.lang",local);
        NomImage.setText(bundle.getString("NomImage"));
        motCle.setText(bundle.getString("motCle"));
        type.setText(bundle.getString("type"));
        taille.setText(bundle.getString("taille"));
        rechercherPar.setText(bundle.getString("rechercherPar"));
        listesImage.setText(bundle.getString("listesImage"));
        btnModif.setText(bundle.getString("btnModif"));
        mofifieInfo.setText(bundle.getString("mofifieInfo"));
        quitterApp.setText(bundle.getString("quitterApp"));
        ok.setText(bundle.getString("ok"));
        promptRecherch.setPromptText(bundle.getString("promptRecherch"));
        promptMotCle.setPromptText(bundle.getString("promptMotCle"));
        promptNom.setPromptText(bundle.getString("promptNom"));
        labelDetails.setText(bundle.getString("labelDetails"));
       reunitialiserListe.setText(bundle.getString("reunitialiserListe"));
    }
    
    @FXML private void btnAssocierMotClesButtonAction(ActionEvent event){
        
        if (!promptMotCle.getText().isEmpty()) {
            JSONObject jsonObject = new JSONObject();
            JSONParser parser = new JSONParser();
            String item = directory.getAbsolutePath()+"/"+listeImages.getSelectionModel().getSelectedItem().toString();
            
            String[] motCles = promptMotCle.getText().split(",");
            Boolean motCleAssocie = false;
            
            try 
            {
                Object obj = parser.parse(new FileReader("MotCles.json"));
                jsonObject = (JSONObject) obj;
                for (int i = 0; i < motCles.length; i++) {
                    String cle = motCles[i];
                    System.out.print(cle);
                    Object name = (JSONArray) jsonObject.get(cle);
                    
                    JSONArray imagesAssociees = (JSONArray) jsonObject.get(cle);
                    if(imagesAssociees == null){
                        //Key not exist:
                        JSONArray list = new JSONArray();
                        list.add(item);
                        jsonObject.put(cle, list);
                        motCleAssocie = true;
                    }
                    else{
                        if(imagesAssociees.contains(item)){
                            showMessage("Cette image est déjà associé à cette clé: "+cle);
                        }
                        else{
                            imagesAssociees.add(item);
                            jsonObject.put(cle, imagesAssociees);
                            motCleAssocie = true;
                        } 
                    }
                }
                
                try (FileWriter file = new FileWriter("MotCles.json")) {
                    file.write(jsonObject.toString());
                    file.flush();
                }catch (FileNotFoundException e){}

            }
            catch (FileNotFoundException e) {
                System.out.print("Aucun fichier trouver");
            }
            catch (IOException e){
                e.printStackTrace();
            }catch (ParseException e){
                System.out.print("Le fichier est vide");
                    
                for (int i = 0; i < motCles.length; i++) {
                    String cle = motCles[i];
                    JSONArray list = new JSONArray();
                    list.add(item);
                    jsonObject.putIfAbsent(cle, list);
                 }
                try (FileWriter file = new FileWriter("MotCles.json")) {

                    file.write(jsonObject.toString());
                    file.flush();

                }catch (IOException euror){}      
            }
            catch (Exception e){
                e.printStackTrace();
            }finally{
                if(motCleAssocie){
                    showMessage("Le(s) mot-clés est/sont bien associée(s).");
                }
            }

        } else {
            showMessage("Le champs mot-clés n'est pas remplis!");
        }
    }
    
    @FXML private void btnOKButtonAction(ActionEvent envent){
        if(!promptRecherch.getText().isEmpty()){
            searchInJSON(promptRecherch.getText());
        }else{
            showMessage("Veuillez saisir un mot-clé pour la recherche!");
        }
    }
    public void searchInJSON(String cle){
        JSONParser parser = new JSONParser();
        
        try {
            Object obj = parser.parse(new FileReader("MotCles.json"));
            JSONObject jsonObject = (JSONObject) obj;

            JSONArray images = (JSONArray) jsonObject.get(cle);
            JSONArray l = new JSONArray();
            if (images != null) {
                for (int i = 0; i < images.size(); i++) {
                    Object get = images.get(i);
                    if (get.toString().startsWith(directory.getAbsolutePath())) {
                         l.add(get.toString().substring(directory.getAbsolutePath().length()+1));
                    }
                }
                listeImages.getItems().clear();
                listeImages.getItems().addAll(l);
                setImageInImageView(directory.getAbsolutePath(), listeImages.getItems().get(0).toString());
                listeImages.getSelectionModel().select(0);
            }else{
                showMessage("Le mot-clé " + cle +" n'est associé à aucune image !"); 
            }
        } 
        catch (FileNotFoundException e) {
            showMessage("Aucun fichier trouver");
        }
        catch (IOException e){e.printStackTrace();}
        catch (ParseException e){
            //e.printStackTrace();
            showMessage("Le fichier est vide");
        }
        catch (Exception e){e.printStackTrace();}
        
    }
    
    public void showMessage(String message){
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(message);
        alert.show();
    }
}
