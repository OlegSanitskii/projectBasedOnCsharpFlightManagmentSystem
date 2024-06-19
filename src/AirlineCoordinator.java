import java.util.Arrays;

public class AirlineCoordinator {
    private Customer[] customers;
    private Flight[] flights;
    private Booking[] bookings;
    private int customerCount, flightCount, bookingCount;

    public AirlineCoordinator(int customerCapacity, int flightCapacity, int bookingCapacity) {
        customers = new Customer[customerCapacity];
        flights = new Flight[flightCapacity];
        bookings = new Booking[bookingCapacity];
        customerCount = flightCount = bookingCount = 0;
    }

    // Getters
    public Customer[] getCustomers() {
        return customers;
    }

    public Flight[] getFlights() {
        return flights;
    }

    public Booking[] getBookings() {
        return bookings;
    }

    // Add a customer
    public boolean addCustomer(String firstName, String lastName, int age) {
        if (customerCount >= customers.length) {
            return false; // Array is full
        }

        // Unique ID for customer
        int customerId = customerCount + 1;
        Customer newCustomer = new Customer(customerId, firstName, lastName, age);
        customers[customerCount++] = newCustomer;
        return true;
    }

    // Delete a customer by ID
    public boolean deleteCustomer(int customerId) {
        for (int i = 0; i < customers.length; i++) {
            if (customers[i] != null && customers[i].getId() == customerId) {
                // Check if customer has bookings
                if (customers[i].getNumberOfBookings() > 0) {
                    System.out.println("Cannot delete customer: Customer has existing bookings.");
                    return false; // Customer cannot be deleted due to existing bookings
                }

                customers[i] = null; // Remove the customer
                return true; // Customer deleted successfully
            }
        }
        return false; // Customer not found
    }

    // Add a flight
    public boolean addFlight(String flightNumber, String origin, String destination, int capacity, double flightFare) {
        if (flightCount >= flights.length) {
            return false; // Array is full
        }

        // Check if a flight with the same number already exists
        if (isFlightNumberExists(flightNumber)) {
            System.out.println("A flight with this number already exists.");
            return false; // Flight number already exists
        }

        Flight newFlight = new Flight(flightNumber, origin, destination, capacity, flightFare);
        flights[flightCount++] = newFlight;
        return true;
    }

    // Check if a flight number already exists
    public boolean isFlightNumberExists(String flightNumber) {
        return Arrays.stream(flights)
                .filter(flight -> flight != null && flight.getFlightNumber().equals(flightNumber))
                .findAny()
                .isPresent();
    }

    // Delete a flight by flight number
    public boolean deleteFlight(String flightNumber) {
        for (int i = 0; i < flights.length; i++) {
            if (flights[i] != null && flights[i].getFlightNumber().equals(flightNumber)) {
                // Check if flight has bookings
                if (Arrays.stream(flights[i].getCustomers()).anyMatch(customer -> customer != null)) {
                    System.out.println("Cannot delete flight: Flight has existing bookings.");
                    return false; // Flight cannot be deleted due to existing bookings
                }

                flights[i] = null; // Remove the flight
                return true; // Flight deleted successfully
            }
        }
        return false; // Flight not found
    }

    // Add a booking
    public boolean addBooking(int customerId, String flightNumber, String bookingDate) {
        if (bookingCount >= bookings.length) {
            return false; // Array is full
        }

        Customer customer = findCustomer(customerId);
        Flight flight = findFlight(flightNumber);

        if (customer == null || flight == null || !flight.addCustomer(customer)) {
            return false; // Customer or flight not found or flight is full
        }

        int bookingNumber = bookingCount + 1;
        Booking newBooking = new Booking(bookingNumber, bookingDate, flight, customer);
        bookings[bookingCount++] = newBooking;

        customer.addBooking(); // Increment the customer's booking count

        return true;
    }

    // Find a customer by ID
    public Customer findCustomer(int customerId) {
        return Arrays.stream(customers)
                .filter(customer -> customer != null && customer.getId() == customerId)
                .findAny()
                .orElse(null);
    }

    // Find a flight by flight number
    public Flight findFlight(String flightNumber) {
        return Arrays.stream(flights)
                .filter(flight -> flight != null && flight.getFlightNumber().equals(flightNumber))
                .findAny()
                .orElse(null);
    }
}
