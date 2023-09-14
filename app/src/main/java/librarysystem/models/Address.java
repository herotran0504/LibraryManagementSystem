package librarysystem.models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.io.Serializable;

public class Address implements Serializable {

    private transient final StringProperty streetProperty;
    private transient final StringProperty cityProperty;
    private transient final StringProperty stateProperty;
    private transient final StringProperty zipProperty;
    public String street;
    public String city;
    public String state;
    public String zip;

    public Address() {
        this.cityProperty = new SimpleStringProperty("");
        this.streetProperty = new SimpleStringProperty("");
        this.stateProperty = new SimpleStringProperty("");
        this.zipProperty = new SimpleStringProperty("");
    }

    public Address(String street, String city, String state, String zip) {
        this.street = street;
        this.city = city;
        this.state = state;
        this.zip = zip;

        this.cityProperty = new SimpleStringProperty(city);
        this.streetProperty = new SimpleStringProperty(street);
        this.stateProperty = new SimpleStringProperty(state);
        this.zipProperty = new SimpleStringProperty(zip);
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public StringProperty getCityProperty() {
        return cityProperty;
    }

    public StringProperty getStreetProperty() {
        return streetProperty;
    }

    public StringProperty getStateProperty() {
        return stateProperty;
    }

    public StringProperty getZipProperty() {
        return zipProperty;
    }


}