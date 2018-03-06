package VisionnageImages;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;

public class FXMLRecadrerController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
        private ImageView imageRecadrer;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

              
    }    
     public void setImage(Image img) {
    this.imageRecadrer.setImage(img);
  }
}
