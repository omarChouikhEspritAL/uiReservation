package ui.modulereservation;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ReservationApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ReservationApplication.class.getResource("reservation-home.fxml"));
        Scene scene = new Scene(fxmlLoader.load(),  1200, 700);
        stage.setTitle("Reservation Home");
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args){launch();}
}
