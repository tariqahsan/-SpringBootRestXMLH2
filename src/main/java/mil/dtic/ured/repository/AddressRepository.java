package mil.dtic.ured.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import mil.dtic.ured.model.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
