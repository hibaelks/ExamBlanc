package packagee;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class InsertController {
    @FXML
    private TextField nomField;
    @FXML
    private TextField prenomField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField phoneField;

    public void handleInsertButtonClick(ActionEvent event) throws IOException{
      try{
          Membre m=new Membre(nomField.getText(),prenomField.getText(),emailField.getText(),phoneField.getText());
          MembreDaoImpl md=new MembreDaoImpl();
          md.insere(m);
          navigateToScene(event,"Success.fxml");
      }
      catch(Exception e){ navigateToScene(event,"Failure.fxml");}
    }
    private void navigateToScene(ActionEvent event, String fxmlFile) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

}
