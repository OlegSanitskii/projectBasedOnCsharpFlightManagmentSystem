import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.beans.property.ReadOnlyObjectWrapper;

public class ViewFlightsForm extends Stage {

    private AirlineCoordinator coordinator;

    public ViewFlightsForm(AirlineCoordinator coordinator) {
        this.coordinator = coordinator;
        setTitle("View Flights");

        VBox vbox = new VBox();
        TableView<Flight> table = createFlightTable();
        vbox.getChildren().add(table);

        setScene(new Scene(vbox));
        populateTable(table);
    }

    private TableView<Flight> createFlightTable() {
        TableView<Flight> table = new TableView<>();
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        // Define columns for flight details
        TableColumn<Flight, String> flightNumberColumn = new TableColumn<>("Flight Number");
        flightNumberColumn.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getFlightNumber()));

        TableColumn<Flight, String> originColumn = new TableColumn<>("Origin");
        originColumn.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getOrigin()));

        TableColumn<Flight, String> destinationColumn = new TableColumn<>("Destination");
        destinationColumn.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getDestination()));

        TableColumn<Flight, Number> capacityColumn = new TableColumn<>("Capacity");
        capacityColumn.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getCapacity()));

        table.getColumns().addAll(flightNumberColumn, originColumn, destinationColumn, capacityColumn);

        return table;
    }

    private void populateTable(TableView<Flight> table) {
        ObservableList<Flight> flightList = FXCollections.observableArrayList();
        for (Flight flight : coordinator.getFlights()) {
            if (flight != null) {
                flightList.add(flight);
            }
        }
        table.setItems(flightList);
    }
}