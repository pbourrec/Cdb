package org.webservice;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.model.Computer;
import org.model.ComputerDTO;
import org.validator.DataValidation;

@Component
public class ComputerDTOMapper {
	@Autowired
	private DataValidation dataValidation;
	
	public ComputerDTO computerToDTO(Computer computer) {
		ComputerDTO computerDtoDto = new ComputerDTO(computer.getId(),
				computer.getComputerName(),
				(computer.getCompany()!=null ? computer.getCompany().getName() :null),
				(computer.getCompany()!=null ?  computer.getCompany().getId():0),
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
											       (computer.getCompany()!=null ? computer.getCompany().getName() :null),
											       (computer.getCompany()!=null ?  computer.getCompany().getId():0),
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

