package nl.sog.carrental.restapi.services;

import nl.sog.carrental.restapi.entities.Vehicle;
import org.springframework.stereotype.Service;

@Service
public class VehicleService {


    /**
     * Get the fuel price based on the vehicle type
     *
     * @param vehicle
     * @return float fuel price
     * @throws Exception when type does not exist
     */
    public float getFuelPrice(Vehicle vehicle) {
        if (vehicle.getVehicleType().equals(0)) {
            return 1.5f; // gas price
        } else if (vehicle.getVehicleType().equals(1)) {
            return 0.5f; // battery price
        } else if (vehicle.getVehicleType().equals(2)) {
            return 1f; // Fuel cell price
        } else {
            throw new IllegalStateException("Vehicle type is not correct");
        }
    }

    /**
     * Calculate fuel used
     *
     * @param averageKm
     * @param fuelConsumption
     * @return total fuel used
     */
    public float calculateFuelConsumption(float averageKm, float fuelConsumption) {
        if(fuelConsumption == 0){
            throw new ArithmeticException("Cannot divide by zero");
        }
        return averageKm / fuelConsumption;
    }

    /**
     * Get total cost of fuel consumed
     *
     * @param vehicle
     * @return total cost
     */
    public float calculateTotalFuelCost(Vehicle vehicle) {
        float averageKm = 10000.0f;
        float gasPricePerLiter = this.getFuelPrice(vehicle);
        float fuelConsumption = this.calculateFuelConsumption(averageKm, vehicle.getFuelConsumptionPerKilometer());

        return fuelConsumption * gasPricePerLiter;
    }

    /**
     * get cost per km
     *
     * @param vehicle
     * @return float cost
     */
    public float calculateCostPerKm(Vehicle vehicle) {
        float gasPricePerLiter = this.getFuelPrice(vehicle);
        float fuelConsumedPerKilometer = vehicle.getFuelConsumptionPerKilometer();
        return gasPricePerLiter / fuelConsumedPerKilometer;
    }

}
