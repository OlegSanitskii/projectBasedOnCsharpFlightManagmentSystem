public class Flight {
    private final String flightNumber;
    private String origin;
    private String destination;
    private final int capacity;
    private Customer[] customers;
    private int customerCount;

    private static double flightFare;

    // Constructor
    public Flight(String flightNumber, String origin, String destination, int capacity, double flightFare) {
        this.flightNumber = flightNumber;
        this.origin = origin;
        this.destination = destination;
        this.capacity = capacity;
        this.customers = new Customer[capacity];
        this.flightFare = flightFare;
        this.customerCount = 0;
    }

    // Add a customer to the flight
    public boolean addCustomer(Customer customer) {
        if (customerCount < capacity) {
            customers[customerCount] = customer;
            customerCount++;
            return true;
        } else {
            return false; // Flight is full
        }
    }

    // Getters
    public String getFlightNumber() {
        return flightNumber;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public int getCapacity() {
        return capacity;
    }

    public Customer[] getCustomers() {
        return customers;
    }

    public static double getFlightFare() { return flightFare; }


    // Override toString method
    @Override
    public String toString() {
        return "Flight Number: " + flightNumber + ", Origin: " + origin +
                ", Destination: " + destination + ", Capacity: " + capacity +
                ", Booked Seats: " + customerCount;
    }
}
