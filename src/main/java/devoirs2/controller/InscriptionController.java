package devoirs2.controller;

import devoirs2.java.model.Client;
import devoirs2.java.model.Profile;
import devoirs2.java.model.Utilisateur;
import devoirs2.utils.Fabrique;
import devoirs2.utils.LoadView;
import devoirs2.utils.Utils;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.awt.*;
import java.io.File;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;


public class InscriptionController implements Initializable {

    //private FileChooser inscrip = new FileChooser();
    @FXML
    private Button enregBtn;

    @FXML
    private Button cancelBtn;

    @FXML
    private ImageView imgView1;

    @FXML
    private ImageView imgView2;

    @FXML
    private ImageView imgView3;

    @FXML
    private ImageView imgView4;

    @FXML
    private ImageView imgView5;

    @FXML
    private ImageView imgView6;

    @FXML
    private ImageView imgView7;

    @FXML
    private ImageView imgView8;

    @FXML
    private ImageView imgView9;

    @FXML
    private Button logBtn;

    @FXML
    private TextField nomTfd;

    @FXML
    private TextField prenomTfd;

    @FXML
    private TextField adrTfd;

    @FXML
    private TextField loginTfd;

    @FXML
    private DatePicker dateTfd;

    @FXML
    private ChoiceBox<Profile> profileCbx;

    @FXML
    private PasswordField mdpPfd;


    @FXML
    void cancelHandler(ActionEvent event) {
        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        stage.close();
    }

    @FXML
    void connexionHandler(ActionEvent event) {
        if(nomTfd.getText().trim().isEmpty() || prenomTfd.getText().trim().isEmpty() || dateTfd.getValue().equals(null) ||
                adrTfd.getText().trim().isEmpty() || loginTfd.getText().trim().isEmpty() || profileCbx.getItems().isEmpty() ||
                mdpPfd.getText().trim().isEmpty()){
            Utils.showMessage("Gestion des utilisateurs", "Ajout des utilisateurs", "Tous les champs de saisie doivent etre remplis!!!");
        }
        Utilisateur user = new Utilisateur();
        user.setNom(nomTfd.getText().trim());
        user.setPrenom(prenomTfd.getText().trim());
        user.setAdresse(adrTfd.getText().trim());
        Date date = new Date();
        try {
            date = new SimpleDateFormat("dd/MM/yyyy").parse(dateTfd.toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        user.setDatenaissance(date);
        try {
            user.setProfile(Fabrique.getiProfile().findById(profileCbx.getValue().getId()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        user.setLogin(loginTfd.getText().trim());
        user.setPassword(mdpPfd.getText().trim());
        try {
            Fabrique.getiUtilisateur().ajouterUtilisateur(user);
            Utils.showMessage("Gestion des utilisateurs", "Ajout des utilisateurs", "L'utilisateur a ete ajoutee avec succes!!");
            LoadView.showView("Tableau de bord", "Menu.fxml",1);
        } catch (Exception e) {
            e.printStackTrace();
            Utils.showMessage("Gestion des commandes", "Ajout des clients", "Erreu lors de l'ajout de l'utilisateur:  "+e.getMessage());
        }
    }

    @FXML
    void registerHandler(ActionEvent event) {
        LoadView.showView("Formulaire de connexion", "FormUtilisateur.fxml",1);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            List<Profile> prof = Fabrique.getiProfile().allProfile();
            profileCbx.setItems(FXCollections.observableArrayList(prof));

        } catch (Exception e) {
            e.printStackTrace();
        }
        File imgFile1 = new File("C:/Users/CELIA/IdeaProjects/DevoirClient/src/main/java/devoirs2/view/icons/name_tag_24px.png");
         Image img1 = new Image(imgFile1.toURI().toString());
         imgView1.setImage(img1);

        File imgFile2 = new File("C:/Users/CELIA/IdeaProjects/DevoirClient/src/main/java/devoirs2/view/icons/name_tag_24px.png");
        Image img2 = new Image(imgFile2.toURI().toString());
        imgView2.setImage(img2);

        File imgFile3 = new File("C:/Users/CELIA/IdeaProjects/DevoirClient/src/main/java/devoirs2/view/icons/calendar_26px.png");
        Image img3 = new Image(imgFile3.toURI().toString());
        imgView3.setImage(img3);

        File imgFile4 = new File("C:/Users/CELIA/IdeaProjects/DevoirClient/src/main/java/devoirs2/view/icons/address_32px.png");
        Image img4 = new Image(imgFile4.toURI().toString());
        imgView4.setImage(img4);

        File imgFile5 = new File("C:/Users/CELIA/IdeaProjects/DevoirClient/src/main/java/devoirs2/view/icons/customer_32px.png");
        Image img5 = new Image(imgFile5.toURI().toString());
        imgView5.setImage(img5);

        File imgFile6 = new File("C:/Users/CELIA/IdeaProjects/DevoirClient/src/main/java/devoirs2/view/icons/password_26px.png");
        Image img6 = new Image(imgFile6.toURI().toString());
        imgView6.setImage(img6);

        File imgFile7 = new File("C:/Users/CELIA/IdeaProjects/DevoirClient/src/main/java/devoirs2/view/icons/profile_32px.png");
        Image img7 = new Image(imgFile7.toURI().toString());
        imgView7.setImage(img7);

        File imgFile8 = new File("C:/Users/CELIA/IdeaProjects/DevoirClient/src/main/java/devoirs2/view/icons/login_32px.png");
        Image img8 = new Image(imgFile8.toURI().toString());
        imgView8.setImage(img8);

        File imgFile9 = new File("C:/Users/CELIA/IdeaProjects/DevoirClient/src/main/java/devoirs2/view/icons/cancel_32px.png");
        Image img9 = new Image(imgFile9.toURI().toString());
        imgView9.setImage(img9);

    }
}
