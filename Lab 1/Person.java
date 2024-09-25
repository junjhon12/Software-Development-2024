// Class representing a Person
public class Person {
    private String name;    // Person's name
    private int age;        // Person's age
    private String address; // Person's address

    // Constructor to initialize a Person's details
    public Person(String name, int age, String address) {
        this.name = name;        // Initialize name
        this.age = age;          // Initialize age
        this.address = address;  // Initialize address
    }

    // Method to print the person's information
    public void printInfo() {
        System.out.println("Person Information:"); // Print header for person information
        System.out.println("Name: " + name);       // Print the person's name
        System.out.println("Age: " + age);         // Print the person's age
        System.out.println("Address: " + address); // Print the person's address
    }

    // Getter method to retrieve the person's name
    public String getName() {
        return name; // Return the name
    }

    // Getter method to retrieve the person's age
    public int getAge() {
        return age; // Return the age
    }

    // Getter method to retrieve the person's address
    public String getAddress() {
        return address; // Return the address
    }
}
