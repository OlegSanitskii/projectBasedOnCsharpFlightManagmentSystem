public class Customer {
    private final int id;
    private String firstName;
    private String lastName;
    private int age;
    private int numberOfBookings;



    // Constructor
    public Customer(int id, String firstName, String lastName, int age) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.numberOfBookings = 0; // Initially, the customer has no bookings.
    }


    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getNumberOfBookings() {
        return numberOfBookings;
    }

    // Add a booking
    public void addBooking() {
        this.numberOfBookings++;
    }

    // Override toString method
    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + firstName + " " + lastName + ", Phone: " + age + ", Bookings: " + numberOfBookings;
    }
}