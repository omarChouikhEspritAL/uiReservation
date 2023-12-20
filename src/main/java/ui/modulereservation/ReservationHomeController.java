package ui.modulereservation;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import ui.BackendPackges.Entities.Offres;
import ui.BackendPackges.Entities.Reservation;
import ui.BackendPackges.Entities.ReservationT;
import ui.BackendPackges.Services.ServiceOffre;
import ui.BackendPackges.Services.ServiceReservation;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;

public class ReservationHomeController {
    @FXML
    private ListView<ReservationT> reservationsListView=new ListView<>();

    private final ServiceReservation serviceReservation = new ServiceReservation();
    private final ObservableList<ReservationT> reservationsList = FXCollections.observableArrayList();
    @FXML
    private DatePicker DateDeb;
    @FXML
    private DatePicker DateFin;
    @FXML
    private Slider slider;
    @FXML
    private Label sliderValueLabel;

    //detail label
    @FXML
    private Label idLabel;
    @FXML
    private Label dateDebutLabel;
    @FXML
    private Label dateFinLabel;
    @FXML
    private Label nbrPassLabel;
    @FXML
    private Label idOffreLabel;
    @FXML
    private Label paysLabel;
    @FXML
    private Label citeLabel;
    @FXML
    private Label lieuLabel;
    @FXML
    private Label dateOffreLabel;
    @FXML
    private Label prixLabel;

    @FXML
    private void addReservation() {
        try {
            // Check if DateDeb and DateFin are not empty
            if (DateDeb.getValue() == null || DateFin.getValue() == null) {
                showAlert("Missing Dates", "Please select Date Debut & Date Fin & offre.");
                return;
            }

            // Check if DateDeb is after DateFin
            if (DateDeb.getValue().isAfter(DateFin.getValue())) {
                showAlert("Invalid Dates", "Date Debut should be greater than Date Fin.");
                return;
            }

            Reservation reservation = new Reservation(Date.valueOf(DateDeb.getValue()),
                    Date.valueOf(DateFin.getValue()),
                    (int) slider.getValue(),
                    new Offres(1, "Turkey", "Istambul", "xxxx",
                            "10-09-2023", 1852.8f)
            );
            serviceReservation.add(reservation);
            System.out.println("Reservation added successfully.");
            showAlert("Success", "Reservation added successfully.");
            // Close window
            Stage stage = (Stage) DateDeb.getScene().getWindow();
            stage.close();
            displayReservations();
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error adding reservation.");
        }
    }

