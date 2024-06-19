import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.beans.property.ReadOnlyObjectWrapper;

public class ViewBookingsForm extends Stage {

    private AirlineCoordinator coordinator;
    private TableView<Booking> table;

    public ViewBookingsForm(AirlineCoordinator coordinator) {
        this.coordinator = coordinator;
        setTitle("View Bookings");

        VBox vbox = new VBox(10); // Add some spacing between components
        table = createBookingTable();
        vbox.getChildren().add(table);

        Button printReceiptButton = new Button("Preview Receipt");
        printReceiptButton.setOnAction(event -> handlePrintReceipt());
        vbox.getChildren().add(printReceiptButton);

        setScene(new Scene(vbox));
        populateTable(table);
    }

    private TableView<Booking> createBookingTable() {
        TableView<Booking> table = new TableView<>();
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        // Define columns for booking details
        TableColumn<Booking, Number> bookingNumberColumn = new TableColumn<>("Booking Number");
        bookingNumberColumn.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getBookingNumber()));

        TableColumn<Booking, String> bookingDateColumn = new TableColumn<>("Booking Date");
        bookingDateColumn.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getBookingDate()));

        TableColumn<Booking, String> customerColumn = new TableColumn<>("Customer");
        customerColumn.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(
                cellData.getValue().getBookedCustomer().getFirstName() + " " + cellData.getValue().getBookedCustomer().getLastName()));

        TableColumn<Booking, String> flightColumn = new TableColumn<>("Flight");
        flightColumn.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getAssociatedFlight().getFlightNumber()));



        table.getColumns().addAll(bookingNumberColumn, bookingDateColumn, customerColumn, flightColumn);

        return table;
    }

    private void populateTable(TableView<Booking> table) {
        ObservableList<Booking> bookingList = FXCollections.observableArrayList();
        for (Booking booking : coordinator.getBookings()) {
            if (booking != null) {
                bookingList.add(booking);
            }
        }
        table.setItems(bookingList);
    }

    private void handlePrintReceipt() {
        Booking selectedBooking = table.getSelectionModel().getSelectedItem();
        if (selectedBooking != null) {
            PrintReceiptForm printReceiptForm = new PrintReceiptForm(selectedBooking);
            printReceiptForm.show();
        } else {
            new Alert(Alert.AlertType.WARNING, "Please select a booking to print the receipt.").showAndWait();
        }
    }
}