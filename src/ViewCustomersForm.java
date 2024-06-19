import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.beans.property.ReadOnlyObjectWrapper;

public class ViewCustomersForm extends Stage {

    private AirlineCoordinator coordinator;

    public ViewCustomersForm(AirlineCoordinator coordinator) {
        this.coordinator = coordinator;
        this.setTitle("View Customers");

        VBox vbox = new VBox();
        TableView<Customer> table = createCustomerTable();
        vbox.getChildren().add(table);

        this.setScene(new Scene(vbox));
        populateTable(table);
    }

    private TableView<Customer> createCustomerTable() {
        TableView<Customer> table = new TableView<>();
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        // Creating columns
        TableColumn<Customer, Number> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getId()));

        TableColumn<Customer, String> firstNameColumn = new TableColumn<>("First Name");
        firstNameColumn.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getFirstName()));

        TableColumn<Customer, String> lastNameColumn = new TableColumn<>("Last Name");
        lastNameColumn.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getLastName()));

        TableColumn<Customer, String> phoneColumn = new TableColumn<>("Phone");
        phoneColumn.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(Integer.toString(cellData.getValue().getAge())));

        // Add columns to table
        table.getColumns().addAll(idColumn, firstNameColumn, lastNameColumn, phoneColumn);

        return table;
    }

    private void populateTable(TableView<Customer> table) {
        ObservableList<Customer> customerList = FXCollections.observableArrayList();
        for (Customer customer : coordinator.getCustomers()) {
            if (customer != null) {
                customerList.add(customer);
            }
        }
        table.setItems(customerList);
    }
}
