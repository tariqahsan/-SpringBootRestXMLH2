package mil.dtic.ured.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import mil.dtic.ured.model.Purchase;

public interface PurchaseRepository extends CrudRepository<Purchase, Long> {

}
