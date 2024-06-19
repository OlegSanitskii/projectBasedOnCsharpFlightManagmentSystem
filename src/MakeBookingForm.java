import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.geometry.Insets;
import java.util.stream.Collectors;
import java.util.Arrays;
import javafx.collections.FXCollections;

public class MakeBookingForm extends Stage {

    private AirlineCoordinator coordinator;

    public MakeBookingForm(AirlineCoordinator coordinator) {
        this.coordinator = coordinator;
        setTitle("Make Booking");

        GridPane grid = createFormLayout();
        setScene(new Scene(grid));
    }

    private GridPane createFormLayout() {
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(10, 10, 10, 10));

        ComboBox<Customer> customerComboBox = new ComboBox<>();
        customerComboBox.setItems(FXCollections.observableArrayList(
                Arrays.stream(coordinator.getCustomers()).filter(c -> c != null).collect(Collectors.toList())
        ));
        ComboBox<Flight> flightComboBox = new ComboBox<>();
        flightComboBox.setItems(FXCollections.observableArrayList(
                Arrays.stream(coordinator.getFlights()).filter(f -> f != null).collect(Collectors.toList())
        ));
        DatePicker bookingDatePicker = new DatePicker();

        grid.add(new Label("Customer:"), 0, 0);
        grid.add(customerComboBox, 1, 0);
        grid.add(new Label("Flight:"), 0, 1);
        grid.add(flightComboBox, 1, 1);
        grid.add(new Label("Booking Date:"), 0, 2);
        grid.add(bookingDatePicker, 1, 2);

        Button bookButton = new Button("Book");
        bookButton.setOnAction(e -> handleMakeBooking(customerComboBox, flightComboBox, bookingDatePicker));
        grid.add(bookButton, 1, 3);

        return grid;
    }

    private void handleMakeBooking(ComboBox<Customer> customerComboBox, ComboBox<Flight> flightComboBox, DatePicker bookingDatePicker) {
        Customer customer = customerComboBox.getValue();
        Flight flight = flightComboBox.getValue();
        String bookingDate = bookingDatePicker.getValue().toString();  // Add error handling for null values

        if (customer != null && flight != null && bookingDate != null) {
            boolean booked = coordinator.addBooking(customer.getId(), flight.getFlightNumber(), bookingDate);
            String message = booked ? "Booking made successfully." : "Error making booking. Check details.";
            Alert.AlertType alertType = booked ? Alert.AlertType.INFORMATION : Alert.AlertType.ERROR;
            new Alert(alertType, message).showAndWait();
            if (booked) {
                this.close();
            }
        } else {
            new Alert(Alert.AlertType.ERROR, "Please complete all fields.").showAndWait();
        }
    }
}
