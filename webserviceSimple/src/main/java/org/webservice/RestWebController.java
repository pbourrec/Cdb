package org.webservice;

import java.util.List;

import org.model.ComputerDTO;
import org.service.CompanyService;
import org.service.ComputerService;
import org.service.Page;
import org.service.ServletServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
		return computerDtoMapper.computerListToDTO(computerService.getComputer());
}
	
	@GetMapping("/computers/{id}")
	public ResponseEntity<ComputerDTO> getDashboard (@PathVariable("id")  String id){
		ComputerDTO computersToFind = computerDtoMapper.computerToDTO(computerService.queryOneComputer(id));
		if (computersToFind!=null) {
			return new ResponseEntity<ComputerDTO>(computersToFind, HttpStatus.OK);
		}
		return new ResponseEntity<ComputerDTO>(HttpStatus.NOT_FOUND);
		//	}
		//
		//	@PostMapping("/dashboard")
		//	protected ModelAndView postDashboard( @RequestParam Map<String, String> parameters){
		//
		//	}
		//
		//	@GetMapping("/addComputer")
		//	public ModelAndView getAdd(){
		//	}
		//
		//	@PostMapping("/addComputer")
		//	protected ModelAndView postAdd(@Valid ComputerDTO computerDto ){
		//	}
		//
		//	
		//	@GetMapping("/deleteCompany")
		//	protected ModelAndView getDelete(){
		//	}
		//
		//	
		//	@PostMapping("/deleteCompany")
		//	protected ModelAndView postDelete( @RequestParam Map<String, String> parameters){
		//	}	
		//	
		//	@GetMapping("/editComputer")
		//	public ModelAndView getEdit ( @RequestParam("computerid")String computerId){
		//	}
		//
		//	@PostMapping("/editComputer")
		//	public ModelAndView postEdit ( @Valid ComputerDTO computerDto ){
		//	}
		//	
		//	@PostMapping("/deleteSelected")
		//	public ModelAndView postDeleteSelected ( @RequestParam("selection")String idToDelete){
	}
}
