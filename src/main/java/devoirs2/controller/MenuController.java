package devoirs2.controller;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import devoirs2.java.model.*;
import devoirs2.utils.Fabrique;
import devoirs2.utils.LoadView;
import devoirs2.utils.Utils;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.util.converter.IntegerStringConverter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

public class MenuController implements Initializable {
    Utilisateur user = new Utilisateur();
    private static Client cli = null;
    private static Produit pro = null;
    private static Commande nm = null;
    //private static  Commande cmd = new Commande();
    List<DetailsCommande> details ;
    int mnt = 0;
    double mntfac = 0;

    @FXML
    private AnchorPane grPane;

    @FXML
    private ImageView logoImg;

    @FXML
    private Button homeBtn;

    @FXML
    private Button gesproBtn;

    @FXML
    private Button approBtn;

    @FXML
    private Button invBtn;

    @FXML
    private Button gesClientsBtn;


    @FXML
    private Button selectcmdBtn;


    @FXML
    private Button ajoutCmdBtn;

    @FXML
    private Button gesCmdBtn;

    @FXML
    private Button gesPaymntBtn;

    @FXML
    private Button gesUserBtn;

    @FXML
    private Button deconnexionBtn;

    @FXML
    private Label userLbl;

    @FXML
    private Button nvUserBtn;


    @FXML
    private Pane accueilPane;

    @FXML
    private Pane produitPane;

    @FXML
    private TableView<Produit> DtTbPro;

    @FXML
    private TableColumn<Produit, String> descProCol;

    @FXML
    private TableColumn<Produit, Integer> prixMinCol;

    @FXML
    private TableColumn<Produit, Integer> pUProCol;

    @FXML
    private TableColumn<Produit, Integer> qteSProCol;

    @FXML
    private TextField descProTfd;

    @FXML
    private TextField pmProTfd;

    @FXML
    private TextField puProTfd;

    @FXML
    private TextField qtestckProTfd;

    @FXML
    private TextField dateEntreeProTfd;

    @FXML
    private TextField qteEntreeProTfd;

    @FXML
    private Button ajoutProBtn;

    @FXML
    private Button selectProBtn;

    @FXML
    private Button modifProBtn;

    @FXML
    private Button supProBtn;

    @FXML
    private Button approProBtn;

    @FXML
    private Pane clientPane;

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
    private Pane userPane;

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
    private Button bloquerUserBtn;

    @FXML
    private Button ModifUserBtn;

    @FXML
    private Pane gestCmdPane;

    @FXML
    private TableView<Commande> table_commandes;

    @FXML
    private TableColumn<Commande, String> numerocmdCol;

    @FXML
    private TableColumn<Commande, Integer> montantCmdCol;

    @FXML
    private TableColumn<Commande, Date> dateCmdCol;

    @FXML
    private TableColumn<Commande, String> nomCliCmdCol;

    @FXML
    private TextField dateLivraisonCmdTfd;

    @FXML
    private Button bloquerCmdBtn;

    @FXML
    private Pane inventairePane;

    @FXML
    private TableView<Entree> tableI_entrees;

    @FXML
    private TableColumn<Entree, Date> dateIEntreeCol;

    @FXML
    private TableColumn<Entree, Integer> qteIEntreeCol;

    @FXML
    private TableColumn<Entree, String> prodEntreeCol;

    @FXML
    private TableView<Sortie> tableI_sorties;

    @FXML
    private TableColumn<Sortie, Date> dateSortieCol;

    @FXML
    private TableColumn<Sortie, Integer> qteSortieCol;

    @FXML
    private TableColumn<Sortie, String> prodSortieCol;

    @FXML
    private Pane approPane;

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
    private Button retourApproBtn;

    @FXML
    private Pane facturePane;

    @FXML
    private TextField mntFacTfd;

    @FXML
    private TextField numFacTfd;

    @FXML
    private TextField nomCliFacTfd;

    @FXML
    private TextField typeCliFacTfd;

    @FXML
    private TextField cniCliFacTfd;

    @FXML
    private TextField numCmdFacTfd;

    @FXML
    private TextField mntCmdFacTfd;

    @FXML
    private TextField dateLivraisonFacTfd;

    @FXML
    private Button EnregFacBtn;

    @FXML
    private Pane ajouCmdPane;

    @FXML
    private TableView<DetailsCommande> CommandeTab;

    @FXML
    private TableColumn<DetailsCommande, String> desiCol;

    @FXML
    private TableColumn<DetailsCommande, Integer> qteCmdeeCol;

    @FXML
    private TableColumn<DetailsCommande, Integer> puProCol;

    @FXML
    private TableColumn<DetailsCommande, Integer> totalCmdCol;

    @FXML
    private TextField mntCmdTfd;

    @FXML
    private Button ImprimBtn;

    @FXML
    private TextField numCmdTfd;

    @FXML
    private TextField nomCliTfd;

    @FXML
    private TextField typeCliTfd;

    @FXML
    private TextField cniCliTfd;

    @FXML
    private Button EnregBtn;

    @FXML
    private ChoiceBox<Produit> pdtsCbx;

    @FXML
    private TextField puProdTfd;

    @FXML
    private TextField qtsProTfd;

    @FXML
    private TextField qteCmdeTfd;

    @FXML
    private Button ajoutBtn;

    @FXML
    private Button suppBtn;

    @FXML
    private Button selectBtn;

    @FXML
    private LineChart<Date,Number> lineChart;

    @FXML
    private CategoryAxis x;

    @FXML
    private NumberAxis y;



