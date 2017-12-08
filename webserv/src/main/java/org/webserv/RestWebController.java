package org.webserv;

import java.util.List;

import org.model.Computer;
import org.model.ComputerDTO;
import org.service.CompanyService;
import org.service.ComputerService;
import org.service.ServletServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.validator.FormatValidation;

@RestController
public class RestWebController {
	private static final String errorName = "Vous devez saisir un nom pour l'ordinateur à créer";
	private  Logger logger = LoggerFactory.getLogger(RestWebController.class);
	static Long companyId=0L;
	private static final String errorMessageDelete ="Une erreur est survenue lors de la suppression de la companie. voir le message ci-dessous :" ;
	@Autowired
	private  FormatValidation controlFormat;
	@Autowired
	private ServletServices servletServices;
	@Autowired
	private ComputerDTOMapper computerDtoMapper;
	@Autowired
	private ComputerService computerService;
	@Autowired
	private CompanyService companyService;
	@Autowired
	private CompanyDTOMapper companyDtoMapper;

	@GetMapping("/computers")
	public List<ComputerDTO> getComputers() {
		logger.info("We were here : find all");
		return computerDtoMapper.computerListToDTO(computerService.getComputer());
	}

	@GetMapping("/computers/{id}")
	public ResponseEntity<ComputerDTO> getComputerById(@PathVariable("id")  String id){
		logger.info("We were there: find");
		ComputerDTO computersToFind = computerDtoMapper.computerToDTO(computerService.queryOneComputer(id));
		if (computersToFind!=null) {
			return new ResponseEntity<ComputerDTO>(computersToFind, HttpStatus.OK);
		}
		return new ResponseEntity<ComputerDTO>(HttpStatus.NOT_FOUND);
	}
	
	
	
	@PostMapping("/addComputer")
	public ResponseEntity<ComputerDTO>  addComputer( @RequestBody ComputerDTO computerDto){
		if(computerDto != null) {
			computerService.addComputer(computerDtoMapper.computerDtoTocomputer(computerDto));
			return new ResponseEntity<ComputerDTO>(computerDto, HttpStatus.OK);

		}
		
		return new ResponseEntity<ComputerDTO>(HttpStatus.NOT_FOUND);

	}
	
	
	@DeleteMapping("/deleteComputer/{id}")
	public ResponseEntity<ComputerDTO>  deleteComputer( @PathVariable String id){
		logger.info("We were there : delete");
		ComputerDTO computersToFind = computerDtoMapper.computerToDTO(computerService.queryOneComputer(id));	
		if(computersToFind != null) {
			computerService.deleteComputer(id);
			return new ResponseEntity<ComputerDTO>(computersToFind, HttpStatus.OK);

		}
		
		return new ResponseEntity<ComputerDTO>(HttpStatus.NOT_FOUND);

	}
	
	@PatchMapping("/editComputer")
	public ResponseEntity<ComputerDTO>  editComputer( @RequestBody ComputerDTO computerDto){
		if(computerDto != null) {
			computerService.updateComputer(computerDto.getComputerId(), computerDtoMapper.computerDtoTocomputer(computerDto));
			Computer computerUpdate = computerService.queryOneComputer(String.valueOf(computerDto.getComputerId()));
			ComputerDTO computerDtoUpdate = computerDtoMapper.computerToDTO(computerUpdate);
			return new ResponseEntity<ComputerDTO>(computerDtoUpdate, HttpStatus.OK);

		}
		
		return new ResponseEntity<ComputerDTO>(HttpStatus.NOT_FOUND);
	}

}
