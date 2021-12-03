package nl.sog.carrental.restapi.controllers;

import nl.sog.carrental.restapi.entities.User;
import nl.sog.carrental.restapi.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;


@RestController
@RequestMapping(path = "/user")
public class UserController {
    @Autowired private UserRepository repository;

    /**
     * Create new user with post request and inserted body.
     * Check if user inserted has email which is unique
     *
     * @param firstName
     * @param lastName
     * @param address
     * @param zipCode
     * @param country
     * @param email
     * @param phone
     * @return Returns newly created user
     */
    @PostMapping(path = "/create")
    public @ResponseBody User create(@RequestParam String firstName,
                                     @RequestParam String lastName,
                                     @RequestParam String address,
                                     @RequestParam String zipCode,
                                     @RequestParam String country,
                                     @RequestParam String email,
                                     @RequestParam String phone) {

        User existing = repository.findByEmail(email);
        if (existing != null) {
            throw new ResponseStatusException(HttpStatus.IM_USED);
        }
        User user = new User(firstName, lastName, address, zipCode, country, email, phone);
        repository.save(user);
        return user;
    }

    /**
     * Retrieves a user based on an id.
     * @param id This is the id that is used to retrieve a user.
     * @return Returns the selected User.
     */
    @GetMapping(path = "/{id}")
    public @ResponseBody User get(@PathVariable(value="id") long id) {
        Optional<User> user = repository.findById(id);
        return user.orElse(null);
    }

    /**
     * Retrieves all users
     * @return Returns all users
     */
    @GetMapping(path = "/all")
    public @ResponseBody Iterable<User> all() {
        return repository.findAll();
    }

    /**
     * Updates a single user
     *
     * @param user The user that should be updated.
     *                This user contains all the new data that
     *                will be saved in the database.
     *
     * @return Return the newly updated user
     */
    @PutMapping(path = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody User update(@RequestBody User user) {
        User userNew = repository.save(user);
        System.out.println("Updated user");
        return userNew;
    }

    /**
     * Removes the user by using the id in inside the PathVariable.
     * @param id
     * @return Returns the user before it's removed.
     */
    @DeleteMapping(path = "/{id}")
    public @ResponseBody User delete(@PathVariable(value="id") long id) {
        Optional<User> user = repository.findById(id);
        if (user.isPresent()) {
            repository.deleteById(id);
            return user.get();
        }

        return null;
    }
}
