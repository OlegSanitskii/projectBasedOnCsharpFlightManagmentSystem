import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class TravelReservationSystem extends Application {

    private AirlineCoordinator coordinator = new AirlineCoordinator(100, 50, 200);

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("XYZ Airlines Booking System");

        // Main layout
        BorderPane root = new BorderPane();

        // Menu
        MenuBar menuBar = new MenuBar();
        Menu customerMenu = new Menu("Customer Management");
        Menu flightMenu = new Menu("Flight Management");
        Menu bookingMenu = new Menu("Booking Management");
        menuBar.getMenus().addAll(customerMenu, flightMenu, bookingMenu);



        // ------------------------------------------------Customer Management Menu Items-----------------------------------------------------------------------

        MenuItem addCustomer = new MenuItem("Add Customer");
        MenuItem viewCustomers = new MenuItem("View Customers");
        MenuItem deleteCustomer = new MenuItem("Delete Customer");

        addCustomer.setOnAction(event -> {
            AddCustomerForm addCustomerForm = new AddCustomerForm(coordinator);
            addCustomerForm.show();
        });

        viewCustomers.setOnAction(event -> {
            ViewCustomersForm viewCustomersForm = new ViewCustomersForm(coordinator);
            viewCustomersForm.show();
        });

        deleteCustomer.setOnAction(event -> {
            DeleteCustomerForm deleteCustomerForm = new DeleteCustomerForm(coordinator);
            deleteCustomerForm.show();
        });

        customerMenu.getItems().addAll(addCustomer, viewCustomers, deleteCustomer);




        // ----------------------------------------------Flight Management Menu Items-----------------------------------------------------------------------

        MenuItem addFlight = new MenuItem("Add Flight");
        MenuItem viewFlights = new MenuItem("View Flights");
        MenuItem deleteFlight = new MenuItem("Delete Flight");

        addFlight.setOnAction(event -> {
            AddFlightForm addFlightForm = new AddFlightForm(coordinator);
            addFlightForm.show();
        });

        viewFlights.setOnAction(event -> {
            ViewFlightsForm viewFlightsForm = new ViewFlightsForm(coordinator);
            viewFlightsForm.show();
        });

        deleteFlight.setOnAction(event -> {
            DeleteFlightForm deleteFlightForm = new DeleteFlightForm(coordinator);
            deleteFlightForm.show();
        });

        flightMenu.getItems().addAll(addFlight, viewFlights, deleteFlight);



        // ------------------------------------------------------------Booking Management Menu Items------------------------------------------------------------------

        MenuItem makeBooking = new MenuItem("Make Booking");
        MenuItem viewBookings = new MenuItem("View Bookings");

        makeBooking.setOnAction(event -> {
            MakeBookingForm makeBookingForm = new MakeBookingForm(coordinator);
            makeBookingForm.show();
        });

        viewBookings.setOnAction(event -> {
            ViewBookingsForm viewBookingsForm = new ViewBookingsForm(coordinator);
            viewBookingsForm.show();
        });

        bookingMenu.getItems().addAll(makeBooking, viewBookings);

        root.setTop(menuBar);

        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}