package GUI.Controllers;

import BE.User;
import GUI.Models.MainModel;
import GUI.Utils.SceneSetter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class StudentsController implements Initializable {
    MainModel mainModel;
    MAdminStudentViewController menuController;
    User userToShow;
    SceneSetter sceneSetter;

    @FXML
    private Button btnCreate;

    @FXML
    private Button btnEdit;

    @FXML
    private Label labelNameView;

    @FXML
    private Label labelFirstName, labelUsername, labelLastName;

    @FXML
    private TableColumn<User, String> tableColFname;

    @FXML
    private TableColumn<User, String> tableColLname;

    @FXML
    private TableView<User> tableViewUsers;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        sceneSetter = new SceneSetter();
        try {
            mainModel = new MainModel();
            menuController = new MAdminStudentViewController();
            updateTableView();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        labelNameView.setText(mainModel.getRoleName());
    }

    public void updateTableView() throws Exception {
        tableViewUsers.getItems().clear();
        tableColFname.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        tableColLname.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        tableViewUsers.getItems().setAll(mainModel.getUsersByRole(mainModel.getRoleId()));
    }

    @FXML
    void toCreateNewUser(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/GUI/Views/NewUserView.fxml"));
        sceneSetter.setScene(loader);
    }

    @FXML
    void toDeleteCurUser(ActionEvent event) throws Exception {
        User userToDelete = tableViewUsers.getSelectionModel().getSelectedItem();
        System.out.println("User is about to be deleted: " + userToDelete);
        mainModel.deleteUser(userToDelete);
        updateTableView();
        System.out.println("button to Delete user is clicked");
    }

    @FXML
    void toEditCurrentUser(ActionEvent event) throws IOException {
        userToShow = tableViewUsers.getSelectionModel().getSelectedItem();
        System.out.println("chosen user to edit: " + userToShow);
        EditUserController editUserController = new EditUserController(userToShow);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/Views/EditUserView.fxml"));
        loader.setController(editUserController);
        sceneSetter.setScene(loader);
    }

    @FXML
    void toShowUser(MouseEvent event) {
        userToShow = tableViewUsers.getSelectionModel().getSelectedItem();
        labelFirstName.setText(userToShow.getFirstName());
        labelLastName.setText(userToShow.getLastName());
        labelUsername.setText(userToShow.getLoginName());
    }

}
