package nl.sog.carrental.restapi.repositories;

import nl.sog.carrental.restapi.entities.Vehicle;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface VehicleRepository extends CrudRepository<Vehicle, Long> {

    /**
     * Find the vehicle based on userId
     *
     * @param userId
     * @return Returns list with vehicles
     */
    List<Vehicle> findByUserId(long userId);

    /**
     * Find the brand
     *
     * @param brand
     * @return Returns list with vehicles
     */
    List<Vehicle> findByBrand(String brand);

    /**
     * Find vehicle based on brand and model
     *
     * @param brand
     * @param model
     * @return Returns list with vehicles
     */
    List<Vehicle> findByBrandAndModel(String brand, String model);
}
