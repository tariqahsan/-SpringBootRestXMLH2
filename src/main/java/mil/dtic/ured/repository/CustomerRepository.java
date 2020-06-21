package mil.dtic.ured.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import mil.dtic.ured.model.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
//public interface CustomerRepository extends JpaRepository<Customer, Long> {
	List<Customer> findByAge(int age);
}
