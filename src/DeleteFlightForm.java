import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.geometry.Insets;

public class DeleteFlightForm extends Stage {

    private AirlineCoordinator coordinator;

    public DeleteFlightForm(AirlineCoordinator coordinator) {
        this.coordinator = coordinator;
        setTitle("Delete Flight");

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
        Button deleteButton = new Button("Delete");
        deleteButton.setOnAction(e -> handleDeleteFlight(flightNumberField));

        grid.add(new Label("Flight Number:"), 0, 0);
        grid.add(flightNumberField, 1, 0);
        grid.add(deleteButton, 1, 1);

        return grid;
    }

    private void handleDeleteFlight(TextField flightNumberField) {
        String flightNumber = flightNumberField.getText();
        boolean deleted = coordinator.deleteFlight(flightNumber);
        String message = deleted ? "Flight deleted successfully." : "Error deleting flight. Flight may not exist or have registered passengers bookings.";
        Alert.AlertType alertType = deleted ? Alert.AlertType.INFORMATION : Alert.AlertType.ERROR;
        new Alert(alertType, message).showAndWait();
        if (deleted) {
            this.close();
        }
    }
}