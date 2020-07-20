package mil.dtic.ured.repository;

import org.springframework.data.repository.CrudRepository;

import mil.dtic.ured.model.Login;

public interface LoginRepository extends CrudRepository<Login, String>{

}
