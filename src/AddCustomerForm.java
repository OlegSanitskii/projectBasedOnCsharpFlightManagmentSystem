import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.geometry.Insets;

public class AddCustomerForm extends Stage {

    private AirlineCoordinator coordinator;

    public AddCustomerForm(AirlineCoordinator coordinator) {
        this.coordinator = coordinator;
        this.setTitle("Add Customer");

        GridPane grid = createFormLayout();
        this.setScene(new Scene(grid));
    }

    private GridPane createFormLayout() {
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(10, 10, 10, 10));

        TextField firstNameField = new TextField();
        firstNameField.setPromptText("First Name");
        TextField lastNameField = new TextField();
        lastNameField.setPromptText("Last Name");
        TextField ageField = new TextField();
        ageField.setPromptText("Age");


        grid.add(new Label("First Name:"), 0, 0);
        grid.add(firstNameField, 1, 0);
        grid.add(new Label("Last Name:"), 0, 1);
        grid.add(lastNameField, 1, 1);
        grid.add(new Label("Age:"), 0, 2);
        grid.add(ageField, 1, 2);

        Button addButton = new Button("Add");
        addButton.setOnAction(e -> handleAddCustomer(firstNameField, lastNameField, ageField));
        grid.add(addButton, 1, 3);

        return grid;
    }

    private void handleAddCustomer(TextField firstNameField, TextField lastNameField, TextField ageField) {
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String age = ageField.getText();
        boolean added = coordinator.addCustomer(firstName, lastName, Integer.parseInt(age));
        if (added) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Customer added successfully.");
            alert.showAndWait();
            this.close();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Error adding customer.");
            alert.showAndWait();
        }
    }
}
