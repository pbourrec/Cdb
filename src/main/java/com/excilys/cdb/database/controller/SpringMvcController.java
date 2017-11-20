package com.excilys.cdb.database.controller;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.excilys.cdb.database.core.CompanyDTO;
import com.excilys.cdb.database.core.Computer;
import com.excilys.cdb.database.core.ComputerDTO;
import com.excilys.cdb.database.core.Page;
import com.excilys.cdb.database.mapperdao.ComputerMapper;
import com.excilys.cdb.database.mapperdto.CompanyDTOMapper;
import com.excilys.cdb.database.mapperdto.ComputerDTOMapper;
import com.excilys.cdb.database.service.CompanyService;
import com.excilys.cdb.database.service.ComputerService;
import com.excilys.cdb.database.service.ServletServices;
import com.excilys.cdb.database.validator.FormatValidation;


@Controller
@RequestMapping
public class SpringMvcController {
	private static final String errorName = "Vous devez saisir un nom pour l'ordinateur à créer";
	private  Logger logger = LoggerFactory.getLogger(SpringMvcController.class);
	static Long companyId=0L;
	private static final String errorMessageDelete ="Une erreur est survenue lors de la suppression de la companie. voir le message ci-dessous :" ;

	@Autowired
	private  FormatValidation controlFormat;
	@Autowired
	private ServletServices servletServices;
	@Autowired
	private Page page;
	@Autowired
	private ComputerDTOMapper computerDtoMapper;
	@Autowired
	private ComputerService computerService;
	@Autowired
	private CompanyService companyService;
	@Autowired
	private CompanyDTOMapper companyDtoMapper;
	@Autowired
	private ComputerMapper computerMapper;
	

	@GetMapping("/dashboard")
	public ModelAndView getDashboard ( @RequestParam Map<String, String> parameters){
		logger.info("entrée dans la méthode doGet de DashboardServlet");
		ModelAndView modelAndView = new ModelAndView("/dashboard");
		
		String computerPerPageReciever = parameters.get("numberdisplay")!=null ? parameters.get("numberdisplay") : "";
		String operation = parameters.get("pageoperation")!=null ? parameters.get("pageoperation") : "";
		String pageChange = parameters.get("page")!=null ? parameters.get("page") : "";
		String restart =parameters.get("restart")!=null ? parameters.get("restart") : ""; 
		logger.info( computerPerPageReciever+"," +  operation+"," + pageChange+"," +  restart+"," );


		int sizeTable= servletServices.changePageFormat ( computerPerPageReciever,  operation, pageChange,  restart,  page) ;
		page.pageOfComputer = computerDtoMapper.computerListToDTO(servletServices.listPage(
				page.getOffsetPage()*page.getComputerPerPage(),page.getComputerPerPage()));
		modelAndView.addObject("listComputer", page.pageOfComputer );
		modelAndView.addObject( "nextPage", page.getNextPageOK());
		modelAndView.addObject( "size", sizeTable);
		modelAndView.addObject("page", page.getOffsetPage());
		return modelAndView;
	}

	@PostMapping("/dashboard")
	protected ModelAndView postDashboard( @RequestParam Map<String, String> parameters){
		ModelAndView modelAndView = new ModelAndView("/dashboard");

		String actionType = parameters.get("actionType");
		int sizeTable = computerService.getSizeComputer();
		logger.info("entrée dans la méthode doGet de DashboardServlet");

		if(actionType.equals("delete")) {
			String idToDelete = parameters.get("selection");
			logger.info("Id to delete  " + idToDelete+", parameters.get()" + parameters.get("selection"));

			String computersDeleted = computerService.deleteComputer(idToDelete);
			modelAndView.addObject("deleted", computersDeleted);
			page.pageOfComputer = computerDtoMapper.computerListToDTO(servletServices.listPage(
					page.getOffsetPage()*page.getComputerPerPage(),page.getComputerPerPage()));
			modelAndView.addObject("listComputer", page.pageOfComputer );
		} else if (actionType.equals("Filter by name")) {
			String nameToFind = parameters.get("search");
			List<ComputerDTO> computersToFind = computerDtoMapper.computerListToDTO(computerService.findComputersByName(nameToFind));
			modelAndView.addObject("listComputer", computersToFind );
		} else if (actionType.equals("Filter by company")) {
			String CompanyToFind = parameters.get("search");
			List<ComputerDTO> computersToFind = computerDtoMapper.computerListToDTO(computerService.findComputersByCompany(CompanyToFind));
			modelAndView.addObject("listComputer", computersToFind );
		}
		sizeTable= computerService.getSizeComputer();
		modelAndView.addObject( "size", sizeTable);
		return modelAndView;

	}

