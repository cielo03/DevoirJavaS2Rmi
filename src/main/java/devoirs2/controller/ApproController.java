package devoirs2.controller;

import devoirs2.java.model.Entree;
import devoirs2.java.model.Produit;
import devoirs2.utils.Fabrique;
import devoirs2.utils.LoadView;
import devoirs2.utils.Utils;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class ApproController implements Initializable{

    @FXML
    private TextField descApproTfd;

    @FXML
    private TextField qteAppTfd;

    @FXML
    private TextField dateApproTfd;

    @FXML
    private Button ajoutApproBtn;

    @FXML
    private TableView<Entree> DtTbAppro;

    @FXML
    private TableColumn<Entree, Date> dateApprCol;

    @FXML
    private TableColumn<Entree, Integer> qteApproCol;

    @FXML
    private TableColumn<Entree, String> prodApproCol;

    @FXML
    void ajoutApproHandler(ActionEvent event) {
        if(descApproTfd.getText().trim().isEmpty() || qteAppTfd.getText().trim().isEmpty() || dateApproTfd.getText().trim().isEmpty()) {
            Utils.showMessage("Gestion des personnes", "Ajout des produits", "Tous les champs de saisie doivent etre remplis!!!");
        }
        else {
            Produit produit = new Produit();
            Entree entree = new Entree();
            int qte = Integer.parseInt( qteAppTfd.getText().trim());
            entree.setQteEntree(qte);
            Date date = new Date();
            try {
                date = new SimpleDateFormat("dd/MM/yyyy").parse(dateApproTfd.getText());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            entree.setDateEntree(date);
            int qteN = Integer.parseInt( qteAppTfd.getText().trim());
            try {
                produit = Fabrique.getiProduit().findByDesc(descApproTfd.getText().trim());
                int qteA = produit.getQteStock();
                produit.setQteStock(qteN+qteA);

            } catch (Exception e) {
                e.printStackTrace();
            }
            entree.setProduit(produit);

            try {
                Fabrique.getiEntree().ajouterEntree(entree);
                Utils.showMessage("Gestion des stocks", "Approvisionnement des produits", "Approvisionnement Reussii!!!!");
                updateTab();
            } catch (Exception e) {
                e.printStackTrace();
                Utils.showMessage("Gestion des stocks", "Approvisionnement des produits", "Erreu lors de l'Approvisionnement de l'entree:  "+e.getMessage());
            }


        }

    }

    @FXML
    void retourApproHandler(ActionEvent event) {
        LoadView.showView("Formulaire de Produit", "FormProduit.fxml",1);
    }

    void updateTab(){
        DtTbAppro.getItems().clear();
        dateApprCol.setCellValueFactory(new PropertyValueFactory<>("dateEntree"));
        qteApproCol.setCellValueFactory(new PropertyValueFactory<>("qteEntree"));
        prodApproCol.setCellValueFactory(new PropertyValueFactory<>("produit"));

        try {
            //DtTbAppro.setItems(FXCollections.observableArrayList(Fabrique.getiEntree().allEntrees()));
            DtTbAppro.setItems(FXCollections.observableArrayList(Fabrique.getiEntree().allEntrees()));
            List<Entree> entreees = Fabrique.getiEntree().allEntrees();
            for (Entree e : entreees){
                DtTbAppro.getItems().add(e);
            }
        }catch (Exception e){
            Utils.showMessage("Gestion des stocks","Gestion des Entrees","Erreur!!!!!! :"+e.getMessage());
        }
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
       updateTab();
    }
}
