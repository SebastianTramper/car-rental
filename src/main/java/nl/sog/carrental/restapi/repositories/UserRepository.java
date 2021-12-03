package nl.sog.carrental.restapi.repositories;

import nl.sog.carrental.restapi.entities.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

    /**
     * Searches the database to find a user with the given email
     *
     * @param email The email of the user
     * @return Returns a user object if a user exists with the corresponding email
     */
    User findByEmail(String email);
}