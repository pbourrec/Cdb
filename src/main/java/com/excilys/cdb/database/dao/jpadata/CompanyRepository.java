package com.excilys.cdb.database.dao.jpadata;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.excilys.cdb.database.core.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long>{
}