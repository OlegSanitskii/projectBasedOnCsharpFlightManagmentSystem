import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.geometry.Insets;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.image.WritableImage;
import javafx.scene.SnapshotParameters;
import javax.imageio.ImageIO;
import java.io.File;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;



public class PrintReceiptForm extends Stage {

    private Booking selectedBooking;

    public PrintReceiptForm(Booking booking) {
        this.selectedBooking = booking;
        setTitle("Print Receipt");

        VBox vbox = new VBox(10);
        vbox.setPadding(new Insets(10));

        Label flightNumberLabel = new Label("Flight Number: " + booking.getAssociatedFlight().getFlightNumber());
        Label sourceLabel = new Label("Source: " + booking.getAssociatedFlight().getOrigin());
        Label destinationLabel = new Label("Destination: " + booking.getAssociatedFlight().getDestination());
        Label dateLabel = new Label("Date of Travel: " + booking.getBookingDate());
        Label passengerLabel = new Label("Passenger: " + booking.getBookedCustomer().getFirstName() + " " + booking.getBookedCustomer().getLastName());
        Label ageLabel = new Label("Age: " + booking.getBookedCustomer().getAge());
        Label totalFareLabel = new Label ("Total Fare: " + booking.totalFareCount());

        Button printButton = new Button("Print Receipt");
        printButton.setOnAction(event -> saveReceiptAsImage());

        vbox.getChildren().addAll(flightNumberLabel, sourceLabel, destinationLabel, dateLabel, passengerLabel, ageLabel, totalFareLabel, printButton);

        setScene(new Scene(vbox));
    }


    private void saveReceiptAsImage() {
        VBox receiptLayout = createReceiptLayout(); // Create your receipt layout
        Scene receiptScene = new Scene(receiptLayout);

        // Take a snapshot of the layout
        WritableImage image = receiptLayout.snapshot(new SnapshotParameters(), null);

        // Save the image
        File file = new File("Receipt_" + selectedBooking.getBookingNumber() + ".png");
        try {
            ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", file);

            // Show a confirmation dialog
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Receipt Saved");
            alert.setHeaderText(null);
            alert.setContentText("Receipt saved as image to " + file.getAbsolutePath());
            alert.showAndWait();
        } catch (Exception e) {
            // Show an error dialog
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Failed to Save Receipt");
            alert.setContentText("An error occurred while saving the receipt.");
            alert.showAndWait();
        }
    }

    private VBox createReceiptLayout() {
        VBox vbox = new VBox(10);
        vbox.getChildren().add(new Label("Flight Number: " + selectedBooking.getAssociatedFlight().getFlightNumber()));
        vbox.getChildren().add(new Label("Source: " + selectedBooking.getAssociatedFlight().getOrigin()));
        vbox.getChildren().add(new Label("Destination: " + selectedBooking.getAssociatedFlight().getDestination()));
        vbox.getChildren().add(new Label("Date of Travel: " + selectedBooking.getBookingDate()));
        vbox.getChildren().add(new Label("Passenger: " + selectedBooking.getBookedCustomer().getFirstName() + " " + selectedBooking.getBookedCustomer().getLastName()));
        vbox.getChildren().add(new Label("Age: " + selectedBooking.getBookedCustomer().getAge()));
        vbox.getChildren().add(new Label("Total Fare: " + selectedBooking.totalFareCount()));
        return vbox;
    }
}





/*
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.text.SimpleDateFormat;

public class PrintReceiptForm {

    public static void printTicket(Booking booking) {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Ticket Details");
        window.setMinWidth(350);
        window.setMinHeight(200);

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(10, 10, 10, 10));

        // Create and format the ticket details
        Label ticketDetails = new Label();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dateStr = booking.getBookingDate() != null ? booking.getBookingDate() : "N/A";

        String ticketText = String.format("Flight Number: %s\nSource: %s\nDestination: %s\nDate of Travel: %s\n" +
                        "Passenger Name: %s %s\nAge: %s\nTotal Fare: %s",
                booking.getAssociatedFlight().getFlightNumber(), booking.getAssociatedFlight().getOrigin(), booking.getAssociatedFlight().getDestination(), dateStr,
                booking.getBookedCustomer().getFirstName(), booking.getBookedCustomer().getLastName(), booking.getBookedCustomer().getAge(), booking);

        ticketDetails.setText(ticketText);

        layout.getChildren().add(ticketDetails);

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
    }
}
*/