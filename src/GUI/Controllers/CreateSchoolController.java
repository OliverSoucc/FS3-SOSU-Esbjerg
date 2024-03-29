package GUI.Controllers;

import GUI.Models.MainModel;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class CreateSchoolController implements Initializable {
    MainModel mainModel;

    @FXML
    private Button btnCancel;

    @FXML
    private Button btnSave;

    @FXML
    private Label labelMessage;

    @FXML
    private TextField txtFieldCity;

    @FXML
    private TextField txtFieldName;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            mainModel = new MainModel();
        } catch (IOException e) {
            e.printStackTrace();
        }
        labelMessage.setText(" ");
    }

    @FXML
    void toCancelPage(ActionEvent event) {
        Stage stage = (Stage) btnSave.getScene().getWindow();
        stage.close();
    }

    @FXML
    void toCreateNewSchool(ActionEvent event) throws Exception {
        if (!txtFieldName.getText().isEmpty() && !txtFieldCity.getText().isEmpty()){
            mainModel.createSchool(txtFieldName.getText(), txtFieldCity.getText());
            System.out.println("Created new school");
            Stage stage = (Stage) btnSave.getScene().getWindow();
            stage.close();
        }

        labelMessage.setText("One of the input is empty");
    }

}
