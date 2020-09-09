package devoirs2.controller;

import devoirs2.java.model.Client;
import devoirs2.java.model.Produit;
import devoirs2.java.model.TypeClient;
import devoirs2.utils.Fabrique;
import devoirs2.utils.Utils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
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

import static javafx.collections.FXCollections.observableArrayList;

public class ClientController implements Initializable {

    @FXML
    private TextField nomClientTfd;

    @FXML
    private TextField pnomClientTfd;

    @FXML
    private TextField cniClientTfd;

    @FXML
    private TextField adrClientTfd;

    @FXML
    private TextField dateClientTfd;

    @FXML
    private ChoiceBox<TypeClient> typeClientCbx;

    @FXML
    private Button ajoutCliBtn;

    @FXML
    private TableView<Client> ClientTab;

    @FXML
    private TableColumn<Client, String> nomClientCol;

    @FXML
    private TableColumn<Client, String> pnomClientCol;

    @FXML
    private TableColumn<Client, String> cniClientCol;

    @FXML
    private TableColumn<Client, String> adrClientCol;

    @FXML
    private TableColumn<Client, String> typeClientCol;

    @FXML
    private TableColumn<Client, Date> dateClientCol;

    @FXML
    private Button selectCliBtn;

    @FXML
    private Button modifCliBtn;

    @FXML
    void ajoutHandler(ActionEvent event) {
        if(nomClientTfd.getText().trim().isEmpty() || pnomClientTfd.getText().trim().isEmpty() || cniClientTfd.getText().trim().isEmpty() ||
                adrClientTfd.getText().trim().isEmpty() || dateClientTfd.getText().trim().isEmpty() || typeClientCbx.getItems().isEmpty()) {
            Utils.showMessage("Gestion des commandes", "Ajout des clients", "Tous les champs de saisie doivent etre remplis!!!");
        }
        Client client = new Client();
        client.setNom(nomClientTfd.getText().trim());
        client.setPrenom(pnomClientTfd.getText().trim());
        client.setCni(cniClientTfd.getText().trim());
        client.setAdresse(adrClientTfd.getText().trim());
        Date date = new Date();
        try {
            date = new SimpleDateFormat("dd/MM/yyyy").parse(dateClientTfd.getText());
            client.setDatenaissance(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        try {
            client.setTypeClient(Fabrique.getiTypeClient().findById(typeClientCbx.getValue().getId()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            Fabrique.getiClient().ajouterClient(client);
            Utils.showMessage("Gestion des commandes", "Ajout des clients", "Le client a ete ajoutee avec succes!!");
            updateTable();
        } catch (Exception e) {
            e.printStackTrace();
            Utils.showMessage("Gestion des commandes", "Ajout des clients", "Erreu lors de l'ajout du client:  "+e.getMessage());
        }
    }

    @FXML
    void modifHandler(ActionEvent event) {
        Client cli = ClientTab.getSelectionModel().getSelectedItem();
        cli.setNom(nomClientTfd.getText().trim());
        cli.setPrenom(pnomClientTfd.getText().trim());
        cli.setCni(cniClientTfd.getText().trim());
        cli.setAdresse(adrClientTfd.getText().trim());
        Date date = new Date();
        try {
            date = new SimpleDateFormat("dd/MM/yyyy").parse(dateClientTfd.getText());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        cli.setDatenaissance(date);
        try {
            cli.setTypeClient(Fabrique.getiTypeClient().findById(typeClientCbx.getValue().getId()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            Fabrique.getiClient().modifClient(cli);
            Utils.showMessage("Gestion des commandes", "Modification des clients", "Le client a ete modifiee avec succes!!");
        } catch (Exception e) {
            e.printStackTrace();
            Utils.showMessage("Gestion des commandes", "Modification des clients", "Erreu lors de la modification du client:  "+e.getMessage());
        }
    }

    @FXML
    void selectHandler(ActionEvent event) {
        Client cli = ClientTab.getSelectionModel().getSelectedItem();
        nomClientTfd.setText(cli.getNom());
        pnomClientTfd.setText(cli.getPrenom());
        cniClientTfd.setText(cli.getCni());
        adrClientTfd.setText(cli.getAdresse());
        dateClientTfd.setText(String.valueOf(cli.getDatenaissance()));
        try {
            List<TypeClient> typesCli = Fabrique.getiTypeClient().allTypeClients();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    void updateTable(){
        ClientTab.getItems().clear();
        nomClientCol.setCellValueFactory(new PropertyValueFactory<>("nom"));
        pnomClientCol.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        cniClientCol.setCellValueFactory(new PropertyValueFactory<>("cni"));
        adrClientCol.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        typeClientCol.setCellValueFactory(new PropertyValueFactory<>("typeClient"));
        dateClientCol.setCellValueFactory(new PropertyValueFactory<>("datenaissance"));
        try {
            List<Client> clients = Fabrique.getiClient().allClients();
            ClientTab.setItems(FXCollections.observableArrayList(clients));

        }catch (Exception e){
            Utils.showMessage("Gestion des Commandes","Gestion des clients","Erreur!!!!!! :"+e.getMessage());
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            List<TypeClient> types = Fabrique.getiTypeClient().allTypeClients();
            typeClientCbx.setItems(FXCollections.observableArrayList(types));

        } catch (Exception e) {
            e.printStackTrace();
        }
        updateTable();


    }

}
