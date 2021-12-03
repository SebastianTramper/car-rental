package nl.sog.carrental.restapi.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class User extends Base {
    @Id
    @GeneratedValue
    private long id;
    private String firstName;
    private String lastName;
    private String address;
    private String zipCode;
    private String country;
    private String email;
    private String phone;
    private Float drivingBehaviourRating;

    protected User() {}

    public User(String firstName, String lastName, String address, String zipCode, String country, String email, String phone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.zipCode = zipCode;
        this.country = country;
        this.email = email;
        this.phone = phone;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Float getDrivingBehaviourRating() {
        return drivingBehaviourRating;
    }

    public void setDrivingBehaviourRating(Float drivingBehaviourRating) {
        this.drivingBehaviourRating = drivingBehaviourRating;
    }
}
