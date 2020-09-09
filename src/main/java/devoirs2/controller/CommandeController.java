package devoirs2.controller;

import com.itextpdf.text.*;
import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import devoirs2.java.model.*;
import devoirs2.utils.Fabrique;
import devoirs2.utils.Utils;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.IntegerStringConverter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

public class CommandeController implements Initializable {
    private static Client cli = null;
    private static Produit pro = null;
  //private static  Commande cmd = new Commande();
    List<DetailsCommande> details ;
    int mnt = 0;
    @FXML
    private TextField numCmdTfd;

    @FXML
    private TextField qteCmdeeCol;

    @FXML
    private TextField nomCliTfd;

    @FXML
    private TextField cniCliTfd;

    @FXML
    private TextField typeCliTfd;

    @FXML
    private ChoiceBox<Produit> pdtsCbx;

    @FXML
    private TextField puProdTfd;

    @FXML
    private TextField qtsProTfd;

    @FXML
    private TextField mntCmdTfd;

    @FXML
    private Button ajoutBtn;

    @FXML
    private Button ImprimBtn;

    @FXML
    private TableView<DetailsCommande> CommandeTab;

    @FXML
    private TableColumn<DetailsCommande, String> desiCol;

    @FXML
    private TableColumn<DetailsCommande, Integer> qteCol;

    @FXML
    private TableColumn<DetailsCommande, Integer> puProCol;

    @FXML
    private TableColumn<DetailsCommande, Integer> totalCmdCol;

    @FXML
    private Button selectBtn;

    @FXML
    private Button modifBtn;

    @FXML
    private Button suppBtn;

    @FXML
    private Button EnregBtn;


    @FXML
    void ajoutHandler(ActionEvent event) {
        if(qteCmdeeCol.getText().trim().isEmpty() || pdtsCbx.getItems().isEmpty() ||
                puProdTfd.getText().trim().isEmpty() || qtsProTfd.getText().trim().isEmpty()) {
            Utils.showMessage("Gestion des commandes", "Ajout des produits dans la commandes", "Tous les champs de saisie doivent etre remplis!!!");
        }
        int qteC = Integer.parseInt(qteCmdeeCol.getText().trim());
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
        detail.setProduit(pro);
        details.add(detail);
        if(Integer.parseInt(qtsProTfd.getText().trim()) < Integer.parseInt(qteCmdeeCol.getText().trim())) {
            Utils.showMessage("Gestion des commandes", "Ajout des details",
                    "Erreur ce produit est en rupture de stock!! vous devriez l'approvisionner!!!");
        }else {
            try {
                Utils.showMessage("Gestion des commandes", "Ajout des details", "Ajout Reussii!!!!");
                qteCmdeeCol.setText("");
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
    void editQte(TableColumn.CellEditEvent edditedCell){
        DetailsCommande tbSelected = CommandeTab.getSelectionModel().getSelectedItem();
        tbSelected.setQteCommande(Integer.parseInt(edditedCell.getNewValue().toString()));
    }

    @FXML
    void selectHandler(ActionEvent event) {
        DetailsCommande tb = CommandeTab.getSelectionModel().getSelectedItem();
        desiCol.setText(tb.getProduit().getDescription());
        qteCol.setText(String.valueOf(tb.getQteCommande()));
        puProCol.setText(String.valueOf(tb.getProduit().getPrixU()));
        totalCmdCol.setText(String.valueOf(tb.getQteCommande()*tb.getProduit().getPrixU()));
    }

    @FXML
    void supprimerHandler(ActionEvent event) {
        ObservableList<DetailsCommande> allelemnts,elemnt;
        allelemnts = CommandeTab.getItems();
        elemnt = CommandeTab.getSelectionModel().getSelectedItems();
        elemnt.forEach(allelemnts::remove);
    }

    @FXML
    void enregistrerHandler(ActionEvent event) throws DocumentException {
        if(numCmdTfd.getText().trim().isEmpty() || cniCliTfd.getText().trim().isEmpty() ||
                nomCliTfd.getText().trim().isEmpty() || typeCliTfd.getText().trim().isEmpty()) {
            Utils.showMessage("Gestion des commandes", "Ajout des produits dans la commandes", "Tous les champs de saisie doivent etre remplis!!!");
        }

        System.out.println(details);
        Commande command = new Commande();
        DateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        command.setDateCommande(date);
        command.setNumero(numCmdTfd.getText().trim());
        command.setMontant(Integer.parseInt(mntCmdTfd.getText().trim()));
        Client clie = new Client();
        try {
            clie = Fabrique.getiClient().findByCni(cniCliTfd.getText().trim());
        } catch (Exception e) {
            e.printStackTrace();
        }
        command.setClient(clie);
        //command.setUtilisateur(SessionUtilisateur.getUser());
        try {
            Fabrique.getiCommande().ajouterCommande(command);
            Utils.showMessage("Gestion des commandes", "Ajout de la commande", "Ajout Reussii!!!!");
        } catch (Exception e) {
            e.printStackTrace();
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
            PdfWriter.getInstance(doc, new FileOutputStream("Bon de Commande.pdf"));
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
        doc.close();
        System.out.println("OK");

    }

    @FXML
    void impressionHandler(ActionEvent event) throws DocumentException {
        Document doc = new Document();
        try {
            PdfWriter.getInstance(doc, new FileOutputStream("Bon de Commande.pdf"));
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        details = new ArrayList<>();
        numCmdTfd.setText(autoINcremntNum());
        try {
            List<Produit> pro = Fabrique.getiProduit().allProduits();
            pdtsCbx.setItems(FXCollections.observableArrayList(pro));

        } catch (Exception e) {
            e.printStackTrace();
        }

        rechercherCli();
        rechercherProd();

        CommandeTab.setEditable(true);
        qteCol.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));

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

    void updateTableD(){

        CommandeTab.getItems().clear();

        desiCol.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getProduit().getDescription()));
        qteCol.setCellValueFactory(celldata -> new ReadOnlyObjectWrapper<>(celldata.getValue().getQteCommande()));
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

}