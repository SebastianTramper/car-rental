package nl.sog.carrental.restapi.controllers;

import nl.sog.carrental.restapi.entities.Vehicle;
import nl.sog.carrental.restapi.services.VehicleService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VehicleControllerTest {

    Vehicle vehicle = new Vehicle(1L,
            "BMW",
            "i3",
            "red",
            2020,
            1,
            0,
            2000,
            20.0f,
            20.0f,
            "dit is een tekst");

    /**
     * Check if the cost of amount returned correct
     */
    @Test
    void calculateFuelConsumption() {
        VehicleService vehicleService = new VehicleService();
        float gasConsumption = vehicleService.calculateFuelConsumption(100f,20f);
        assertEquals(5f,gasConsumption);
    }



    /**
     * Check if vehicle type and return the correct price per unit
     */
    @Test
    void getFuelPrice() {
        VehicleService vehicleService = new VehicleService();
        vehicle.setVehicleType(0);
        float fuelPrice = vehicleService.getFuelPrice(vehicle);
        assertEquals(1.5,fuelPrice);

        vehicle.setVehicleType(1);
        fuelPrice = vehicleService.getFuelPrice(vehicle);
        assertEquals(0.5,fuelPrice);

        vehicle.setVehicleType(2);
        fuelPrice = vehicleService.getFuelPrice(vehicle);
        assertEquals(1,fuelPrice);

        vehicle.setVehicleType(3);
        assertThrows(IllegalStateException.class, () -> vehicleService.getFuelPrice(vehicle));

    }

    /**
     * Check the total cost of fuel in a year
     */
    @Test
    void calculateFuelCost() {
        VehicleService vehicleService = new VehicleService();
        float gasConsumption = vehicleService.calculateTotalFuelCost(vehicle);
        assertEquals(250f,gasConsumption);
    }

    /**
     * get fuel price kilometer
     */
    @Test
    void getFuelCostPerKm() {
        VehicleService vehicleService = new VehicleService();
        float gasConsumption = vehicleService.calculateCostPerKm(vehicle);
        assertEquals(0.025f,gasConsumption);
    }
}