package com.excilys.cdb.database.mapperdto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.excilys.cdb.database.core.Computer;
import com.excilys.cdb.database.core.ComputerDTO;

@Component
public class ComputerDTOMapper {
	
	public ComputerDTO computerToDTO(Computer computer) {
		ComputerDTO computerDto = new ComputerDTO(computer.getId(),
				computer.getComputerName(),
				computer.getCompany().getName(),
				computer.getCompany().getId(),
				(computer.getDateIntroduced()!=null? String.valueOf(computer.getDateIntroduced()) :""),
				(computer.getDateDiscontinued()!=null?  String.valueOf(computer.getDateDiscontinued()): "")
				);		
		return computerDto;
		
	}
	
	public List<ComputerDTO> computerListToDTO(List<Computer> computerList) {
		List<ComputerDTO> listComputerDTO = new ArrayList<>();
		for (Computer computer : computerList) {
		ComputerDTO computerDto = new ComputerDTO(computer.getId(),
												  computer.getComputerName(),
												  computer.getCompany().getName(),
												  computer.getCompany().getId(),
												  (computer.getDateIntroduced()!=null? String.valueOf(computer.getDateIntroduced()) :""),
												  (computer.getDateDiscontinued()!=null?  String.valueOf(computer.getDateDiscontinued()): "")
												  );		
		listComputerDTO.add(computerDto);
		}
		return listComputerDTO;
		
	}
}
