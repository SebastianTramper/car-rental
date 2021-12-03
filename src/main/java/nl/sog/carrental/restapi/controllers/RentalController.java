package nl.sog.carrental.restapi.controllers;

import nl.sog.carrental.restapi.entities.Rental;
import nl.sog.carrental.restapi.repositories.RentalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.Optional;

@RestController
@RequestMapping(path = "/rental")
public class RentalController {

    @Autowired
    private RentalRepository repository;

    /**
     * Creates new rental with post request and inserted body.
     *
     * @param userId
     * @param vehicleId
     * @param checkIn
     * @param checkOut
     * @param mileageStart
     * @param fuelAmountStart
     * @return Returns newly created rental
     */

    @PostMapping(path = "/create")
    public @ResponseBody Rental create(@RequestParam long userId,
                                       @RequestParam long vehicleId,
                                       @RequestParam String checkIn,
                                       @RequestParam String checkOut,
                                       @RequestParam Integer mileageStart,
                                       @RequestParam Integer fuelAmountStart) {

        Timestamp checkInTimestamp = Timestamp.valueOf(checkIn);
        Timestamp checkOutTimestamp = Timestamp.valueOf(checkOut);

        System.out.println("Check in: " + checkInTimestamp.toString());
        System.out.println("Check out: " + checkOutTimestamp.toString());

        Rental rental = new Rental(userId, vehicleId, checkInTimestamp, checkOutTimestamp, mileageStart, fuelAmountStart);
        repository.save(rental);

        return rental;
    }

    /**
     * Retrieves a rental based on an id.
     * @param id This is the id that is used to retrieve a rental.
     * @return Returns the selected Rental.
     */
    @GetMapping(path = "/{id}")
    public @ResponseBody Rental get(@PathVariable(value="id") long id) {
        Optional<Rental> rental = repository.findById(id);
        return rental.orElse(null);
    }

    /**
     * Retrieves all rentals
     * @return Returns all rentals
     */
    @GetMapping(path = "/all")
    public @ResponseBody Iterable<Rental> all() {
        return repository.findAll();
    }

    /**
     * Updates a single Rental
     *
     * @param rental The rental that should be updated.
     *                This rental contains all the new data that
     *                will be saved in the database.
     *
     * @return Return the newly updated rental
     */
    @PatchMapping(path = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Rental update(@RequestBody Rental rental) {
        Rental rentalNew = repository.save(rental);
        return rental;
    }

    /**
     *
      * @param id
     * @return Removes the user by using the id in inside the PathVariable.
     */
    @DeleteMapping(path = "/{id}")
    public @ResponseBody Rental delete(@PathVariable(value="id") long id) {
        Optional<Rental> rental = repository.findById(id);
        if (rental.isPresent()) {
            repository.deleteById(id);
            return rental.get();
        }

        return null;
    }
}
