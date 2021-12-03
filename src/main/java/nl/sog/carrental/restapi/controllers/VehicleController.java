package nl.sog.carrental.restapi.controllers;

import nl.sog.carrental.restapi.entities.Vehicle;
import nl.sog.carrental.restapi.repositories.VehicleRepository;
import nl.sog.carrental.restapi.services.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/vehicle")
public class VehicleController {

    /**
     * Inject VehicleRepository dependency
     */
    @Autowired
    private VehicleRepository repository;

    private VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    /**
     * This endpoint creates a new vehicle
     *
     * @param userId                      This is the primary key that is used to do database actions.
     * @param brand                       This is the brand of the Vehicle.
     * @param model                       This is the model of the Vehicle.
     * @param color                       This is the color of the Vehicle.
     * @param yearOfManufacture           This is year of manufacturing.
     * @param vehicleType                 This is the vehicle type.
     * @param fuel                        This is the fuel type of the vehicle. This is an enum.
     * @param drivenKilometers            The drivenKilometers is used to track the mileage.
     * @param fuelConsumptionPerKilometer This is the fuel consumption per kilometer.
     * @param pricePerDay                 This is the price that the renter sets for his/her vehicle.
     * @param description                 This is the description that the renter sets for his/her vehicle.
     * @return Returns newly created Vehicle
     */
    @PostMapping(path = "/create")
    public @ResponseBody
    Vehicle create(@RequestParam long userId,
                   @RequestParam String brand,
                   @RequestParam String model,
                   @RequestParam String color,
                   @RequestParam Integer yearOfManufacture,
                   @RequestParam Integer vehicleType,
                   @RequestParam Integer fuel,
                   @RequestParam Integer drivenKilometers,
                   @RequestParam Float fuelConsumptionPerKilometer,
                   @RequestParam Float pricePerDay,
                   @RequestParam String description) {
        Vehicle vehicle = new Vehicle(userId, brand, model, color, yearOfManufacture, vehicleType, fuel, drivenKilometers, fuelConsumptionPerKilometer, pricePerDay, description);
        repository.save(vehicle);
        return vehicle;
    }

    /**
     * Endpoint get a specific vehicle with the id from the path
     *
     * @param id
     * @return Return the selected vehicle
     */
    @GetMapping(path = "/{id}")
    public @ResponseBody
    Vehicle get(@PathVariable(value = "id") long id) {
        Optional<Vehicle> vehicle = repository.findById(id);
        System.out.println("Getting vehicle for id: " + id);
        return vehicle.orElse(null);
    }

    /**
     * Filter vehicle based on brand or brand and model
     *
     * @param brand
     * @param model
     * @return Returns all vehicles based on the filters
     */
    @GetMapping(path = "/filter")
    public @ResponseBody
    List<Vehicle> getByBrandAndModel(@RequestParam String brand,
                                     @Nullable @RequestParam String model) {
        if (model != null) {
            return repository.findByBrandAndModel(brand, model);
        } else {
            return repository.findByBrand(brand);
        }
    }

    /***
     * Endpoint for getting the vehicle based on the userId
     *
     * @param id
     * @return Returns the vehicle from the user
     */
    @GetMapping(path = "/user/{userId}")
    public @ResponseBody
    List<Vehicle> getForUser(@PathVariable(value = "userId") long id) {
        List<Vehicle> vehicle = repository.findByUserId(id);
        return vehicle;
    }

    /**
     * Endpoint for returning all vehicles in the database
     *
     * @return Returns a list of all vehicles
     */
    @GetMapping(path = "/all")
    public @ResponseBody
    Iterable<Vehicle> all() {
        return repository.findAll();
    }

    /**
     * Updates a single vehicle
     *
     * @param vehicle The vehicle that should be updated.
     *                This vehicle contains all the new data that
     *                will be saved in the database.
     * @return Return the newly updated vehicle
     */
    @PatchMapping(path = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    Vehicle update(@RequestBody Vehicle vehicle) {
        Vehicle vehicleNew = repository.save(vehicle);
        return vehicleNew;
    }

    /***
     * Endpoint deletes a vehicle with id from the path
     *
     * @param id
     * @return Return the Vehicle which is delete from the database
     */
    @DeleteMapping(path = "/{id}")
    public @ResponseBody
    Vehicle delete(@PathVariable(value = "id") long id) {
        Optional<Vehicle> vehicle = repository.findById(id);
        if (vehicle.isPresent()) {
            repository.deleteById(id);
            return vehicle.get();
        }

        return null;
    }

    /**
     * Calculate the cost of owing a vehicle
     *
     * @param id
     * @return Total cost of owning
     */
    @GetMapping(path = "/{id}/total-cost/")
    public Float getTotalFuelCostPerYear(@PathVariable(value = "id") long id) {
        Optional<Vehicle> vehicle = repository.findById(id);
        return vehicleService.calculateTotalFuelCost(vehicle.get());
    }


    /**
     * Calculate the cost of owing a vehicle
     *
     * @param id
     * @return Total cost of owning
     */
    @GetMapping(path = "/{id}/cost")
    public Float getFuelCostPerKm(@PathVariable(value = "id") long id) {
        Optional<Vehicle> vehicle = repository.findById(id);
        return vehicleService.calculateCostPerKm(vehicle.get());
    }
}
