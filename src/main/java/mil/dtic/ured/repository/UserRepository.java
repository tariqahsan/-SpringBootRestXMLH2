package mil.dtic.ured.repository;

import org.springframework.data.repository.CrudRepository;
import mil.dtic.ured.model.User;

public interface UserRepository extends CrudRepository<User, Long>{

}
