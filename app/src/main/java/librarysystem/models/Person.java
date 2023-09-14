package librarysystem.models;

import java.io.Serializable;

public class Person implements Serializable {

    private String firstname;
    private String lastName;
    private String phone;
    private Address address;

    public Person() {
    }

    public Person(String firstname, String lastName, String phone, Address address) {
        this.firstname = firstname;
        this.lastName = lastName;
        this.phone = phone;
        this.address = address;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}