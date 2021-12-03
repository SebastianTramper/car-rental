package nl.sog.carrental.restapi.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Vehicle extends Base {
    @Id
    @GeneratedValue
    private long id;
    private long userId;
    private String brand;
    private String model;
    private String color;
    private Integer yearOfManufacture;
    private Integer vehicleType;
    private Integer fuel;
    private Integer drivenKilometers;
    private Float fuelConsumptionPerKilometer;
    private Float pricePerDay;
    private String description;

    protected Vehicle() {}

    public Vehicle(long userId,
                   String brand,
                   String model,
                   String color,
                   Integer yearOfManufacture,
                   Integer vehicleType,
                   Integer fuel,
                   Integer drivenKilometers,
                   Float fuelConsumptionPerKilometer,
                   Float pricePerDay,
                   String description) {
        this.userId = userId;
        this.brand = brand;
        this.model = model;
        this.color = color;
        this.yearOfManufacture = yearOfManufacture;
        this.vehicleType = vehicleType;
        this.fuel = fuel;
        this.drivenKilometers = drivenKilometers;
        this.fuelConsumptionPerKilometer = fuelConsumptionPerKilometer;
        this.pricePerDay = pricePerDay;
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getYearOfManufacture() {
        return yearOfManufacture;
    }

    public void setYearOfManufacture(Integer yearOfManufacture) {
        this.yearOfManufacture = yearOfManufacture;
    }

    public Integer getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(Integer vehicleType) {
        this.vehicleType = vehicleType;
    }

    public Integer getFuel() {
        return fuel;
    }

    public void setFuel(Integer fuel) {
        this.fuel = fuel;
    }

    public Integer getDrivenKilometers() {
        return drivenKilometers;
    }

    public void setDrivenKilometers(Integer drivenKilometers) {
        this.drivenKilometers = drivenKilometers;
    }

    public Float getFuelConsumptionPerKilometer() {
        return fuelConsumptionPerKilometer;
    }

    public void setFuelConsumptionPerKilometer(Float fuelConsumptionPerKilometer) {
        this.fuelConsumptionPerKilometer = fuelConsumptionPerKilometer;
    }

    public Float getPricePerDay() {
        return pricePerDay;
    }

    public void setPricePerDay(Float pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
