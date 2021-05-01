package de.yannickSz.Rest.API.RestAPI.repositories;

import de.yannickSz.Rest.API.RestAPI.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository()
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
}
