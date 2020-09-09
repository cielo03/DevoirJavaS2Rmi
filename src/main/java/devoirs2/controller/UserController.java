package devoirs2.controller;

import com.mysql.cj.Session;
import devoirs2.java.model.SessionUtilisateur;
import devoirs2.java.model.Utilisateur;
import devoirs2.utils.Fabrique;
import devoirs2.utils.LoadView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class UserController implements Initializable {

    Utilisateur user = new Utilisateur();
    @FXML
    private TextField logTfd;

    @FXML
    private Button connexionBtn;

    @FXML
    private PasswordField mdpTfd;

    @FXML
    private Button cancelBtn;

    @FXML
    private Button registerBtn;

    @FXML
    private Label incorrectTfd;

    @FXML
    private ImageView incorrectImg;

    @FXML
    private ImageView imgV1;

    @FXML
    private ImageView imgV2;

    @FXML
    private ImageView imgV3;

    @FXML
    private ImageView imgV4;

    @FXML
    void cancelHandler(ActionEvent event) {
        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        stage.close();
    }

    @FXML
    void connexionHandler(ActionEvent event) {
        /*if(logTfd.getText().trim().isEmpty() || mdpTfd.getText().trim().isEmpty()){
            incorrectTfd.setText("  Tous les champs doivent etre remplis");
            File imgFile1 = new File("C:/Users/CELIA/IdeaProjects/DevoirClient/src/main/java/devoirs2/view/icons/error_48px.png");
            Image img1 = new Image(imgFile1.toURI().toString());
            incorrectImg.setImage(img1);
        }*/
        if (login().equals("success")) {
            incorrectTfd.setText("  Connexion établie!Redirection...");
            incorrectTfd.setTextFill(Color.GREEN);
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            stage.close();
            SessionUtilisateur.setSession(user);
            LoadView.showView("Tableau de bord", "Menu.fxml", 1);

        } else {
            if (login().equals("success") && user.getEtat() == -1) {
                incorrectTfd.setText("Désolée cher utilisateur votre contrat est résilié ");
                File imgFile1 = new File("C:/Users/CELIA/IdeaProjects/DevoirClient/src/main/java/devoirs2/view/icons/crying_48px.png");
                Image img1 = new Image(imgFile1.toURI().toString());
                incorrectImg.setImage(img1);
            } else {
                incorrectTfd.setText("  Login ou mdp incorrecte");
                File imgFile1 = new File("C:/Users/CELIA/IdeaProjects/DevoirClient/src/main/java/devoirs2/view/icons/crying_48px.png");
                Image img1 = new Image(imgFile1.toURI().toString());
                incorrectImg.setImage(img1);
            }
        }
    }

    private String login(){
        String log = logTfd.getText().trim();
        String mdp = mdpTfd.getText().trim();

        try {
            user = Fabrique.getiUtilisateur().findByLogin(log);
            if (user != null && user.getEtat()!= -1){
                return "success";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "echec";
    }

    @FXML
    void registerHandler(ActionEvent event) {
        registerBtn.getScene().getWindow().hide();
        LoadView.showView("Formulaire d'inscription", "InscriptionUser.fxml",1);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        File imgFile1 = new File("C:/Users/CELIA/IdeaProjects/DevoirClient/src/main/java/devoirs2/view/icons/user_40px.png");
        Image img1 = new Image(imgFile1.toURI().toString());
        imgV1.setImage(img1);

        File imgFile2 = new File("C:/Users/CELIA/IdeaProjects/DevoirClient/src/main/java/devoirs2/view/icons/key_40px.png");
        Image img2 = new Image(imgFile2.toURI().toString());
        imgV2.setImage(img2);

        File imgFile3 = new File("C:/Users/CELIA/IdeaProjects/DevoirClient/src/main/java/devoirs2/view/icons/login_32px.png");
        Image img3 = new Image(imgFile3.toURI().toString());
        imgV3.setImage(img3);

        File imgFile4 = new File("C:/Users/CELIA/IdeaProjects/DevoirClient/src/main/java/devoirs2/view/icons/cancel_32px.png");
        Image img4 = new Image(imgFile4.toURI().toString());
        imgV4.setImage(img4);
    }
}
