package devoirs2.controller;

import devoirs2.java.model.Profile;
import devoirs2.java.model.Utilisateur;
import devoirs2.utils.Fabrique;
import devoirs2.utils.Utils;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class ActUserController implements Initializable {

    @FXML
    private TextField nomUserTfd;

    @FXML
    private TextField prenomUserTfd;

    @FXML
    private TextField dateUserTfd;

    @FXML
    private TextField adrUserTfd;

    @FXML
    private TextField logUserTfd;

    @FXML
    private ChoiceBox<Profile> profileUserCbx;

    @FXML
    private Button bloquerBtn;

    @FXML
    private Button selectBtn;

    @FXML
    private Button ModifBtn;

    @FXML
    private TableView<Utilisateur> table_users;

    @FXML
    private TableColumn<Utilisateur, String> nomUserCol;

    @FXML
    private TableColumn<Utilisateur, String> prenomUserCol;

    @FXML
    private TableColumn<Utilisateur, Date> dateUserCol;

    @FXML
    private TableColumn<Utilisateur, String> adrUserCol;

    @FXML
    private TableColumn<Utilisateur, String> logUserCol;

    @FXML
    private TableColumn<Utilisateur, String> profileUserCol;

    @FXML
    void BloquerHandler(ActionEvent event) {
        Utilisateur user = table_users.getSelectionModel().getSelectedItem();
        try {
            Fabrique.getiUtilisateur().deleteUtilisateur(user);
            Utils.showMessage("Gestion des utilisateur","Blocage d'un utilisateur","utilisateur bloqué");

        } catch (Exception e) {
            e.printStackTrace();
            Utils.showMessage("Gestion des stocks","Blocage d'un utilisateur","Erreur lors de la suppression!!!!!! :"+e.getMessage());
        }

    }

    @FXML
    void SelectHandler(ActionEvent event) {

    }
    @FXML
    void ModifHandler(ActionEvent event) {
        Utilisateur user = table_users.getSelectionModel().getSelectedItem();
        user.setNom(nomUserTfd.getText().trim());
        user.setPrenom(prenomUserTfd.getText().trim());
        user.setAdresse(adrUserTfd.getText().trim());
        Date date = new Date();
        try {
            date = new SimpleDateFormat("dd/MM/yyyy").parse(dateUserTfd.getText());
            user.setDatenaissance(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int id = profileUserCbx.getValue().getId();
        try {
            user.setProfile(Fabrique.getiProfile().findById(id));
        } catch (Exception e) {
            e.printStackTrace();
        }
        user.setLogin(logUserTfd.getText().trim());

        try {
            Fabrique.getiUtilisateur().modifUtilisateur(user);
            Utils.showMessage("Gestion des utilisateurs", "Mise a jour des utilisateurs", "L'utilisateur' a ete modifiee avec succes!!");
        } catch (Exception e) {
            e.printStackTrace();
            Utils.showMessage("Gestion des utilisateurs", "Mise a jour des utilisateurs", "Erreu lors de l'ajout de l'utilisateur:  "+e.getMessage());
        }
    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        updateTableUser();

    }

    void updateTableUser(){
        table_users.getItems().clear();
        nomUserCol.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenomUserCol.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        dateUserCol.setCellValueFactory(new PropertyValueFactory<>("datenaissance"));
        adrUserCol.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        logUserCol.setCellValueFactory(new PropertyValueFactory<>("login"));
        profileUserCol.setCellValueFactory(new PropertyValueFactory<>("profile"));
        try {
            List<Utilisateur> users = Fabrique.getiUtilisateur().allUsersByEtat();
            table_users.setItems(FXCollections.observableArrayList(users));
            for (Utilisateur u : users){
                table_users.getItems().add(u);
            }
        }catch (Exception e){
            Utils.showMessage("Gestion des Utilisateurs","Liste","Erreur!!!!!! :"+e.getMessage());
        }
    }

    public void getSelected(javafx.scene.input.MouseEvent mouseEvent) {

        Utilisateur tu = table_users.getSelectionModel().getSelectedItem();
        nomUserTfd.setText(tu.getNom());
        prenomUserTfd.setText(tu.getPrenom());
        dateUserTfd.setText(String.valueOf(tu.getDatenaissance()));
        adrUserTfd.setText(tu.getAdresse());
        logUserTfd.setText(tu.getLogin());
        try {
            List<Profile> prof = Fabrique.getiProfile().allProfile();
            profileUserCbx.setItems(FXCollections.observableArrayList(prof));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void eventBloq(javafx.scene.input.MouseEvent mouseEvent) {
        nomUserCol.setStyle("-fx-background-color : #53639F");
        prenomUserCol.setStyle("-fx-background-color : #53639F");
        dateUserCol.setStyle("-fx-background-color : #53639F");
        adrUserCol.setStyle("-fx-background-color : #53639F");
        logUserCol.setStyle("-fx-background-color : #53639F");
        profileUserCol.setStyle("-fx-background-color : #53639F");

    }
}