    @FXML
    private void updateReservation() {
        ReservationT selectedReservation = reservationsListView.getSelectionModel().getSelectedItem();
        if (selectedReservation == null) {
            showAlert("No Item Selected", "Please select a reservation to update.");
        } else {
        try {

            int reservationIdToUpdate = selectedReservation.getId();
            Reservation reservation = serviceReservation.get(reservationIdToUpdate);

            if (reservation != null) {
                // Update reservation details (in a real application, you would have a UI for this).
                reservation = new Reservation(Date.valueOf(DateDeb.getValue()),
                        Date.valueOf(DateFin.getValue()),
                        (int) slider.getValue(),
                        new Offres(1, "Turkey", "Istambul", "xxxx",
                                "10-09-2023", 1852.8f)
                );

                serviceReservation.update(reservation);
                System.out.println("Reservation updated successfully.");
            } else {
                System.out.println("Reservation not found.");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error updating reservation.");
        }
        }
    }

    @FXML
    private void displayReservations() {
        try {
            reservationsList.clear();
            ArrayList<Reservation> allReservations = serviceReservation.readAll();
            reservationsList.addAll(allReservations.stream().map(
                    reservation -> new ReservationT(reservation.getId(),reservation.getDateDebut(),
                            reservation.getDateFin(),reservation.getNombrePassages(),reservation.getOffre().getId_offre())
            ).collect(Collectors.toList()));

            // Update the ListView with the reservations
            reservationsListView.setItems(reservationsList);
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error displaying reservations.");
        }
    }
    @FXML
    private void detailWindow() {
        ReservationT selectedReservation = reservationsListView.getSelectionModel().getSelectedItem();

        if (selectedReservation == null) {
            showAlert("No Item Selected", "Please select a reservation to display.");
        } else {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("reservation-details.fxml"));
                Parent root = fxmlLoader.load();
                
//                idLabel.setText(String.valueOf(selectedReservation.getId()));
//                dateDebutLabel.setText(selectedReservation.getDateDebut().toString());
//                dateFinLabel.setText(selectedReservation.getDateFin().toString());
//                nbrPassLabel.setText(String.valueOf(selectedReservation.getNombrePassages()));
//                idOffreLabel.setText(String.valueOf(selectedReservation.getOffre()));
//
//                Offres o = new Offres(1, "Turkey", "Istambul", "xxxx",
//                        "10-09-2023", 1852.8f);
//                paysLabel.setText(o.getPays());
//                    citeLabel.setText(o.getCite());
//                    lieuLabel.setText(o.getLieu());
//                    dateOffreLabel.setText(o.getDate());
//                    prixLabel.setText(String.valueOf(o.getPrix()));
//                try {
//                    o = new ServiceOffre().get(selectedReservation.getOffre());
//                    paysLabel.setText(o.getPays());
//                    citeLabel.setText(o.getCite());
//                    lieuLabel.setText(o.getLieu());
//                    dateOffreLabel.setText(o.getDate());
//                    prixLabel.setText(String.valueOf(o.getPrix()));
//                } catch (SQLException e) {
//                    System.out.println(e);
//                    showAlert("Error", "Error retrieving Offres details.");
//                    return;
//                }

                Stage detailsStage = new Stage();
                detailsStage.setTitle("Reservation Details");
                detailsStage.initModality(Modality.WINDOW_MODAL);
                detailsStage.initOwner(reservationsListView.getScene().getWindow());
                detailsStage.setScene(new Scene(root));
                detailsStage.showAndWait();
            } catch (IOException e) {
                e.printStackTrace(); // Handle the exception appropriately
                showAlert("Error", "Error loading details window.");
            }
        }
    }


    @FXML
    private void addWindow() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(ReservationApplication.class.getResource("reservation-add.fxml"));

            // You can get the scene from the event source (the menu item that triggered the event)
            Scene currentScene = new Scene(fxmlLoader.load(), 550, 500);

            // Create a new stage for the new window
            Stage newStage = new Stage();
            newStage.setScene(currentScene);

            // Set the position of the new stage relative to the current scene
            newStage.setX(currentScene.getWindow().getX() + 50);
            newStage.setY(currentScene.getWindow().getY() + 50);

            // Set additional properties for the new stage
            newStage.setTitle("Add Reservation");
            //bloc main window
            newStage.initModality(Modality.WINDOW_MODAL);
            newStage.initOwner(reservationsListView.getScene().getWindow());

            newStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    private void editWindow() {
        showAlert("Edit Window", "Edit window clicked!");
    }

    @FXML
    private void deleteRes() {
        ReservationT selectedReservation = reservationsListView.getSelectionModel().getSelectedItem();

        if (selectedReservation == null) {
            showAlert("No Item Selected", "Please select a reservation to delete.");
        } else {
            // Show a confirmation alert before deleting
            Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
            confirmationAlert.setTitle("Confirmation");
            confirmationAlert.setHeaderText("Delete Reservation");
            confirmationAlert.setContentText("Are you sure you want to delete the selected reservation?");

            Optional<ButtonType> result = confirmationAlert.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.OK) {
                try {
                    // Assuming that Reservation class has a method getId() to get the ID of the reservation.
                    serviceReservation.delete(selectedReservation.getId());

                    // Placeholder message
                    System.out.println("Reservation deleted successfully");
                    displayReservations();
                } catch (SQLException ex) {
                    System.out.println("Error deleting reservation: " + ex.getMessage());
                }
            }
        }
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    @FXML
    private void initialize() {
        displayReservations();
        // add menu initi number passages
        if (slider != null) {
            // Set the initial text of the label to the value property of the slider
            sliderValueLabel.setText(String.format("%.0f", slider.getValue()));

            // Add a ChangeListener to the value property of the slider
            slider.valueProperty().addListener((observable, oldValue, newValue) ->
                    sliderValueLabel.setText(String.format("%.0f", newValue))
            );
        }
    }

    @FXML
    private void addCancel(ActionEvent event) {
        // Close the window
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.close();
    }


}
