package nl.sog.carrental.restapi.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import java.sql.Timestamp;

@Entity
public class Rental extends Base {
    @Id
    @GeneratedValue
    private long id;
    private long userId;
    private long vehicleId;
    private Timestamp CheckInDate;
    private Timestamp CheckOutDate;
    private Integer mileageStart;
    private Integer mileageEnd;
    private Integer fuelAmountStart;
    private Integer fuelAmountEnd;

    protected Rental() {}

    public Rental(long userId, long vehicleId, Timestamp checkInDate, Timestamp checkOutDate, Integer mileageStart, Integer fuelAmountStart) {
        this.userId = userId;
        this.vehicleId = vehicleId;
        this.CheckInDate = checkInDate;
        this.CheckOutDate = checkOutDate;
        this.mileageStart = mileageStart;
        this.fuelAmountStart = fuelAmountStart;
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

    public long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(long vehicleId) {
        this.vehicleId = vehicleId;
    }

    public Timestamp getCheckInDate() {
        return CheckInDate;
    }

    public void setCheckInDate(Timestamp checkInDate) {
        CheckInDate = checkInDate;
    }

    public Timestamp getCheckOutDate() {
        return CheckOutDate;
    }

    public void setCheckOutDate(Timestamp checkOutDate) {
        CheckOutDate = checkOutDate;
    }

    public Integer getMileageStart() {
        return mileageStart;
    }

    public void setMileageStart(Integer mileageStart) {
        this.mileageStart = mileageStart;
    }

    public Integer getMileageEnd() {
        return mileageEnd;
    }

    public void setMileageEnd(Integer mileageEnd) {
        this.mileageEnd = mileageEnd;
    }

    public Integer getFuelAmountStart() {
        return fuelAmountStart;
    }

    public void setFuelAmountStart(Integer fuelAmountStart) {
        this.fuelAmountStart = fuelAmountStart;
    }

    public Integer getFuelAmountEnd() {
        return fuelAmountEnd;
    }

    public void setFuelAmountEnd(Integer fuelAmountEnd) {
        this.fuelAmountEnd = fuelAmountEnd;
    }
}
