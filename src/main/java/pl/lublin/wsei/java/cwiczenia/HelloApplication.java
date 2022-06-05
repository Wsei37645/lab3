package pl.lublin.wsei.java.cwiczenia;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.application.HostServices;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("gusInfoGraphic.fxml"));
        Parent root = loader.load();
        HelloController controller = loader.getController();
        controller.setHostServices(this.getHostServices());
        controller.setStage(primaryStage);

        Scene scene = new Scene(root, 800, 700);
        primaryStage.setTitle("Czytamy pliczki xml!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}