    @FXML
    void BloquerCmdHandler(ActionEvent event) {
        Commande cmd = table_commandes.getSelectionModel().getSelectedItem();
        try {
            Fabrique.getiCommande().deleteCommande(cmd);
            Utils.showMessage("Gestion des commandes","Commande Supprimée","utilisateur bloqué");
            listeCommandes();
        } catch (Exception e) {
            e.printStackTrace();
            Utils.showMessage("Gestion des commandes","ECHEC","Erreur lors de la suppression!!!!!! :"+e.getMessage());
        }
    }

    @FXML
    void NvUserHandler(ActionEvent event) {
        LoadView.showView("Inscription", "InscriptionUser.fxml",1);
    }

    
    @FXML
    void BloquerUserHandler(ActionEvent event) {
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
    void ModifUserHandler(ActionEvent event) {
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
            updateTableUser();
        } catch (Exception e) {
            e.printStackTrace();
            Utils.showMessage("Gestion des utilisateurs", "Mise a jour des utilisateurs", "Erreu lors de l'ajout de l'utilisateur:  "+e.getMessage());
        }


    }


    @FXML
    void ajoutApproHandler(ActionEvent event) {
        if(descApproTfd.getText().trim().isEmpty() || qteAppTfd.getText().trim().isEmpty() || dateApproTfd.getText().trim().isEmpty()) {
            Utils.showMessage("Gestion des personnes", "Ajout des produits", "Tous les champs de saisie doivent etre remplis!!!");
        }
        else {
            Produit produit = new Produit();
            Entree entree = new Entree();
            int qte = Integer.parseInt(qteAppTfd.getText().trim());
            entree.setQteEntree(qte);
            Date date = new Date();
            try {
                date = new SimpleDateFormat("dd/MM/yyyy").parse(dateApproTfd.getText());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            entree.setDateEntree(date);
            try {
                produit = Fabrique.getiProduit().findByDesc(descApproTfd.getText().trim());
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (produit != null) {
                try {
                    int qteN = Integer.parseInt(qteAppTfd.getText().trim());


                    int qteA = produit.getQteStock();

                    produit.setQteStock(qteN + qteA);

                    Fabrique.getiProduit().modifProduit(produit);
                    entree.setProduit(produit);
                } catch (Exception e) {
                    e.printStackTrace();
                }


                try {
                    Fabrique.getiEntree().ajouterEntree(entree);
                    Utils.showMessage("Gestion des stocks", "Approvisionnement des produits", "Approvisionnement Reussii!!!!");
                    updateTabAppro();
                    descApproTfd.setText("");
                    qteAppTfd.setText("");
                    dateApproTfd.setText("");

                } catch (Exception e) {
                    e.printStackTrace();
                    Utils.showMessage("Gestion des stocks", "Approvisionnement des produits", "Erreu lors de l'Approvisionnement de l'entree:  " + e.getMessage());
                }

            }
            else {
                Utils.showMessage("Gestion des stocks", "Approvisionnement des produits", "Erreu lors de l'Approvisionnement, ce produit n'existe pas" );
                descApproTfd.setText("");
                qteAppTfd.setText("");
                dateApproTfd.setText("");
            }
        }
    }

    @FXML
    void ajoutCliHandler(ActionEvent event) {
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
            date = new SimpleDateFormat("dd/MM/yyyy").parse(dateClientTfd.getText().trim());
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
            nomClientTfd.setText("");
            pnomClientTfd.setText("");
            adrClientTfd.setText("");
            cniClientTfd.setText("");
            dateClientTfd.setText("");
            updateClientTable();
        } catch (Exception e) {
            e.printStackTrace();
            Utils.showMessage("Gestion des commandes", "Ajout des clients", "Erreu lors de l'ajout du client:  "+e.getMessage());
        }
    }

    @FXML
    void ajoutCmdHandler(ActionEvent event) {
        ajouCmdPane.toFront();
        details = new ArrayList<>();
        numCmdTfd.setText(autoINcremntNum());
        try {
            List<Produit> pro = Fabrique.getiProduit().allProduitsByEtat();
            pdtsCbx.setItems(FXCollections.observableArrayList(pro));

        } catch (Exception e) {
            e.printStackTrace();
        }

        rechercherCli();
        rechercherProd();
        CommandeTab.getItems().clear();
        cniCliTfd.setText("");
        nomCliTfd.setText("");
        typeCliTfd.setText("");
        CommandeTab.setEditable(true);
        qteCmdeeCol.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));

    }

    @FXML
    void ajoutHandler(ActionEvent event) {
        if(qteCmdeTfd.getText().trim().isEmpty() || pdtsCbx.getItems().isEmpty() ||
                puProdTfd.getText().trim().isEmpty() || qtsProTfd.getText().trim().isEmpty()) {
            Utils.showMessage("Gestion des commandes", "Ajout des produits dans la commandes", "Tous les champs de saisie doivent etre remplis!!!");
        }
        int qteC = Integer.parseInt(qteCmdeTfd.getText().trim());
        int pu = Integer.parseInt(puProdTfd.getText().trim());
        int tot = pu*qteC;

        DetailsCommande detail = new DetailsCommande();
        detail.setQteCommande(qteC);
        Produit pro =  new Produit();
        try {
            pro = Fabrique.getiProduit().findByDesc(pdtsCbx.getValue().getDescription());
        } catch (Exception e) {
            e.printStackTrace();
        }
        int qtSA = pro.getQteStock();
        int q = detail.getQteCommande();
        pro.setQteStock(qtSA - q);
        try {
            Fabrique.getiProduit().modifProduit(pro);
        } catch (Exception e) {
            e.printStackTrace();
        }
        detail.setProduit(pro);
        details.add(detail);
        if(Integer.parseInt(qtsProTfd.getText().trim()) < Integer.parseInt(qteCmdeTfd.getText().trim())) {
            Utils.showMessage("Gestion des commandes", "Ajout des details",
                    "Erreur ce produit est en rupture de stock!! vous devriez l'approvisionner!!!");
        }else {
            try {
                Utils.showMessage("Gestion des commandes", "Ajout des details", "Ajout Reussii!!!!");
                qteCmdeTfd.setText("");
                puProdTfd.setText("");
                qtsProTfd.setText("");

                mnt = mnt+tot;
                mntCmdTfd.setText(String.valueOf(mnt));

                updateTableD();
            } catch (Exception e) {
                e.printStackTrace();
                Utils.showMessage("Gestion des commandes", "Ajout des details", "Erreu lors de l'ajout des details:  " + e.getMessage());
            }
        }

    }

    @FXML
    void ajoutProHandler(ActionEvent event) {
        if(descProTfd.getText().trim().isEmpty() || pmProTfd.getText().trim().isEmpty() || puProTfd.getText().trim().isEmpty() ||
                qtestckProTfd.getText().trim().isEmpty() || dateEntreeProTfd.getText().trim().isEmpty() || qteEntreeProTfd.getText().trim().isEmpty()) {
            Utils.showMessage("Gestion des Stocks", "Ajout des produits", "Tous les champs de saisie doivent etre remplis!!!");
        }
        Produit produit = new Produit();
        Entree entree = new Entree();
        produit.setDescription(descProTfd.getText().trim());
        int pm = Integer.parseInt( pmProTfd.getText().trim());
        produit.setPrixMin(pm);
        int pu = Integer.parseInt( puProTfd.getText().trim());
        produit.setPrixU(pu);
        int qte = Integer.parseInt( qtestckProTfd.getText().trim());
        produit.setQteStock(qte);
        try {
            Fabrique.getiProduit().ajouterProduit(produit);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Produit pro =  new Produit();
        try {
            pro = Fabrique.getiProduit().findByDesc(descProTfd.getText().trim());
        } catch (Exception e) {
            e.printStackTrace();
        }
        //System.out.println(pro.getId());
        entree.setProduit(pro);

        Date date = new Date();
        try {
            date = new SimpleDateFormat("dd/MM/yyyy").parse(dateEntreeProTfd.getText());
        } catch (ParseException e) {
            e.printStackTrace();
            Utils.showMessage("Gestion des stocks", "Ajout des produits", "Erreu lors de l'ajout du produit:  "+e.getMessage());
        }
        entree.setDateEntree(date);
        int qte2 = Integer.parseInt( qteEntreeProTfd.getText().trim());
        entree.setQteEntree(qte2);

        try {
            Fabrique.getiEntree().ajouterEntree(entree);
            Utils.showMessage("Gestion des stocks", "Ajout des produits", "Ajout Reussii!!!!");
            descProTfd.setText("");
            pmProTfd.setText("");
            puProTfd.setText("");
            qtestckProTfd.setText("");
            dateEntreeProTfd.setText("");
            qteEntreeProTfd.setText("");
            updateTablePro();
        } catch (Exception e) {
            e.printStackTrace();
            Utils.showMessage("Gestion des stocks", "Ajout des produits", "Erreu lors de l'ajout de l'entree:  "+e.getMessage());
        }
    }

    @FXML
    void approHandler(ActionEvent event) {
        approPane.toFront();

    }

    @FXML
    void approtHandler(ActionEvent event) {
        Produit pro = DtTbPro.getSelectionModel().getSelectedItem();
        approPane.toFront();
    }

    @FXML
    void deconnexionHandler(ActionEvent event) {
        Alert ask = new Alert(Alert.AlertType.CONFIRMATION);
        ask.setTitle("Deconnexion");
        ask.setContentText("Etes-vous sur de vouloir quitter?");

        Optional<ButtonType> result = ask.showAndWait();
        if (result.get() == ButtonType.OK){
            SessionUtilisateur.cleanUserSession();
            LoadView.showView("Authentificaton", "FormUtilisateur.fxml", 1);
        }else if (result.get() == ButtonType.CANCEL){
            System.out.println("Cancellllll");
        }
    }

    @FXML
    void editQte(TableColumn.CellEditEvent edditedCell) {
        DetailsCommande tbSelected = CommandeTab.getSelectionModel().getSelectedItem();
        tbSelected.setQteCommande(Integer.parseInt(edditedCell.getNewValue().toString()));
    }

    @FXML
    void enregistrerFacHandler(ActionEvent event) {
        if(numFacTfd.getText().trim().isEmpty() || cniCliFacTfd.getText().trim().isEmpty() ||
                nomCliFacTfd.getText().trim().isEmpty() || typeCliFacTfd.getText().trim().isEmpty() ||
                numCmdFacTfd.getText().trim().isEmpty() ||dateLivraisonFacTfd.getText().trim().isEmpty() ||
                dateLivraisonFacTfd.getText().trim().isEmpty()) {
            Utils.showMessage("Gestion des factures", "Ajout des elements de la factures", "Tous les champs de saisie doivent etre remplis!!!");
        }
        Facture fac = new Facture();
        DateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();


        fac.setNumero(numFacTfd.getText().trim());

        /*if (clicli.getTypeClient().getLibelle() == "Particulier"){
            mntfac = Integer.parseInt(mntCmdFacTfd.getText().trim());
            cmm.setEtat(2);
            fac.setMontant(mntfac);
        }else if((clicli.getTypeClient().getLibelle() == "Entreprise") && (cmm.getEtat() == 0) && (date.compareTo(cmm.getDateLivraison()) <= 0)){
            mntfac = (cmm.getMontant() * 54)/1000;
            cmm.setEtat(1);
            fac.setMontant(mntfac);
        }else if((clicli.getTypeClient().getLibelle() == "Entreprise") && (cmm.getEtat() == 0) && (date.compareTo(cmm.getDateLivraison()) > 0)){
            int s = (int) (date.getTime() -  cmm.getDateLivraison().getTime());
            int h = s/3600;
            int j = h/24;
            mntfac = (((cmm.getMontant() * 54)/1000)*j)/100;
            cmm.setEtat(1);
            fac.setMontant(mntfac);
        }else if((clicli.getTypeClient().getLibelle() == "Entreprise") && (cmm.getEtat() == 1) && (date.compareTo(cmm.getDateLivraison()) <= 0)){
            mntfac = (cmm.getMontant() * 126)/100;
            cmm.setEtat(2);

        }else if((clicli.getTypeClient().getLibelle() == "Entreprise") && (cmm.getEtat() == 1) && (date.compareTo(cmm.getDateLivraison()) > 0)){
            int s = (int) (date.getTime() -  cmm.getDateLivraison().getTime());
            int h = s/3600;
            int j = h/24;
            mntfac = (((cmm.getMontant() * 126)/100)*j)/100;
            cmm.setEtat(2);

        }*/
        fac.setClient(nm.getClient());
        fac.setCommande(nm);
        fac.setMontant(Integer.parseInt(mntFacTfd.getText().trim()));
        fac.setDateFacture(date);


        try {
            Fabrique.getiFacture().ajouterFacture(fac);
            Utils.showMessage("Gestion des factures", "Ajout de la facture", "Ajout Reussii!!!!");
            numFacTfd.setText(autoINcremntNumFac());
            cniCliFacTfd.setText("");
            nomCliFacTfd.setText("");
            typeCliFacTfd.setText("");
            numCmdFacTfd.setText("");
            dateLivraisonFacTfd.setText("");
            numCmdFacTfd.setText("");
        } catch (Exception e) {
            e.printStackTrace();
            Utils.showMessage("Gestion des factures", "Ajout de la facture", "Erreu lors de l'ajout:  "+e.getMessage());
        }
    }

    @FXML
    void enregistrerHandler(ActionEvent event) throws DocumentException {
        if(numCmdTfd.getText().trim().isEmpty() || cniCliTfd.getText().trim().isEmpty() ||
                nomCliTfd.getText().trim().isEmpty() || typeCliTfd.getText().trim().isEmpty() ||
                dateLivraisonCmdTfd.getText().trim().isEmpty()) {
            Utils.showMessage("Gestion des commandes", "Ajout des produits dans la commandes", "Tous les champs de saisie doivent etre remplis!!!");
        }

        System.out.println(details);
        Commande command = new Commande();
        DateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        command.setDateCommande(date);
        command.setNumero(numCmdTfd.getText().trim());
        command.setMontant(Integer.parseInt(mntCmdTfd.getText().trim()));

        Date date1 = new Date();
        try {
            date1 = new SimpleDateFormat("dd/MM/yyyy").parse(dateLivraisonCmdTfd.getText().trim());
            command.setDateLivraison(date1);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Client clie = new Client();
        try {
            clie = Fabrique.getiClient().findByCni(cniCliTfd.getText().trim());
        } catch (Exception e) {
            e.printStackTrace();
        }
        command.setClient(clie);
        command.setUtilisateur(SessionUtilisateur.getUser());
        try {
            Fabrique.getiCommande().ajouterCommande(command);
            Utils.showMessage("Gestion des commandes", "Ajout de la commande", "Ajout Reussii!!!!");
        } catch (Exception e) {
            e.printStackTrace();
            Utils.showMessage("Gestion des commandes", "Ajout de la commande", "Erreu lors de l'ajout:  "+e.getMessage());
        }
        Commande cmmd = new Commande();
        try {
            cmmd = Fabrique.getiCommande().findByNum(numCmdTfd.getText().trim());
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (DetailsCommande de : details){
            de.setCommande(cmmd);
            try {
                Fabrique.getiDetailsCommande().ajouterDetail(de);
                Sortie sortie = new Sortie();
                sortie.setDateSortie(de.getCommande().getDateCommande());
                sortie.setQteSortie(de.getQteCommande());
                sortie.setProduit(de.getProduit());
                Fabrique.getiSortie().ajouterSOrtie(sortie);

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        Document doc = new Document();
        try {
            PdfWriter.getInstance(doc, new FileOutputStream("Bon de Commande de "+clie.getNom()+" "+clie.getPrenom()+".pdf"));
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        doc.open();

        Font bold = new Font(Font.FontFamily.TIMES_ROMAN, 38, Font.BOLD);
        doc.add(new Paragraph("Bon de commande"));
        doc.add(new Paragraph("Numero "+numCmdTfd.getText().trim()));
        Date dat = new Date();
        doc.add( new Paragraph(dat.toString()));
        doc.add(new Paragraph("===================================================="));
        Font regular = new Font(Font.FontFamily.COURIER, 38, Font.BOLD);
        Paragraph paragraph = new Paragraph("Liste des produits commandés");
        doc.add(new Paragraph(" "));
        PdfPTable table = new PdfPTable(4);
        table.setWidthPercentage(100);
        PdfPCell cell;
        cell = new PdfPCell(new Phrase("Produits",FontFactory.getFont("forte",12)));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBorderColor(BaseColor.BLUE);
        cell.setBackgroundColor(BaseColor.GRAY);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Quantité commandée",FontFactory.getFont("forte",12)));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBorderColor(BaseColor.BLUE);
        cell.setBackgroundColor(BaseColor.GRAY);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Prix unitaire",FontFactory.getFont("forte",12)));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBorderColor(BaseColor.BLUE);
        cell.setBackgroundColor(BaseColor.GRAY);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Total",FontFactory.getFont("forte",12)));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBorderColor(BaseColor.BLUE);
        cell.setBackgroundColor(BaseColor.GRAY);
        table.addCell(cell);

        for (DetailsCommande de: details){
            cell = new PdfPCell(new Phrase(de.getProduit().getDescription(),FontFactory.getFont("forte",12)));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBorderColor(BaseColor.BLUE);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase(String.valueOf(de.getQteCommande()),FontFactory.getFont("forte",12)));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBorderColor(BaseColor.BLUE);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase(String.valueOf(de.getProduit().getPrixU()),FontFactory.getFont("forte",12)));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBorderColor(BaseColor.BLUE);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase(String.valueOf(de.getProduit().getPrixU()*de.getQteCommande()),FontFactory.getFont("forte",12)));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBorderColor(BaseColor.BLUE);
            table.addCell(cell);
        }
        paragraph.add(table);
        try {
            doc.add(paragraph);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        doc.add(new Paragraph(" "));
        doc.add(new Paragraph("Montant total a payer: "+mntCmdTfd.getText().trim()));
        doc.add(new Paragraph(" "));
        doc.add(new Paragraph("Date de livraison de la commande: "+dateLivraisonCmdTfd.getText().trim()));
        doc.close();
        System.out.println("OK");


    }

    @FXML
    void eventBloq(MouseEvent event) {
        Utilisateur user = table_users.getSelectionModel().getSelectedItem();
        try {
            Fabrique.getiUtilisateur().deleteUtilisateur(user);
            Utils.showMessage("Gestion des utilisateur","Blocage d'un utilisateur","utilisateur bloqué");
            updateTableUser();
        } catch (Exception e) {
            e.printStackTrace();
            Utils.showMessage("Gestion des stocks","Blocage d'un utilisateur","Erreur lors de la suppression!!!!!! :"+e.getMessage());
        }
    }

    @FXML
    void gesClientsHandler(ActionEvent event) {
        clientPane.toFront();
        try {
            List<TypeClient> types = Fabrique.getiTypeClient().allTypeClients();
            typeClientCbx.setItems(FXCollections.observableArrayList(types));

        } catch (Exception e) {
            e.printStackTrace();
        }
        updateClientTable();

    }

    @FXML
    void gesCmdHandler(ActionEvent event) {
        gestCmdPane.toFront();
        listeCommandes();
    }

    @FXML
    void gesPaymntHandler(ActionEvent event) {
        facturePane.toFront();
        numFacTfd.setText(autoINcremntNumFac());
mntFacTfd.setText(String.valueOf(mntfac));
        rechercherCmd();
    }

    @FXML
    void gesUserHandler(ActionEvent event) {
        userPane.toFront();
        updateTableUser();
    }

    @FXML
    void gesproHandler(ActionEvent event) {
        produitPane.toFront();
        updateTablePro();
    }

    @FXML
    void getSelected(MouseEvent event) {

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

    @FXML
    void getSelectedCmd(MouseEvent event) {

    }

    @FXML
    void homeHandler(ActionEvent event) {
        accueilPane.toFront();
        lineChart.getData().clear();
        LineChartGraph();

    }

    @FXML
    void impressionHandler(ActionEvent event) throws DocumentException {
        Client clie = new Client();
        try {
            clie = Fabrique.getiClient().findByCni(cniCliTfd.getText().trim());
        } catch (Exception e) {
            e.printStackTrace();
        }
        Document doc = new Document();
        try {
            PdfWriter.getInstance(doc, new FileOutputStream("Bon de Commande Interne de "+clie.getNom()+" "+clie.getPrenom()+".pdf"));
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        doc.open();

        Font bold = new Font(Font.FontFamily.TIMES_ROMAN, 38, Font.BOLD);
        doc.add(new Paragraph("Bon de commande"));
        Date datt = new Date();
        DateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        try {
            doc.add( new Paragraph(format.parse(datt.toString()).toString()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        doc.add(new Paragraph("===================================================="));
        Font regular = new Font(Font.FontFamily.COURIER, 38, Font.BOLD);
        Paragraph paragraph = new Paragraph("Liste des produits commandés");
        doc.add(new Paragraph(" "));
        PdfPTable table = new PdfPTable(4);
        table.setWidthPercentage(100);
        PdfPCell cell;
        cell = new PdfPCell(new Phrase("Produits",FontFactory.getFont("forte",12)));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBorderColor(BaseColor.BLUE);
        cell.setBackgroundColor(BaseColor.GRAY);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Quantité commandée",FontFactory.getFont("forte",12)));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBorderColor(BaseColor.BLUE);
        cell.setBackgroundColor(BaseColor.GRAY);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Prix unitaire",FontFactory.getFont("forte",12)));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBorderColor(BaseColor.BLUE);
        cell.setBackgroundColor(BaseColor.GRAY);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Total",FontFactory.getFont("forte",12)));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBorderColor(BaseColor.BLUE);
        cell.setBackgroundColor(BaseColor.GRAY);
        table.addCell(cell);

        for (DetailsCommande de: details){
            cell = new PdfPCell(new Phrase(de.getProduit().getDescription(),FontFactory.getFont("forte",12)));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBorderColor(BaseColor.BLUE);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase(String.valueOf(de.getQteCommande()),FontFactory.getFont("forte",12)));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBorderColor(BaseColor.BLUE);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase(String.valueOf(de.getProduit().getPrixU()),FontFactory.getFont("forte",12)));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBorderColor(BaseColor.BLUE);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase(String.valueOf(de.getProduit().getPrixU()*de.getQteCommande()),FontFactory.getFont("forte",12)));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBorderColor(BaseColor.BLUE);
            table.addCell(cell);
        }
        paragraph.add(table);
        try {
            doc.add(paragraph);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        doc.add(new Paragraph(" "));
        doc.add(new Paragraph("Montant total a payer: "+mntCmdTfd.getText().trim()));
        doc.close();
        System.out.println("OK");

    }

    @FXML
    void invHandler(ActionEvent event) {
        inventairePane.toFront();
        remplirTabIEntree();
        remplirTabISortie();
    }

    @FXML
    void modifCliHandler(ActionEvent event) {
        Client cli = ClientTab.getSelectionModel().getSelectedItem();
        cli.setNom(nomClientTfd.getText().trim());
        cli.setPrenom(pnomClientTfd.getText().trim());
        cli.setCni(cniClientTfd.getText().trim());
        cli.setAdresse(adrClientTfd.getText().trim());
        Date date = new Date();
        try {
            date = new SimpleDateFormat("dd/MM/yyyy").parse(dateClientTfd.getText().trim());
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
            nomClientTfd.setText("");
            pnomClientTfd.setText("");
            adrClientTfd.setText("");
            cniClientTfd.setText("");
            dateClientTfd.setText("");
            updateClientTable();
        } catch (Exception e) {
            e.printStackTrace();
            Utils.showMessage("Gestion des commandes", "Modification des clients", "Erreu lors de la modification du client:  "+e.getMessage());
        }
    }

    @FXML
    void modifHandler(ActionEvent event) {
        Produit pro = DtTbPro.getSelectionModel().getSelectedItem();
        pro.setDescription(descProTfd.getText().trim());
        pro.setPrixMin(Integer.parseInt(pmProTfd.getText().trim()));
        pro.setPrixU(Integer.parseInt(puProTfd.getText().trim()));
        pro.setQteStock(Integer.parseInt(qtestckProTfd.getText().trim()));

        try {
            Fabrique.getiProduit().modifProduit(pro);
            Utils.showMessage("Gestion des stocks", "Mise a jour des produits", "La produit a ete modifie avec succes!!");
            updateTablePro();
            descProTfd.setText("");
            pmProTfd.setText("");
            puProTfd.setText("");
            qtestckProTfd.setText("");
        } catch (Exception e) {
            e.printStackTrace();
            Utils.showMessage("Gestion des stocks", "Mise a jour des produits", "Erreu lors de l'ajout du produit:  "+e.getMessage());
        }
    }

    @FXML
    void retourApproHandler(ActionEvent event) {
        produitPane.toFront();
        updateTablePro();
    }

    @FXML
    void selectCmdHandler(ActionEvent event) {
        DetailsCommande tb = CommandeTab.getSelectionModel().getSelectedItem();
        desiCol.setText(tb.getProduit().getDescription());
        qteCmdeeCol.setText(String.valueOf(tb.getQteCommande()));
        puProCol.setText(String.valueOf(tb.getProduit().getPrixU()));
        totalCmdCol.setText(String.valueOf(tb.getQteCommande()*tb.getProduit().getPrixU()));

    }

    @FXML
    void selectCliHandler(ActionEvent event) {
        Client cli = ClientTab.getSelectionModel().getSelectedItem();
        nomClientTfd.setText(cli.getNom());
        pnomClientTfd.setText(cli.getPrenom());
        cniClientTfd.setText(cli.getCni());
        adrClientTfd.setText(cli.getAdresse());
        dateClientTfd.setText(String.valueOf(cli.getDatenaissance()));

        try {
            List<TypeClient> typesCli = Fabrique.getiTypeClient().allTypeClients();
            typeClientCbx.setItems(FXCollections.observableArrayList(typesCli));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void selectHandler(ActionEvent event) {
        Produit pro = DtTbPro.getSelectionModel().getSelectedItem();
        descProTfd.setText(pro.getDescription());
        pmProTfd.setText(Integer.toString(pro.getPrixMin()));
        puProTfd.setText(Integer.toString(pro.getPrixU()));
        qtestckProTfd.setText(Integer.toString(pro.getQteStock()));

    }

    @FXML
    void supHandler(ActionEvent event) {
        Produit proo = DtTbPro.getSelectionModel().getSelectedItem();
        try {
            Fabrique.getiProduit().deleteProduit(proo);
            Utils.showMessage("Gestion des stocks","Suppression des produits","produit supprimé");
            updateTablePro();
            descProTfd.setText("");
            pmProTfd.setText("");
            puProTfd.setText("");
            qtestckProTfd.setText("");
        } catch (Exception e) {
            e.printStackTrace();
            Utils.showMessage("Gestion des stocks","Suppression des produits","Erreur lors de la suppression!!!!!! :"+e.getMessage());
        }

    }

    @FXML
    void supprimerHandler(ActionEvent event) {
        ObservableList<DetailsCommande> allelemnts,elemnt;
        allelemnts = CommandeTab.getItems();

        elemnt = CommandeTab.getSelectionModel().getSelectedItems();
        elemnt.forEach(allelemnts::remove);
        /*DetailsCommande d = (DetailsCommande) CommandeTab.getSelectionModel().getSelectedItems();
        try {
            Fabrique.getiDetailsCommande().deleteDetailsCommande(d);
            updateTableD();
        } catch (Exception e) {
            e.printStackTrace();
        }*/
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        userLbl.setText(SessionUtilisateur.nom());
        File imgFile1 = new File("C:/Users/CELIA/IdeaProjects/DevoirClient/src/main/java/devoirs2/view/icons/welcom-organisation.png");
        Image img1 = new Image(imgFile1.toURI().toString());
        logoImg.setImage(img1);
        LineChartGraph();

    }
    void remplirTabIEntree(){
        tableI_entrees.getItems().clear();

        dateIEntreeCol.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getDateEntree()));
        qteIEntreeCol.setCellValueFactory(celldata -> new ReadOnlyObjectWrapper<>(celldata.getValue().getQteEntree()));
        prodEntreeCol.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getProduit().getDescription()));
        try {
            List<Entree> enntreI = Fabrique.getiEntree().allEntrees();
            tableI_entrees.setItems(FXCollections.observableArrayList(enntreI));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    void remplirTabISortie(){
        tableI_sorties.getItems().clear();
        dateSortieCol.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getDateSortie()));
        qteSortieCol.setCellValueFactory(celldata -> new ReadOnlyObjectWrapper<>(celldata.getValue().getQteSortie()));
        prodSortieCol.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getProduit().getDescription()));
        try {
            List<Sortie> sorties = Fabrique.getiSortie().allSorties();
            tableI_sorties.setItems(FXCollections.observableArrayList(sorties));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void listeCommandes(){
        table_commandes.getItems().clear();

        numerocmdCol.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getNumero()));
        montantCmdCol.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getMontant()));
        dateCmdCol.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getDateCommande()));
        nomCliCmdCol.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getClient().getNom()+" "+cellData.getValue().getClient().getPrenom()));
        try {
            List<Commande> commandes = Fabrique.getiCommande().allCommandesByEtat();
            table_commandes.setItems(FXCollections.observableArrayList(commandes));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateTablePro(){
        DtTbPro.getItems().clear();
        descProCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        prixMinCol.setCellValueFactory(new PropertyValueFactory<>("prixMin"));
        pUProCol.setCellValueFactory(new PropertyValueFactory<>("prixU"));
        qteSProCol.setCellValueFactory(new PropertyValueFactory<>("qteStock"));
        try {
            DtTbPro.setItems(FXCollections.observableArrayList(Fabrique.getiProduit().allProduitsByEtat()));
            /*List<Produit> produits = Fabrique.getiProduit().allProduits();
            for (Produit p : produits){
                DtTbPro.getItems().add(p);
            }*/
        }catch (Exception e){
            Utils.showMessage("Gestion des stocks","Gestion des produit","Erreur!!!!!! :"+e.getMessage());
        }
    }
    void updateTabAppro(){
        DtTbAppro.getItems().clear();
        dateApprCol.setCellValueFactory(new PropertyValueFactory<>("dateEntree"));
        qteApproCol.setCellValueFactory(new PropertyValueFactory<>("qteEntree"));
        prodApproCol.setCellValueFactory(new PropertyValueFactory<>("produit"));

        try {
            //DtTbAppro.setItems(FXCollections.observableArrayList(Fabrique.getiEntree().allEntrees()));
            DtTbAppro.setItems(FXCollections.observableArrayList(Fabrique.getiEntree().allEntreesByDesc(descApproTfd.getText().trim())));
            /*List<Entree> entreees = Fabrique.getiEntree().allEntrees();
            for (Entree e : entreees){
                DtTbAppro.getItems().add(e);
            }*/
        }catch (Exception e){
            Utils.showMessage("Gestion des stocks","Gestion des Entrees","Erreur!!!!!! :"+e.getMessage());
        }
    }

    void updateClientTable(){
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

    void rechercherCli(){

        cniCliTfd.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(!newValue){
                    if(!cniCliTfd.getText().trim().equals("")){
                        try {
                            cli = Fabrique.getiClient().findByCni(cniCliTfd.getText().trim());
                            if(cli != null){
                                nomCliTfd.setText(cli.getNom()+" "+cli.getPrenom());
                                typeCliTfd.setText(cli.getTypeClient().toString());
                            }else {
                                Utils.showMessage("Gestion des commandes", "Ajout des details",
                                        "Erreur ce client n'existe pas!! vous devriez aller l'enregistrer d'abord!!!");
                            }
                        }catch (Exception ex){

                        }
                    }
                }
            }
        });

    }

    void rechercherProd(){

        pdtsCbx.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(!newValue){
                    if(!pdtsCbx.getValue().getDescription().equals("")){
                        try {
                            pro = Fabrique.getiProduit().findByDesc(pdtsCbx.getValue().getDescription());
                            if(pro != null){
                                puProdTfd.setText(String.valueOf(pro.getPrixU()));
                                qtsProTfd.setText(String.valueOf(pro.getQteStock()));
                            }
                        }catch (Exception ex){

                        }
                    }
                }
            }
        });

    }

    void rechercherCmd(){

        numCmdFacTfd.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(!newValue){
                    if(!numCmdFacTfd.getText().trim().equals("")){
                        try {
                            nm = Fabrique.getiCommande().findByNum(numCmdFacTfd.getText().trim());
                            if(nm != null){
                                if(nm.getEtat() != 2 ) {
                                    dateLivraisonFacTfd.setText(String.valueOf(nm.getDateLivraison()));
                                    mntCmdFacTfd.setText(String.valueOf(nm.getMontant()));
                                    cniCliFacTfd.setText(nm.getClient().getCni());
                                    nomCliFacTfd.setText(nm.getClient().getNom() + " " + nm.getClient().getPrenom());
                                    typeCliFacTfd.setText(nm.getClient().getTypeClient().getLibelle());
                                    Date today = new Date();
                                    double tva  = nm.getMontant()*0.18;
                                    double mtn = tva + nm.getMontant();
                                    if  (nm.getClient().getTypeClient().getLibelle().equals("Particulier") ){
                                        mntFacTfd.setText(String.valueOf(mtn));
                                        nm.setEtat(2);
                                    }else if(nm.getClient().getTypeClient().getLibelle().equals("Entreprise") && (nm.getEtat() == 0) && (today.compareTo(nm.getDateLivraison()) <= 0)){
                                       mntfac =   mtn * 0.3;
                                        nm.setEtat(1);
                                        mntFacTfd.setText(String.valueOf(mntfac));
                                    }if(nm.getClient().getTypeClient().getLibelle().equals("Entreprise") && (nm.getEtat() == 0) && (today.compareTo(nm.getDateLivraison()) > 0)){
                                        int s = (int) (today.getTime() -  nm.getDateLivraison().getTime());
                                        int h = s/3600;
                                        int j = h/24;
                                        double reduc = (double) ((nm.getMontant()*j)/100);
                                        mntfac =  (mtn * 0.3) - reduc;
                                        nm.setEtat(1);
                                        mntFacTfd.setText(String.valueOf(mntfac));
                                    }if(nm.getClient().getTypeClient().getLibelle().equals("Entreprise") && (nm.getEtat() == 1) && (today.compareTo(nm.getDateLivraison()) <= 0)){
                                        mntfac = mtn * 0.7;
                                        nm.setEtat(2);
                                        mntFacTfd.setText(String.valueOf(mntfac));
                                    }if(nm.getClient().getTypeClient().getLibelle().equals("Entreprise") && (nm.getEtat() == 1) && (today.compareTo(nm.getDateLivraison()) > 0)){
                                        int s = (int) (today.getTime() -  nm.getDateLivraison().getTime());
                                        int h = s/3600;
                                        int j = h/24;
                                        double reduc = (double) ((nm.getMontant()*j)/100);
                                        mntfac = (mtn *0.3) - reduc;
                                        nm.setEtat(2);
                                        mntFacTfd.setText(String.valueOf(mntfac));
                                    }
                                }if (nm.getEtat() == 2){
                                    Utils.showMessage("Gestion des factures", "Ajout des elements",
                                            "Erreur cette commande est deja payee!!!");

                                }
                            }else {
                                Utils.showMessage("Gestion des factures", "Ajout des elements",
                                        "Erreur cette commande n'est pas repertorié!!!");
                            }
                        }catch (Exception ex){

                        }
                    }
                }
            }
        });

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
            /*for (Utilisateur u : users){
                table_users.getItems().add(u);
            }*/
        }catch (Exception e){
            Utils.showMessage("Gestion des Utilisateurs","Liste","Erreur!!!!!! :"+e.getMessage());
        }
    }


    void updateTableD(){

        CommandeTab.getItems().clear();

        desiCol.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getProduit().getDescription()));
        qteCmdeeCol.setCellValueFactory(celldata -> new ReadOnlyObjectWrapper<>(celldata.getValue().getQteCommande()));
        puProCol.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getProduit().getPrixU()));
        totalCmdCol.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getProduit().getPrixU()*cellData.getValue().getQteCommande()));

        CommandeTab.setItems(FXCollections.observableArrayList(details));
    }

    String autoINcremntNum(){
        int idd = 0;
        try {
            idd = Fabrique.getiCommande().generNum();
            if(idd < 0 ){
                idd = 1;
            }else{
                idd++;

            }
            String numero = "CMD_"+idd;
            return numero;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    String autoINcremntNumFac() {
        int iddd = 0;
        try {
            iddd = Fabrique.getiFacture().maxiNum();
            if (iddd < 0) {
                iddd = 1;
            } else {
                iddd++;

            }
            String numeroo = "FAC_" + iddd;
            return numeroo;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
void LineChartGraph(){
    XYChart.Series<Date,Number> series = new XYChart.Series<Date,Number>();
    List<Sortie> sortieChart = new ArrayList<>();
    try {
        sortieChart = Fabrique.getiSortie().allSorties();
    } catch (Exception e) {
        e.printStackTrace();
    }
    for(Sortie so : sortieChart){
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String strDate = formatter.format(so.getDateSortie());
        series.getData().add(new XYChart.Data(strDate , so.getQteSortie()));
    }
    XYChart.Series<Date,Number> seriess = new XYChart.Series<Date,Number>();
    List<Entree> entreeChart = new ArrayList<>();
    try {
        entreeChart = Fabrique.getiEntree().allEntrees();
    } catch (Exception e) {
        e.printStackTrace();
    }
    for(Entree en : entreeChart){
        SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy");
        String strDat = formater.format(en.getDateEntree());
        series.getData().add(new XYChart.Data(strDat , en.getQteEntree()));
    }

    lineChart.getData().addAll(series,seriess);

}

}
