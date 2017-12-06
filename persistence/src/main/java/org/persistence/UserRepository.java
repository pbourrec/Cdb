package org.persistence;

import org.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long>{

	@Query(value= "SELECT * FROM user WHERE login=?1", nativeQuery=true)
	public User findByLogin(String name);

	@Query(value = "INSERT INTO USER VALUES (?1,?2, ?3) ", nativeQuery= true)
	public boolean createUser(String name, String pwd);
}