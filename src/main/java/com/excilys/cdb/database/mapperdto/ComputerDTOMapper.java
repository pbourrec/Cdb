package com.excilys.cdb.database.mapperdto;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.excilys.cdb.database.core.Computer;
import com.excilys.cdb.database.core.ComputerDTO;
import com.excilys.cdb.database.dao.CompanyDAO;
import com.excilys.cdb.database.dao.ComputerDAO;
import com.excilys.cdb.database.validator.DataValidation;

@Component
public class ComputerDTOMapper {
	Logger logger = LoggerFactory.getLogger(ComputerDTOMapper.class);
	@Autowired
	CompanyDAO companyDao;
	@Autowired
	private DataValidation dataValidation;

	public ComputerDTO computerToDTO(Computer computer) {
		ComputerDTO computerDtoDto = new ComputerDTO(computer.getId(),
				computer.getComputerName(),
				computer.getCompany().getName(),
				computer.getCompany().getId(),
				(computer.getDateIntroduced()!=null? String.valueOf(computer.getDateIntroduced()) :null),
				(computer.getDateDiscontinued()!=null?  String.valueOf(computer.getDateDiscontinued()):null)
				);		
		return computerDtoDto;
	}

	public List<ComputerDTO> computerListToDTO(List<Computer> computertoList) {
		System.out.println(companyDao.getCompany());
		List<ComputerDTO> listcomputerDtoDTO = new ArrayList<>();
		int n=0;
		for (Computer computer: computertoList) {
			try {
				n++;
				ComputerDTO computerDtoDto = new ComputerDTO(computer.getId(),
						computer.getComputerName(),
						(computer.getCompany().getName()!=null? computer.getCompany().getName() :null),
						(computer.getCompany().getId()!=null?  computer.getCompany().getId():null),
						(computer.getDateIntroduced()!=null? String.valueOf(computer.getDateIntroduced()) :null),
						(computer.getDateDiscontinued()!=null?  String.valueOf(computer.getDateDiscontinued()):null)
						);		
				listcomputerDtoDTO.add(computerDtoDto);
			}catch (NullPointerException e) {
				System.out.println("erreur lors de la "+ n + "operation, \n sur l'ordinateur suivant " + computer.getId());
			}
		}
		return listcomputerDtoDTO;
	}

	public Computer computerDtoTocomputer(ComputerDTO computerDto) {
		Computer computer= new Computer(computerDto.getComputerId(), 
				computerDto.getComputerName(), 
				computerDto.getCompanyId(), 
				dataValidation.convertStringToTimestamp(computerDto.getDateIntroduced()),  
				dataValidation.convertStringToTimestamp(computerDto.getDateDiscontinued()));
		return computer;
	}
}

