import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.geometry.Insets;

public class AddFlightForm extends Stage {

    private AirlineCoordinator coordinator;

    public AddFlightForm(AirlineCoordinator coordinator) {
        this.coordinator = coordinator;
        setTitle("Add Flight");

        GridPane grid = createFormLayout();
        setScene(new Scene(grid));
    }

    private GridPane createFormLayout() {
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(10, 10, 10, 10));

        TextField flightNumberField = new TextField();
        flightNumberField.setPromptText("Flight Number");
        TextField originField = new TextField();
        originField.setPromptText("Origin");
        TextField destinationField = new TextField();
        destinationField.setPromptText("Destination");
        TextField capacityField = new TextField();
        capacityField.setPromptText("Capacity");
        TextField flightFareField = new TextField();
        flightFareField.setPromptText("FlightFare");


        grid.add(new Label("Flight Number:"), 0, 0);
        grid.add(flightNumberField, 1, 0);
        grid.add(new Label("Origin:"), 0, 1);
        grid.add(originField, 1, 1);
        grid.add(new Label("Destination:"), 0, 2);
        grid.add(destinationField, 1, 2);
        grid.add(new Label("Capacity:"), 0, 3);
        grid.add(capacityField, 1, 3);
        grid.add(new Label("FlightFare:"), 0, 4);
        grid.add(flightFareField, 1, 4);




        Button addButton = new Button("Add");
        addButton.setOnAction(e -> handleAddFlight(flightNumberField, originField, destinationField, capacityField, flightFareField));
        grid.add(addButton, 1, 5);

        return grid;
    }

    private void handleAddFlight(TextField flightNumberField, TextField originField, TextField destinationField, TextField capacityField, TextField flightFareField) {
        String flightNumber = flightNumberField.getText();
        String origin = originField.getText();
        String destination = destinationField.getText();
        String capacityStr = capacityField.getText();
        String flightFare = flightFareField.getText();


        if (flightNumber.isEmpty() || capacityStr.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Please fill in both the flight number and capacity.");
            alert.showAndWait();
            return;
        }


        if (coordinator.isFlightNumberExists(flightNumber)) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "A flight with this number already exists. Please use a different flight number.");
            alert.showAndWait();
            return;
        }

        try {
            int capacity = Integer.parseInt(capacityStr);
            boolean added = coordinator.addFlight(flightNumber, origin, destination, capacity, Double.parseDouble(flightFare));
            if (added) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Flight added successfully.");
                alert.showAndWait();
                this.close();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Error adding flight. Please check the details.");
                alert.showAndWait();
            }
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid format for capacity. Please enter a number.");
            alert.showAndWait();
        }
    }
}
