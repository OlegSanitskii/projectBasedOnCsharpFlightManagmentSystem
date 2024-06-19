import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.geometry.Insets;

public class DeleteCustomerForm extends Stage {

    private AirlineCoordinator coordinator;

    public DeleteCustomerForm(AirlineCoordinator coordinator) {
        this.coordinator = coordinator;
        setTitle("Delete Customer");

        GridPane grid = createFormLayout();
        setScene(new Scene(grid));
    }

    private GridPane createFormLayout() {
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(10, 10, 10, 10));

        TextField customerIdField = new TextField();
        customerIdField.setPromptText("Customer ID");
        Button deleteButton = new Button("Delete");
        deleteButton.setOnAction(e -> handleDeleteCustomer(customerIdField));

        grid.add(new Label("Customer ID:"), 0, 0);
        grid.add(customerIdField, 1, 0);
        grid.add(deleteButton, 1, 1);

        return grid;
    }

    private void handleDeleteCustomer(TextField customerIdField) {
        try {
            int customerId = Integer.parseInt(customerIdField.getText());
            boolean deleted = coordinator.deleteCustomer(customerId);
            String message = deleted ? "Customer deleted successfully." : "Error deleting customer. Customer may not exist or has booked flights.";
            Alert.AlertType alertType = deleted ? Alert.AlertType.INFORMATION : Alert.AlertType.ERROR;
            new Alert(alertType, message).showAndWait();
            if (deleted) {
                this.close();
            }
        } catch (NumberFormatException ex) {
            new Alert(Alert.AlertType.ERROR, "Invalid ID format.").showAndWait();
        }
    }
}