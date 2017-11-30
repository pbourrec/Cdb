package org.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import org.model.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long>{
}