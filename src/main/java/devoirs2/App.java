package devoirs2;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import javafx.application.Application;
import javafx.stage.Stage;
import devoirs2.utils.LoadView;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.stream.Stream;


public class App extends Application{


    @Override
    public void start(Stage primaryStage) throws Exception{
        LoadView.showView("Formulaire de connexion", "formUtilisateur.fxml",1);
    }

    public static void main(String[] args) {

        launch(args);

    }
}
