public class Booking {
    private final String bookingDate;
    private final int bookingNumber;
    private final Flight associatedFlight;
    private final Customer bookedCustomer;
    private double taxRate = 1.13;

    // Constructor
    public Booking(int bookingNumber, String bookingDate, Flight flight, Customer customer) {
        this.bookingNumber = bookingNumber;
        this.bookingDate = bookingDate;
        this.associatedFlight = flight;
        this.bookedCustomer = customer;
    }

    // Getters
    public String getBookingDate() {
        return bookingDate;
    }

    public int getBookingNumber() {
        return bookingNumber;
    }

    public Flight getAssociatedFlight() {
        return associatedFlight;
    }

    public Customer getBookedCustomer() {
        return bookedCustomer;
    }

    public double totalFareCount() { return Flight.getFlightFare() * taxRate;}

    // Override toString method to display booking information
    @Override
    public String toString() {
        return "Booking Number: " + bookingNumber + ", Date: " + bookingDate +
                ", Flight: " + associatedFlight.getFlightNumber() +
                ", Customer: " + bookedCustomer.getFirstName() + " " + bookedCustomer.getLastName() +
                ", Total Fare " + totalFareCount();
    }
}