package pl.lublin.wsei.java.cwiczenia;

import javafx.application.HostServices;
import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.beans.value.ObservableValueBase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class HelloController {
    public Label lbFile;

    public ListView lstInfografiki;

    public ImageView imgMiniaturka;
    public Button btnPrzejdzDoStrony;
    public Button btnPokazInfografike;
    public TextField txtAdresStrony;

    private Infografika selInfografika;

    ObservableList<String> tytuly = FXCollections.observableArrayList();
    GusInfoGraphicList igList;

    FileChooser fileChooser = new FileChooser();
    FileChooser.ExtensionFilter xmlFilter = new FileChooser.ExtensionFilter("Pliki XML (*.xml)","*.xml");

    private Stage stage;
    private HostServices hostServices;

    public void setStage(Stage stage){
        this.stage = stage;
    }

    public void setHostServices(HostServices hostServices){
        this.hostServices = hostServices;
    }

    @FXML
    public void initialize(){
        fileChooser.getExtensionFilters().add(xmlFilter);
        lstInfografiki.getSelectionModel().selectedIndexProperty().addListener(
                new ChangeListener<Number>(){
                    @Override
                    public void changed(ObservableValue<? extends Number> observableValue, Number old_val, Number new_val){
                        int index = new_val.intValue();
                        if (index != -1){
                            selInfografika = igList.infografiki.get(index);
                            txtAdresStrony.setText(igList.infografiki.get(index).adresStrony);
                            Image image = new Image(igList.infografiki.get(index).adresGrafiki);
                            imgMiniaturka.setImage(image);
                        }
                        else{
                            txtAdresStrony.setText("");
                            imgMiniaturka.setImage(null);
                            selInfografika=null;
                        }
                    }
                }
        );
    }

    public void btnOpenFileAction(ActionEvent actionEvent) {
        File file = fileChooser.showOpenDialog(null);
        if(file != null){
            igList = new GusInfoGraphicList(file.getAbsolutePath());
            lbFile.setText(file.getAbsolutePath());

            for (Infografika ig: igList.infografiki){
                tytuly.add(ig.tytul);
                lstInfografiki.setItems(tytuly);
            }
        }
        else{
            lbFile.setText("Proszę wczytać plik...");
        }
    }


    public void btnPrzejdzDoStrony(ActionEvent actionEvent) {
        if (selInfografika != null){
            hostServices.showDocument(selInfografika.adresStrony);
        }
    }

    public void btnPokaz(ActionEvent actionEvent) {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("imgViewer.fxml"));
            Parent root = loader.load();
            ImgViewer viewer = loader.getController();

            if(selInfografika != null){
                Image img = new Image(selInfografika.adresGrafiki);
                viewer.imgView.setFitWidth(img.getWidth());
                viewer.imgView.setFitHeight(img.getHeight());
                viewer.imgView.setImage(img);
            }

            Stage stage = new Stage();
            stage.setTitle("Podgląd infografiki");
            stage.setScene(new Scene(root, 900, 800));
            stage.show();

        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}