/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VisionnageImages;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.Locale;
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
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.json.simple.JSONObject;

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
    @FXML private RadioButton motCleRecherche;
    @FXML private Label listesImage;
    @FXML private Button quitterApp;
    @FXML private Button btnPrecedent;
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
    int indice;
  
    
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

    public FXMLmainFenetreController(File path) {
        this.directory = path;
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
                setImageInListeView(directory.getAbsolutePath(), listeImages.getItems().get(0).toString());
                listeImages.getSelectionModel().select(0);
            } catch (IndexOutOfBoundsException e) {
                
            }
        }
    }
    
    public void setImageInListeView(String directory, String newValue){
        FileInputStream input = null;
        try {
            input = new FileInputStream(directory+"/"+newValue);
            Image image = new Image(input,455,445,false,false);
            imageView.setImage(image);
        } catch (FileNotFoundException ex) {
            //Logger.getLogger(FXMLmainFenetreController.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("test");
        }
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
        setItemsInListView(getPath());
        listeImages.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                setImageInListeView(directory.getAbsolutePath(), newValue);
            }
        });
    }
    
    
     @FXML
    public void btnRecadrer(ActionEvent event) {

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
    public void btnDiaporamaAction(ActionEvent event) throws IOException{
        try {
            FXMLLoader loaderDiapo = new FXMLLoader(getClass().getResource("FXMLDiaporama.fxml"));
            FXMLDiaporamaController diapoController = new FXMLDiaporamaController(directory,listeImages);
            loaderDiapo.setController(diapoController);

            Parent root =  (Parent)loaderDiapo.load();
            Stage stage = new Stage();
            stage.initStyle(StageStyle.DECORATED);
            stage.setTitle("Diaporama");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            showMessage("Impossible de charger le diaporam !");
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
    
    @FXML private void btnAssocierMotClesButtonAction(ActionEvent event){
        
        if (!promptMotCle.getText().isEmpty()) {
            JSONObject jsonObject = new JSONObject();
            JSONParser parser = new JSONParser();
            String item = directory.getAbsolutePath()+"/"+listeImages.getSelectionModel().getSelectedItem().toString();
            
            String[] motCles = promptMotCle.getText().split(",|;");
            
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
                        //"Key not exist"
                        JSONArray list = new JSONArray();
                        list.add(item);
                        jsonObject.put(cle, list);
                    }
                    else{
                        if(imagesAssociees.contains(item)){
                            showMessage("Cette image est déjà associé à cette clé: "+cle);
                        }
                        else{
                            imagesAssociees.add(item);
                            jsonObject.put(cle, imagesAssociees);
                        } 
                    }
                 }
                
                try (FileWriter file = new FileWriter("MotCles.json")) {
                    file.write(jsonObject.toString());
                    file.flush();
                }catch (FileNotFoundException e){
                }

            }
            catch (FileNotFoundException e) {
                System.out.print("Aucun fichier trouver");
            }
            catch (IOException e){e.printStackTrace();}
            catch (ParseException e){
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

                }catch (IOException euror) 
                {}
                    
            }
            catch (Exception e){e.printStackTrace();}
        } else {
            showMessage("Le champs mot-clés n'est pas remplis!");
        }
    }
    
    @FXML private void btnOKButtonAction(ActionEvent envent){
        if(!ok.getText().isEmpty()){
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
                setImageInListeView(directory.getAbsolutePath(), listeImages.getItems().get(0).toString());
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
