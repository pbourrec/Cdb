package com.excilys.cdb.database.mvcservlets;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.excilys.cdb.database.datatype.CompanyDTO;
import com.excilys.cdb.database.service.ServletServices;

@Controller
@RequestMapping("/addComputer")
public class AddComputerServlet {

	private static final String errorName = "Vous devez saisir un nom pour l'ordinateur à créer";
	@Autowired
	private ServletServices servletService;

	@GetMapping
	public ModelAndView doGet ( @RequestParam Map<String, String> parameters){
		ModelAndView modelAndView = new ModelAndView("/addComputer");

		List<CompanyDTO> listCompanies= servletService.getListCompany();
		modelAndView.addObject( "listcompanies", listCompanies);
		return modelAndView;
	}

	@PostMapping
	protected ModelAndView doPost( @RequestParam Map<String, String> parameters){
		ModelAndView modelAndView = new ModelAndView("/addComputer");

		String computerName = parameters.get("computerName");
		String introduced = parameters.get("introduced");
		String discontinued = parameters.get("discontinued");
		String companyId = parameters.get("companyId");
		if (computerName.equals("")) {
			modelAndView.addObject("errorName", errorName);
			List<CompanyDTO> listCompanies= servletService.getListCompany();
			modelAndView.addObject( "listcompanies", listCompanies);

		}else {
			servletService.addComputer(computerName, introduced, discontinued, companyId);
		}
		return modelAndView;
	}
}


