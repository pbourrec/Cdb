package com.excilys.cdb.database.mapperdto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.excilys.cdb.database.core.Company;
import com.excilys.cdb.database.core.CompanyDTO;
@Component
public class CompanyDTOMapper {
	
	public CompanyDTO companyToDTO(Company company) {
		CompanyDTO companyDto = new CompanyDTO((long)company.getId(), 
				company.getName());

		return companyDto;
	}


	public List<CompanyDTO> companyListToDTO(List<Company> companyList) {
		List<CompanyDTO> listcompanyDTO = new ArrayList<>();
		for (Company company : companyList) {
			CompanyDTO companyDto = new CompanyDTO((long)company.getId(), 
													company.getName());

			listcompanyDTO.add(companyDto);
		}
		return listcompanyDTO;

	}
}
