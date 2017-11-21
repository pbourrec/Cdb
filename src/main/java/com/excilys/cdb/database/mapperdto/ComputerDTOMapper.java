package com.excilys.cdb.database.mapperdto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.excilys.cdb.database.core.Computer;
import com.excilys.cdb.database.core.ComputerDTO;
import com.excilys.cdb.database.validator.DataValidation;

@Component
public class ComputerDTOMapper {
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
		List<ComputerDTO> listcomputerDtoDTO = new ArrayList<>();
		for (Computer computer: computertoList) {
			ComputerDTO computerDtoDto = new ComputerDTO(computer.getId(),
											       computer.getComputerName(),
											       computer.getCompany().getName(),
											       computer.getCompany().getId(),
												  (computer.getDateIntroduced()!=null? String.valueOf(computer.getDateIntroduced()) :null),
												  (computer.getDateDiscontinued()!=null?  String.valueOf(computer.getDateDiscontinued()):null)
												  );		
		listcomputerDtoDTO.add(computerDtoDto);
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

