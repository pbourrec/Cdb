package org.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import org.model.Computer;

@Repository
public interface ComputerRepository extends CrudRepository<Computer, Long>{
	
	@Query(value= "SELECT * FROM computer LEFT JOIN company ON computer.company_id=company.id WHERE computer.id=?1", nativeQuery=true)
	public Computer getComputerByIdJpa(Long id);
	
	@Query(value=  "SELECT * FROM computer LEFT JOIN company ON computer.company_id=company.id WHERE computer.name LIKE '%' ?1 '%'", nativeQuery=true)
	public List<Computer> getComputerByNameJpa(String nameToFind);
	
	@Query(value= "SELECT * FROM computer LEFT JOIN company ON computer.company_id=company.id WHERE company.name LIKE '%' ?1 '%'", nativeQuery=true)
	public  List<Computer> getComputerByCompanyJpa(String companyToFind);
	
	@Query(value= "SELECT * FROM computer LEFT JOIN company ON computer.company_id=company.id WHERE company.id=?1", nativeQuery=true)
	public  List<Computer> getComputerByCompanyIdJpa(Long companyIdToFind);
	
	@Query(value= "DELETE FROM computer WHERE computer.company_id=?1", nativeQuery=true)
	public  void deleteWithCompanyJpa( Long companyId);
		
	@Query(value= "SELECT * FROM computer LEFT JOIN company ON computer.company_id=company.id LIMIT ?2 OFFSET ?1", nativeQuery=true)
	public  List<Computer> getComputerPaginationJpa(Long offSet, Long limit);
	
	@Query(value="SELECT COUNT(*) from computer WHERE id=?1", nativeQuery=true)
	public  int getSizeComputerIdJpa(Long computerId) ;
}