	@GetMapping("/addComputer")
	public ModelAndView getAdd( @RequestParam Map<String, String> parameters){
		ModelAndView modelAndView = new ModelAndView("/addComputer");

		List<CompanyDTO> listCompanies= companyDtoMapper.companyListToDTO(companyService.getListCompany());
		modelAndView.addObject( "listcompanies", listCompanies);
		return modelAndView;
	}

	@PostMapping("/addComputer")
	protected ModelAndView postAdd( @RequestParam Map<String, String> parameters){
		ModelAndView modelAndView = new ModelAndView("/addComputer");

		String computerName = parameters.get("computerName");
		String introduced = parameters.get("introduced");
		String discontinued = parameters.get("discontinued");
		String companyId = parameters.get("companyId");
		if (computerName.equals("")) {
			modelAndView.addObject("errorName", errorName);
			List<CompanyDTO> listCompanies= companyDtoMapper.companyListToDTO(companyService.getListCompany());
			modelAndView.addObject( "listcompanies", listCompanies);

		}else {
			computerService.addComputer(computerName, introduced, discontinued, companyId);
		}
		return modelAndView;
	}

	
	@GetMapping("/deleteCompany")
	protected ModelAndView getDelete( @RequestParam Map<String, String> parameters){
		ModelAndView modelAndView = new ModelAndView("/deleteCompany");		
		List<CompanyDTO> listCompanies= companyDtoMapper.companyListToDTO(companyService.getListCompany());
		modelAndView.addObject( "listcompanies", listCompanies);

		return modelAndView;
	}

	
	@PostMapping("/deleteCompany")
	protected ModelAndView postDelete( @RequestParam Map<String, String> parameters){
		ModelAndView modelAndView = new ModelAndView("/deleteCompany");
		String confirmation = parameters.get("confirmation");
		if(confirmation==null) {	

			String companyIdString = parameters.get("companyId");
			companyId=controlFormat.stringTolong(companyIdString);
			List<ComputerDTO> computersToFind = computerDtoMapper.computerListToDTO(computerService.findComputersByCompanyId(companyId));
			modelAndView.addObject("listComputer", computersToFind );
			List<CompanyDTO> listCompanies= companyDtoMapper.companyListToDTO(companyService.getListCompany());
			modelAndView.addObject( "listcompanies", listCompanies);
			return modelAndView;
		}else if(confirmation.equals("Delete!")) {
			try {
				computerService.deleteComputerAndCompany(companyId);
				return modelAndView = new ModelAndView("/deleteCompany");
			} catch (SQLException e) {
				modelAndView.addObject( "errorMessage", errorMessageDelete+"\n"+e.getMessage());
				List<ComputerDTO> computersToFind = computerDtoMapper.computerListToDTO(
						computerService.findComputersByCompanyId(companyId));
				modelAndView.addObject("listComputer", computersToFind );
				List<CompanyDTO> listCompanies= companyDtoMapper.companyListToDTO(companyService.getListCompany());
				modelAndView.addObject( "listcompanies", listCompanies);
				return modelAndView;
			}
		}
		return modelAndView;
	}	
	
	@GetMapping("/editComputer")
	public ModelAndView getEdit ( @RequestParam Map<String, String> parameters){
		ModelAndView modelAndView = new ModelAndView("/editComputer");

		List<CompanyDTO> listCompanies= companyDtoMapper.companyListToDTO(companyService.getListCompany());
		modelAndView.addObject( "listcompanies", listCompanies);

		String computerId = parameters.get("computerid");
		ComputerDTO computerToEdit = computerDtoMapper.computerToDTO(computerService.queryOneComputer(computerId));
		modelAndView.addObject( "computer",computerToEdit);
		return modelAndView;
	}

	@PostMapping("/editComputer")
	public ModelAndView postEdit ( @RequestParam Map<String, String> parameters){
		ModelAndView modelAndView = new ModelAndView("/editComputer");

		String computerId = parameters.get("computerid");
		String computerName = parameters.get("computerName");
		String introduced = parameters.get("introduced");
		String discontinued = parameters.get("discontinued");
		String companyId = parameters.get("companyId");

		Computer computerToAdd = new Computer();
		computerToAdd = computerMapper.computerBuilder(computerName, companyId, introduced, discontinued);
		
		computerService.updateComputer(computerId, computerToAdd);
		return modelAndView;
	}
}

