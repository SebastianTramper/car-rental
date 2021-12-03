package nl.sog.carrental.restapi.repositories;

import nl.sog.carrental.restapi.entities.Rental;
import org.springframework.data.repository.CrudRepository;

public interface RentalRepository extends CrudRepository<Rental, Long> {}