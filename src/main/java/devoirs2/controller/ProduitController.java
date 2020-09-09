package devoirs2.controller;

import devoirs2.java.model.Entree;
import devoirs2.java.model.Produit;
import devoirs2.utils.Fabrique;
import devoirs2.utils.LoadView;
import devoirs2.utils.Utils;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.css.PseudoClass;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class ProduitController implements Initializable {

        @FXML
        private TextField descTfd;

        @FXML
        private TextField pmTfd;

        @FXML
        private TextField puTfd;

        @FXML
        private TextField qtestckTfd;

        @FXML
        private TextField dateEntreeTfd;

        @FXML
        private TextField qteEntreeTfd;

        @FXML
        private Button ajoutBtn;

        @FXML
        private TableView<Produit> DtTb;

        @FXML
        private TableColumn<Produit, String> descCol;

        @FXML
        private TableColumn<Produit, Integer> prixMinCol;

        @FXML
        private TableColumn<Produit, Integer> pUCol;

        @FXML
        private TableColumn<Produit, Integer> qteSCol;

        @FXML
        private Button selectBtn;

        @FXML
        private Button modifBtn;

        @FXML
        private Button supBtn;

        @FXML
        void ajoutHandler(ActionEvent event) {
            if(descTfd.getText().trim().isEmpty() || pmTfd.getText().trim().isEmpty() || puTfd.getText().trim().isEmpty() ||
                    qtestckTfd.getText().trim().isEmpty() || dateEntreeTfd.getText().trim().isEmpty() || qteEntreeTfd.getText().trim().isEmpty()) {
                Utils.showMessage("Gestion des Stocks", "Ajout des produits", "Tous les champs de saisie doivent etre remplis!!!");
            }
            Produit produit = new Produit();
            Entree entree = new Entree();
            produit.setDescription(descTfd.getText().trim());
            int pm = Integer.parseInt( pmTfd.getText().trim());
            produit.setPrixMin(pm);
            int pu = Integer.parseInt( puTfd.getText().trim());
            produit.setPrixU(pu);
            int qte = Integer.parseInt( qtestckTfd.getText().trim());
            produit.setQteStock(qte);
            try {
                Fabrique.getiProduit().ajouterProduit(produit);
            } catch (Exception e) {
                e.printStackTrace();
            }
            Produit pro =  new Produit();
            try {
                pro = Fabrique.getiProduit().findByDesc(descTfd.getText().trim());
            } catch (Exception e) {
                e.printStackTrace();
            }
            //System.out.println(pro.getId());
            entree.setProduit(pro);

            Date date = new Date();
            try {
                date = new SimpleDateFormat("dd/MM/yyyy").parse(dateEntreeTfd.getText());
            } catch (ParseException e) {
                e.printStackTrace();
                Utils.showMessage("Gestion des stocks", "Ajout des produits", "Erreu lors de l'ajout du produit:  "+e.getMessage());
            }
            entree.setDateEntree(date);
            int qte2 = Integer.parseInt( qteEntreeTfd.getText().trim());
            entree.setQteEntree(qte2);

            try {
                Fabrique.getiEntree().ajouterEntree(entree);
                Utils.showMessage("Gestion des stocks", "Ajout des produits", "Ajout Reussii!!!!");
            } catch (Exception e) {
                e.printStackTrace();
                Utils.showMessage("Gestion des stocks", "Ajout des produits", "Erreu lors de l'ajout de l'entree:  "+e.getMessage());
            }
        }

        @FXML
        void modifHandler(ActionEvent event) {
            Produit pro = DtTb.getSelectionModel().getSelectedItem();
            pro.setDescription(descTfd.getText().trim());
            pro.setPrixMin(Integer.parseInt(pmTfd.getText().trim()));
            pro.setPrixU(Integer.parseInt(puTfd.getText().trim()));
            pro.setQteStock(Integer.parseInt(qtestckTfd.getText().trim()));

            try {
                Fabrique.getiProduit().modifProduit(pro);
                Utils.showMessage("Gestion des stocks", "Mise a jour des produits", "La produit a ete modifie avec succes!!");
            } catch (Exception e) {
                e.printStackTrace();
                Utils.showMessage("Gestion des stocks", "Mise a jour des produits", "Erreu lors de l'ajout du produit:  "+e.getMessage());
            }
        }

        @FXML
        void selectHandler(ActionEvent event) {
            Produit pro = DtTb.getSelectionModel().getSelectedItem();
            descTfd.setText(pro.getDescription());
            pmTfd.setText(Integer.toString(pro.getPrixMin()));
            puTfd.setText(Integer.toString(pro.getPrixU()));
            qtestckTfd.setText(Integer.toString(pro.getQteStock()));

        }

        @FXML
        void supHandler(ActionEvent event) {
            Produit pro = DtTb.getSelectionModel().getSelectedItem();
            try {
                Fabrique.getiProduit().deleteProduit(pro);
                Utils.showMessage("Gestion des stocks","Suppression des produits","produit supprimé");

            } catch (Exception e) {
                e.printStackTrace();
                Utils.showMessage("Gestion des stocks","Suppression des produits","Erreur lors de la suppression!!!!!! :"+e.getMessage());
            }

        }
    @FXML
    void approtHandler(ActionEvent event) {
        Produit pro = DtTb.getSelectionModel().getSelectedItem();

            LoadView.showView("Approvisionnement", "ApproProduit.fxml",1);
    }


    public void initialize(URL location, ResourceBundle resources) {

        DtTb.getItems().clear();
        descCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        prixMinCol.setCellValueFactory(new PropertyValueFactory<>("prixMin"));
        pUCol.setCellValueFactory(new PropertyValueFactory<>("prixU"));
        qteSCol.setCellValueFactory(new PropertyValueFactory<>("qteStock"));
        try {
            //DtTb.setItems(FXCollections.observableArrayList(Fabrique.getiEntree().allEntrees()));
            DtTb.setItems(FXCollections.observableArrayList(Fabrique.getiProduit().allProduits()));
            List<Produit> produits = Fabrique.getiProduit().allProduits();
            for (Produit p : produits){
                DtTb.getItems().add(p);
            }
        }catch (Exception e){
            Utils.showMessage("Gestion des stocks","Gestion des produit","Erreur!!!!!! :"+e.getMessage());
        }
    }



    }
