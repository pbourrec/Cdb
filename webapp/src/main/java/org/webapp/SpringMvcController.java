package org.webapp;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.validation.Valid;

import org.model.CompanyDTO;
import org.model.ComputerDTO;
import org.service.CompanyService;
import org.service.ComputerService;
import org.service.Page;
import org.service.ServletServices;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.validator.FormatValidation;



@Controller
@RequestMapping
public class SpringMvcController {
	private static final String errorName = "Vous devez saisir un nom pour l'ordinateur à créer";
	private  Logger logger = (Logger) LoggerFactory.getLogger(SpringMvcController.class);
	static Long companyId=0L;
	private static final String errorMessageDelete ="Une erreur est survenue lors de la suppression de la companie. voir le message ci-dessous :" ;
	@Autowired
	private  FormatValidation controlFormat;
	@Autowired
	private ServletServices servletServices;
	@Autowired
	private PageDto pageDto;
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
		pageDto.pageOfComputer = computerDtoMapper.computerListToDTO(page.listPage(servletServices));
		modelAndView.addObject("listComputer", pageDto.pageOfComputer );
		modelAndView.addObject( "nextPage", pageDto.getNextPageOK());
		modelAndView.addObject( "size", sizeTable);
		modelAndView.addObject("page", pageDto.getOffsetPage());
		return modelAndView;
	}

	@PostMapping("/dashboard")
	protected ModelAndView postDashboard( @RequestParam Map<String, String> parameters){
		ModelAndView modelAndView = new ModelAndView("/dashboard");
		int sizeTable = computerService.getSizeComputer();
		String actionType = parameters.get("actionType");
		String nameToFind = parameters.get("search");
		String CompanyToFind = parameters.get("search");
		if (actionType.equals("Filter by name")) {
			List<ComputerDTO> computersToFind = computerDtoMapper.computerListToDTO(computerService.findComputersByName(nameToFind));
			modelAndView.addObject("listComputer", computersToFind );
			
		} else if (actionType.equals("Filter by company")) {
			List<ComputerDTO> computersToFind = computerDtoMapper.computerListToDTO(computerService.findComputersByCompany(CompanyToFind));
			modelAndView.addObject("listComputer", computersToFind );
		}
		sizeTable= computerService.getSizeComputer();
		modelAndView.addObject( "size", sizeTable);
		return modelAndView;

	}

	@GetMapping("/addComputer")
	public ModelAndView getAdd(){
		ModelAndView modelAndView = new ModelAndView("/addComputer");
		List<CompanyDTO> listCompanies= companyDtoMapper.companyListToDTO(companyService.getListCompany());
		modelAndView.addObject( "listcompanies", listCompanies);
		return modelAndView;
	}

	@PostMapping("/addComputer")
	protected ModelAndView postAdd(@Valid ComputerDTO computerDto ){
		ModelAndView modelAndView = new ModelAndView("/addComputer");
		logger.info("Ordinateur reçu :" + computerDto.toString());
		if (computerDto.getComputerName().equals("")){
			modelAndView.addObject("errorName", errorName);
			List<CompanyDTO> listCompanies= companyDtoMapper.companyListToDTO(companyService.getListCompany());
			modelAndView.addObject( "listcompanies", listCompanies);

		}else {
			computerService.addComputer(computerDto.getComputerName(), 
										computerDto.getDateIntroduced(),
										computerDto.getDateDiscontinued(), 
										String.valueOf(computerDto.getCompanyId()));
		}
		return modelAndView;
	}

	
	@GetMapping("/deleteCompany")
	protected ModelAndView getDelete(){
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
	public ModelAndView getEdit ( @RequestParam("computerid")String computerId){
		ModelAndView modelAndView = new ModelAndView("/editComputer");
		List<CompanyDTO> listCompanies= companyDtoMapper.companyListToDTO(companyService.getListCompany());
		modelAndView.addObject( "listcompanies", listCompanies);
		ComputerDTO computerToEdit = computerDtoMapper.computerToDTO(computerService.queryOneComputer(computerId));
		modelAndView.addObject( "computer",computerToEdit);
		return modelAndView;
	}

	@PostMapping("/editComputer")
	public ModelAndView postEdit ( @Valid ComputerDTO computerDto ){
		Long computerId = computerDto.getComputerId();
		computerService.updateComputer(computerId, computerDtoMapper.computerDtoTocomputer(computerDto));
		ModelAndView modelAndView = new ModelAndView("redirect:/dashboard");
		return modelAndView ;
	}
	
	@PostMapping("/deleteSelected")
	public ModelAndView postDeleteSelected ( @RequestParam("selection")String idToDelete){
		
		ModelAndView modelAndView = new ModelAndView("redirect/dashboard");
		pageDto.pageOfComputer = computerDtoMapper.computerListToDTO(page.listPage(servletServices));
		modelAndView.addObject("listComputer", pageDto.pageOfComputer );
		return modelAndView ;
	}
}

